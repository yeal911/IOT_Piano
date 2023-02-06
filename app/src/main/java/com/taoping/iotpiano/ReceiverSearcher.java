package com.taoping.iotpiano;

import android.util.Log;

import com.taoping.notes.NoteQueue;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ReceiverSearcher {
    public static String receiverIP = null;
    public static void searchReceiver(MainActivity mainActivity) {
        new Thread(() -> {
            try {
                // 给定广播地址和端口
                InetAddress address = InetAddress.getByName("192.168.1.255");
                Log.d("ReceiverSearcher", "this IP: " + InetAddress.getLocalHost().getHostAddress());
                int port = 8888;

                // 创建数据报并发送
                String message = "PIANO";
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), address, port);
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(3000);
                socket.send(packet);

                // 监听并接收响应
                byte[] buffer = new byte[1024];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // 解析并打印响应
                String response = new String(buffer, 0, packet.getLength());
                InetAddress receiverAddress = packet.getAddress();
                receiverIP = receiverAddress.getHostName();
                System.out.println("Response: " + response);
                socket.close();
                System.out.println("Response ip: " + receiverIP);
                NoteQueue.receiverIP = receiverIP;
                NoteQueue.setSendingMode("WIFI");
                mainActivity.setIpText(receiverIP + ":8888");
            } catch (Exception e) {
                if(e instanceof SocketTimeoutException){
                    mainActivity.setIpText("No receiver found!");
                }
                e.printStackTrace();
            }
        }).start();
    }
}
