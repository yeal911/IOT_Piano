package com.taoping.ir_piano;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayDeque;

public class NoteQueue {
    public static final ArrayDeque<Note> noteQueue = new ArrayDeque<Note>();
    public static boolean sendStatus = true;
    public static String receiverIP = null;
    public static String sendingChannel = "";
    public static String keyboardToneLevel = "MID"; //存储当前键盘的音区，默认中音区MID。LOW低音区，HIG高音区
    public static int sendingMode = 1; //1: 录制完成一次性发送；2：挨个同时发送

//    //从队首返回一个音符
//    public static Note popNote(){
//        if(!noteQueue.isEmpty()){
//            return (Note)noteQueue.poll();
//        }
//        return null;
//    }

    //添加一个音符
    public static void addNote(Note note){
        noteQueue.add(note);
    }

    //播放note，每按一个键，就实时发送
    public static void playNote(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程的主体代码
                try {
                    if(sendingChannel.equals("WIFI")){ //通过wifi发送
                        // 创建一个 DatagramSocket 实例
                        DatagramSocket socket = new DatagramSocket();
                        // 创建一个 DatagramPacket 实例，用于发送数据
                        InetAddress address = InetAddress.getByName(receiverIP);
                        int port = 8888;
                        while(NoteQueue.sendStatus){
                            // 要发送的字符串
                            int noteName = noteQueue.poll().noteIndex;
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
//                            Thread.sleep(50); //发送太快导致接收端完全黏在一起了
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

    //播放note，每按一个键，就实时发送
    public static void sendNotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程的主体代码
                try {
                    if(sendingChannel.equals("WIFI")){ //通过wifi发送
                        if(noteQueue.isEmpty())
                            return;
                        StringBuilder message = new StringBuilder();
                        while(!NoteQueue.noteQueue.isEmpty()){
                            // 要发送的字符串
                            Note note = noteQueue.poll();
                            //小于10的前面补0，保持消息长度一致
                            if(note.noteIndex >= 10)
                                message.append(keyboardToneLevel + note.noteIndex);
                            else
                                message.append(keyboardToneLevel + "0" + note.noteIndex);
                            //最大支持10s，因为超过10s位数就不同了，所以最大9999
                            if(note.interval > 10000)
                                message.append("9999");
                            else
                                message.append("0000".substring(String.valueOf(note.interval).length()) + note.interval);
                        }
                        Log.d("NoteQueue", "run: " + message);
                        // 创建一个 DatagramSocket 实例
                        DatagramSocket socket = new DatagramSocket();
                        // 创建一个 DatagramPacket 实例，用于发送数据
                        InetAddress address = InetAddress.getByName(receiverIP);
                        int port = 8888;
                        // 将字符串转换为字节数组
                        byte[] data = message.toString().getBytes();
                        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                        // 发送数据包
                        socket.send(packet);
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
        sendingChannel = "WIFI";
        sendStatus = true;
        //如果是播放note
        if(NoteQueue.sendingMode == 2) {
            NoteQueue.noteQueue.clear();
            playNote();
        }
    }

    public static void stopSendingNote(){
        sendStatus = false;
    }

}
