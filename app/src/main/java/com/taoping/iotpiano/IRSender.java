package com.taoping.iotpiano;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;

import java.util.ArrayList;
import java.util.List;

public class IRSender {

    private final static int[] IR_HEAD = {9000, 4500}; //引导码
    private final static int[] IR_TAIL = {9000, 2250, 2250, 94000, 9000, 2250, 2250, 94000}; //稳定码

    private final static int[] BINARY_0 = {560, 560};
    private final static int[] BINARY_1 = {560, 1690};

    //红外设备，在主UI载入的时候初始化
    public static ConsumerIrManager mCIR;

    //传递过来钢琴键的index，转换成二进制，然后再编码成pattern
    private static int[] formPattern(int targetNumber){
        String targetBinString = Integer.toBinaryString(targetNumber);
        targetBinString = "00000000".substring(targetBinString.length()) + targetBinString;
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        addArray2List(pattern, IR_HEAD);
        for(int i=0;i<targetBinString.length();i++){
            int digit = Integer.parseInt(targetBinString.substring(i, i+1));
            if(digit == 1)
                addArray2List(pattern, BINARY_1);
            else
                addArray2List(pattern, BINARY_0);
        }
        addArray2List(pattern, IR_TAIL);
        return pattern.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void addArray2List(List<Integer> list, int[] value){
        for (int j : value)
            list.add(j);
    }

    public static void sendIRNote(int note) {
        //判断设备是否可用
        if (mCIR.hasIrEmitter()) {
            //配置你要发送的红外码值   例：0xaabbdd22
            int[] pattern = formPattern(note);
            //发送红外
            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    }

    public static void sendIRMute() {
        //判断设备是否可用
        if (mCIR.hasIrEmitter()) {
            //发送8位全是1的信号，表是静音
            int[] pattern = formPattern(255);
            //发送红外
            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    }
}
