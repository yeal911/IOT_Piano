package com.taoping.ir_piano;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayDeque;

public class NoteQueue {
    public static final ArrayDeque<Integer> noteQueue = new ArrayDeque<Integer>();
    public static boolean sendStatus = true;
    public static String receiverIP = null;
    public static String keyboardToneLevel = "MID"; //存储当前键盘的音区，默认中音区MID。LOW低音区，HIG高音区

    //从队首返回一个音符
    public static int popNote(){
        if(!noteQueue.isEmpty()){
            return (Integer)noteQueue.poll();
        }
        return -1;
    }

    //添加一个音符
    public static void addNote(int note){
        noteQueue.add(note);
    }

    //发送note
    public static void sendNotes(String sendingMode){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程的主体代码
                try {
                    if(sendingMode.equals("WIFI")){ //通过wifi发送
                        // 创建一个 DatagramSocket 实例
                        DatagramSocket socket = new DatagramSocket();
                        // 创建一个 DatagramPacket 实例，用于发送数据
                        InetAddress address = InetAddress.getByName(receiverIP);
                        int port = 8888;
                        while(NoteQueue.sendStatus){
                            // 要发送的字符串
                            int noteName = popNote();
                            if(noteName == -1)
                                continue;
                            String message;
                            //小于10的前面补0，保持消息长度一致
                            if(noteName >= 10)
                                message = keyboardToneLevel + noteName;
                            else
                                message = keyboardToneLevel + "0" + noteName;
                            Log.d("NoteQueue", "sending " + message);
                            // 将字符串转换为字节数组
                            byte[] data = message.getBytes();
                            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                            // 发送数据包
                            socket.send(packet);
                            Thread.sleep(50); //发送太快导致接收端完全黏在一起了
                        }
                        // 关闭套接字
                        socket.close();
                    }else{ //通过IR发送

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void startSendingNote(String sendingMode){
        sendStatus = true;
        NoteQueue.noteQueue.clear();
        sendNotes(sendingMode);
    }

    public static void stopSendingNote(){
        sendStatus = false;
    }

}
