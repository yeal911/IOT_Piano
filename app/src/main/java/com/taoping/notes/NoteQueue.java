package com.taoping.notes;

import android.util.Log;

import com.taoping.iotpiano.IRSender;
import com.taoping.iotpiano.MainActivity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayDeque;
import java.util.Objects;

public class NoteQueue {
    public static final ArrayDeque<Note> noteQueue = new ArrayDeque<>();
    public static final ArrayDeque<Note> recordQueue = new ArrayDeque<>();
    public static boolean sendStatus = true;
    public static String receiverIP = null;
    public static String sendingChannel = "WIFI";
    public static int sendingMode = 1; //1: 录制完成一次性发送；2：挨个同时发送

    //添加一个音符
    public static void addNote(Note note){
        noteQueue.add(note);
    }

    //录音识别状态下添加
    public static void addRecordNote(Note note){
        recordQueue.add(note);
    }

    //播放note，每按一个键，就实时发送
    public static void playNote(){
        new Thread(() -> {
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
                        int noteName = Objects.requireNonNull(noteQueue.poll()).noteIndex;
                        if(noteName == -1)
                            continue;
                        String message;
                        //小于10的前面补0，保持消息长度一致
                        if(noteName >= 10)
                            message = MainActivity.keyboardToneLevel + noteName;
                        else
                            message = MainActivity.keyboardToneLevel + "0" + noteName;
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
                    while(NoteQueue.sendStatus){
                        // 要发送的字符串
                        int noteIndex = Objects.requireNonNull(noteQueue.poll()).noteIndex;
                        if(noteIndex == -1) {
                            Thread.sleep(50);
                            continue;
                        }
                        IRSender.sendIRNote(noteIndex);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    //弹奏后，把所有的notes一次性发送出去播放
    public static void sendPlayNotes(){
        new Thread(() -> {
            // 线程的主体代码
            try {
                if(sendingChannel.equals("WIFI")){ //通过wifi发送
                    if(noteQueue.isEmpty())
                        return;
                    StringBuilder message = new StringBuilder();
                    while(!noteQueue.isEmpty()){
                        // 要发送的字符串
                        Note note = noteQueue.poll();
                        message.append(note.noteName);
                        message.append(note.intervalString);
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
        }).start();
    }

    //弹奏后，把所有的notes一次性发送出去播放
    public static void sendRecordNotes(){
        new Thread(() -> {
            // 线程的主体代码
            try {
                if(sendingChannel.equals("WIFI")){ //通过wifi发送
                    if(recordQueue.isEmpty())
                        return;
                    StringBuilder message = new StringBuilder();
                    while(!recordQueue.isEmpty()){
                        // 要发送的字符串
                        Note note = recordQueue.poll();
                        message.append(note.noteName);
                        message.append(note.intervalString);
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
        }).start();
    }

    public static void setSendingMode(String sendingMode){
        sendingChannel = sendingMode;
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

    public static String allRecordNotes() {
        ArrayDeque<Note> tmp = recordQueue.clone();
        StringBuilder res = new StringBuilder();
        while(!tmp.isEmpty()){
            Note noteTmp = tmp.poll();
            res.append(noteTmp.addTime);
            res.append(": ");
            res.append(noteTmp.noteName);
            res.append(", ");
            res.append(noteTmp.interval);
            res.append("\n");
        }
        return res.toString();
    }
}
