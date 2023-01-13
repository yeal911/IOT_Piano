package com.taoping.iotpiano;

import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;

public class IRSender {

    public static void sendIRNote(Activity activity, int note) {
        //获取安卓开放的红外管理类
        ConsumerIrManager mCIR = (ConsumerIrManager) activity.getSystemService(Context.CONSUMER_IR_SERVICE);
        //判断设备是否可用
        if (mCIR.hasIrEmitter()) {
            //配置你要发送的红外码值   例：0xaabbdd22
            int[] pattern = {9000, 4500,//引导码
                    560, 560, 560, 1690, 560, 560, 560, 560, 560, 560, 560, 1690, 560, 560, 560, 560,
                    560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690,
                    560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690,
                    560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690,
                    9000, 2250, 2250, 94000, 9000, 2250, 2250, 94000};//稳定码

            //发送红外
            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    }

    public static void switchMode(Activity activity) {
        //获取安卓开放的红外管理类
        ConsumerIrManager mCIR = (ConsumerIrManager) activity.getSystemService(Context.CONSUMER_IR_SERVICE);
        //判断设备是否可用
        if (mCIR.hasIrEmitter()) {
            //配置你要发送的红外码值   例：0xaabbdd22
            int[] pattern = {9000, 4500,//引导码
                    560, 560, 560, 1690, 560, 560, 560, 560, 560, 560, 560, 1690, 560, 560, 560, 560,
                    560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690,
                    560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690,
                    560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690,
                    9000, 2250, 2250, 94000, 9000, 2250, 2250, 94000};//稳定码

            //发送红外
            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    }

    public static void sendIRMute(MainActivity activity) {
        //获取安卓开放的红外管理类
        ConsumerIrManager mCIR = (ConsumerIrManager) activity.getSystemService(Context.CONSUMER_IR_SERVICE);
        //判断设备是否可用
        if (mCIR.hasIrEmitter()) {
            //配置你要发送的红外码值   例：0xaabbdd22
            int[] pattern = {9000, 4500,//引导码
                    560, 560, 560, 1690, 560, 560, 560, 560, 560, 560, 560, 1690, 560, 560, 560, 560,
                    560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690,
                    560, 1690, 560, 1690, 560, 560, 560, 1690, 560, 1690, 560, 1690, 560, 560, 560, 1690,
                    560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690, 560, 560, 560, 1690,
                    9000, 2250, 2250, 94000, 9000, 2250, 2250, 94000};//稳定码

            //发送红外
            // 在38.4KHz条件下进行模式转换
            mCIR.transmit(38400, pattern);
        }
    }
}
