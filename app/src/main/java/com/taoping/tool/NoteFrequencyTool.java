package com.taoping.tool;

import java.util.ArrayList;

//频率和音名对应库的类，并提供方法查询某一频率返回音名
public class NoteFrequencyTool {
    private static final ArrayList<NoteFrequencyRange> noteLib = new ArrayList<>();
    //初始化note库
    static{
        noteLib.add(new NoteFrequencyRange(127.14f,134.7f,"C3"));
        noteLib.add(new NoteFrequencyRange(134.7f,142.71f,"CS3"));
        noteLib.add(new NoteFrequencyRange(142.71f,151.195f,"D3"));
        noteLib.add(new NoteFrequencyRange(151.195f,160.185f,"DS3"));
        noteLib.add(new NoteFrequencyRange(160.185f,169.71f,"E3"));
        noteLib.add(new NoteFrequencyRange(169.71f,179.805f,"F3"));
        noteLib.add(new NoteFrequencyRange(179.805f,190.5f,"FS3"));
        noteLib.add(new NoteFrequencyRange(190.5f,201.825f,"G3"));
        noteLib.add(new NoteFrequencyRange(201.825f,213.825f,"GS3"));
        noteLib.add(new NoteFrequencyRange(213.825f,226.54f,"A3"));
        noteLib.add(new NoteFrequencyRange(226.54f,240.01f,"AS3"));
        noteLib.add(new NoteFrequencyRange(240.01f,254.285f,"B3"));
        noteLib.add(new NoteFrequencyRange(254.285f,269.405f,"C4"));
        noteLib.add(new NoteFrequencyRange(269.405f,285.42f,"CS4"));
        noteLib.add(new NoteFrequencyRange(285.42f,302.395f,"D4"));
        noteLib.add(new NoteFrequencyRange(302.395f,320.38f,"DS4"));
        noteLib.add(new NoteFrequencyRange(320.38f,339.43f,"E4"));
        noteLib.add(new NoteFrequencyRange(339.43f,359.61f,"F4"));
        noteLib.add(new NoteFrequencyRange(359.61f,380.995f,"FS4"));
        noteLib.add(new NoteFrequencyRange(380.995f,403.65f,"G4"));
        noteLib.add(new NoteFrequencyRange(403.65f,427.65f,"GS4"));
        noteLib.add(new NoteFrequencyRange(427.65f,453.08f,"A4"));
        noteLib.add(new NoteFrequencyRange(453.08f,480.02f,"AS4"));
        noteLib.add(new NoteFrequencyRange(480.02f,508.565f,"B4"));
        noteLib.add(new NoteFrequencyRange(508.565f,538.81f,"C5"));
        noteLib.add(new NoteFrequencyRange(538.81f,570.85f,"CS5"));
        noteLib.add(new NoteFrequencyRange(570.85f,604.79f,"D5"));
        noteLib.add(new NoteFrequencyRange(604.79f,640.755f,"DS5"));
        noteLib.add(new NoteFrequencyRange(640.755f,678.86f,"E5"));
        noteLib.add(new NoteFrequencyRange(678.86f,719.225f,"F5"));
        noteLib.add(new NoteFrequencyRange(719.225f,761.99f,"FS5"));
        noteLib.add(new NoteFrequencyRange(761.99f,807.3f,"G5"));
        noteLib.add(new NoteFrequencyRange(807.3f,855.305f,"GS5"));
        noteLib.add(new NoteFrequencyRange(855.305f,906.165f,"A5"));
        noteLib.add(new NoteFrequencyRange(906.165f,960.05f,"AS5"));
        noteLib.add(new NoteFrequencyRange(960.05f,1017.135f,"B5"));
        noteLib.add(new NoteFrequencyRange(1017.135f,1077.6f,"C6"));
        noteLib.add(new NoteFrequencyRange(1077.6f,1141.7f,"CS6"));
        noteLib.add(new NoteFrequencyRange(1141.7f,1209.6f,"D6"));
        noteLib.add(new NoteFrequencyRange(1209.6f,1281.5f,"DS6"));
        noteLib.add(new NoteFrequencyRange(1281.5f,1357.7f,"E6"));
        noteLib.add(new NoteFrequencyRange(1357.7f,1438.45f,"F6"));
        noteLib.add(new NoteFrequencyRange(1438.45f,1524f,"FS6"));
        noteLib.add(new NoteFrequencyRange(1524f,1614.6f,"G6"));
        noteLib.add(new NoteFrequencyRange(1614.6f,1710.6f,"GS6"));
        noteLib.add(new NoteFrequencyRange(1710.6f,1812.35f,"A6"));
        noteLib.add(new NoteFrequencyRange(1812.35f,1920.1f,"AS6"));
        noteLib.add(new NoteFrequencyRange(1920.1f,2034.25f,"B6"));
        noteLib.add(new NoteFrequencyRange(15.852f,16.838f,"C0"));
        noteLib.add(new NoteFrequencyRange(16.838f,17.839f,"CS0"));
        noteLib.add(new NoteFrequencyRange(17.839f,18.8995f,"D0"));
        noteLib.add(new NoteFrequencyRange(18.8995f,20.0235f,"DS0"));
        noteLib.add(new NoteFrequencyRange(20.0235f,21.2145f,"E0"));
        noteLib.add(new NoteFrequencyRange(21.2145f,22.476f,"F0"));
        noteLib.add(new NoteFrequencyRange(22.476f,23.8125f,"FS0"));
        noteLib.add(new NoteFrequencyRange(23.8125f,25.2285f,"G0"));
        noteLib.add(new NoteFrequencyRange(25.2285f,26.7285f,"GS0"));
        noteLib.add(new NoteFrequencyRange(26.7285f,28.3175f,"A0"));
        noteLib.add(new NoteFrequencyRange(28.3175f,30.0015f,"AS0"));
        noteLib.add(new NoteFrequencyRange(30.0015f,31.7855f,"B0"));
        noteLib.add(new NoteFrequencyRange(31.7855f,33.6755f,"C1"));
        noteLib.add(new NoteFrequencyRange(33.6755f,35.678f,"CS1"));
        noteLib.add(new NoteFrequencyRange(35.678f,37.7995f,"D1"));
        noteLib.add(new NoteFrequencyRange(37.7995f,40.047f,"DS1"));
        noteLib.add(new NoteFrequencyRange(40.047f,42.4285f,"E1"));
        noteLib.add(new NoteFrequencyRange(42.4285f,44.9515f,"F1"));
        noteLib.add(new NoteFrequencyRange(44.9515f,47.624f,"FS1"));
        noteLib.add(new NoteFrequencyRange(47.624f,50.456f,"G1"));
        noteLib.add(new NoteFrequencyRange(50.456f,53.4565f,"GS1"));
        noteLib.add(new NoteFrequencyRange(53.4565f,56.635f,"A1"));
        noteLib.add(new NoteFrequencyRange(56.635f,60.0025f,"AS1"));
        noteLib.add(new NoteFrequencyRange(60.0025f,63.5705f,"B1"));
        noteLib.add(new NoteFrequencyRange(63.5705f,67.351f,"C2"));
        noteLib.add(new NoteFrequencyRange(67.351f,71.356f,"CS2"));
        noteLib.add(new NoteFrequencyRange(71.356f,75.599f,"D2"));
        noteLib.add(new NoteFrequencyRange(75.599f,80.0945f,"DS2"));
        noteLib.add(new NoteFrequencyRange(80.0945f,84.857f,"E2"));
        noteLib.add(new NoteFrequencyRange(84.857f,89.903f,"F2"));
        noteLib.add(new NoteFrequencyRange(89.903f,95.249f,"FS2"));
        noteLib.add(new NoteFrequencyRange(95.249f,100.9145f,"G2"));
        noteLib.add(new NoteFrequencyRange(100.9145f,106.915f,"GS2"));
        noteLib.add(new NoteFrequencyRange(106.915f,113.27f,"A2"));
        noteLib.add(new NoteFrequencyRange(113.27f,120.005f,"AS2"));
        noteLib.add(new NoteFrequencyRange(120.005f,127.14f,"B2"));
        noteLib.add(new NoteFrequencyRange(2034.25f,2155.25f,"C7"));
        noteLib.add(new NoteFrequencyRange(2155.25f,2283.4f,"CS7"));
        noteLib.add(new NoteFrequencyRange(2283.4f,2419.15f,"D7"));
        noteLib.add(new NoteFrequencyRange(2419.15f,2563f,"DS7"));
        noteLib.add(new NoteFrequencyRange(2563f,2715.4f,"E7"));
        noteLib.add(new NoteFrequencyRange(2715.4f,2876.9f,"F7"));
        noteLib.add(new NoteFrequencyRange(2876.9f,3048f,"FS7"));
        noteLib.add(new NoteFrequencyRange(3048f,3229.2f,"G7"));
        noteLib.add(new NoteFrequencyRange(3229.2f,3421.2f,"GS7"));
        noteLib.add(new NoteFrequencyRange(3421.2f,3624.65f,"A7"));
        noteLib.add(new NoteFrequencyRange(3624.65f,3840.2f,"AS7"));
        noteLib.add(new NoteFrequencyRange(3840.2f,4068.55f,"B7"));
        noteLib.add(new NoteFrequencyRange(4068.55f,4310.45f,"C8"));
        noteLib.add(new NoteFrequencyRange(4310.45f,4566.75f,"CS8"));
        noteLib.add(new NoteFrequencyRange(4566.75f,4838.3f,"D8"));
        noteLib.add(new NoteFrequencyRange(4838.3f,5126f,"DS8"));
        noteLib.add(new NoteFrequencyRange(5126f,5430.85f,"E8"));
        noteLib.add(new NoteFrequencyRange(5430.85f,5753.8f,"F8"));
        noteLib.add(new NoteFrequencyRange(5753.8f,6095.9f,"FS8"));
        noteLib.add(new NoteFrequencyRange(6095.9f,6458.4f,"G8"));
        noteLib.add(new NoteFrequencyRange(6458.4f,6842.45f,"GS8"));
        noteLib.add(new NoteFrequencyRange(6842.45f,7249.3f,"A8"));
        noteLib.add(new NoteFrequencyRange(7249.3f,7680.35f,"AS8"));
        noteLib.add(new NoteFrequencyRange(7680.35f,8137.05f,"B8"));
        noteLib.add(new NoteFrequencyRange(8137.05f,8620.9f,"C9"));
        noteLib.add(new NoteFrequencyRange(8620.9f,9133.55f,"CS9"));
        noteLib.add(new NoteFrequencyRange(9133.55f,9676.7f,"D9"));
        noteLib.add(new NoteFrequencyRange(9676.7f,10252.05f,"DS9"));
        noteLib.add(new NoteFrequencyRange(10252.05f,10861.5f,"E9"));
        noteLib.add(new NoteFrequencyRange(10861.5f,11507.5f,"F9"));
        noteLib.add(new NoteFrequencyRange(11507.5f,12192f,"FS9"));
        noteLib.add(new NoteFrequencyRange(12192f,12917f,"G9"));
        noteLib.add(new NoteFrequencyRange(12917f,13685f,"GS9"));
        noteLib.add(new NoteFrequencyRange(13685f,14498.5f,"A9"));
        noteLib.add(new NoteFrequencyRange(14498.5f,15360.5f,"AS9"));
        noteLib.add(new NoteFrequencyRange(15360.5f,16704f,"B9"));

    }

    //查找note
    public static String getNoteByFrequency(float frequency){
        String res = "";
        for(NoteFrequencyRange note: noteLib){
            if(note.isFrequencyMatch(frequency))
                res = note.noteName;
        }
        return res;
    }
}
