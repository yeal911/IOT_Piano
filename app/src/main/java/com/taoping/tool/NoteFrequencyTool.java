package com.taoping.tool;

import java.util.ArrayList;

//频率和音名对应库的类，并提供方法查询某一频率返回音名
public class NoteFrequencyTool {
    private static final ArrayList<NoteFrequencyRange> noteLibRangeMap = new ArrayList<>();
    //初始化note库
    static{
        noteLibRangeMap.add(new NoteFrequencyRange(127.14f,134.7f,"C03"));
        noteLibRangeMap.add(new NoteFrequencyRange(134.7f,142.71f,"CS3"));
        noteLibRangeMap.add(new NoteFrequencyRange(142.71f,151.195f,"D03"));
        noteLibRangeMap.add(new NoteFrequencyRange(151.195f,160.185f,"DS3"));
        noteLibRangeMap.add(new NoteFrequencyRange(160.185f,169.71f,"E03"));
        noteLibRangeMap.add(new NoteFrequencyRange(169.71f,179.805f,"F03"));
        noteLibRangeMap.add(new NoteFrequencyRange(179.805f,190.5f,"FS3"));
        noteLibRangeMap.add(new NoteFrequencyRange(190.5f,201.825f,"G03"));
        noteLibRangeMap.add(new NoteFrequencyRange(201.825f,213.825f,"GS3"));
        noteLibRangeMap.add(new NoteFrequencyRange(213.825f,226.54f,"A03"));
        noteLibRangeMap.add(new NoteFrequencyRange(226.54f,240.01f,"AS3"));
        noteLibRangeMap.add(new NoteFrequencyRange(240.01f,254.285f,"B03"));
        noteLibRangeMap.add(new NoteFrequencyRange(254.285f,269.405f,"C04"));
        noteLibRangeMap.add(new NoteFrequencyRange(269.405f,285.42f,"CS4"));
        noteLibRangeMap.add(new NoteFrequencyRange(285.42f,302.395f,"D04"));
        noteLibRangeMap.add(new NoteFrequencyRange(302.395f,320.38f,"DS4"));
        noteLibRangeMap.add(new NoteFrequencyRange(320.38f,339.43f,"E04"));
        noteLibRangeMap.add(new NoteFrequencyRange(339.43f,359.61f,"F04"));
        noteLibRangeMap.add(new NoteFrequencyRange(359.61f,380.995f,"FS4"));
        noteLibRangeMap.add(new NoteFrequencyRange(380.995f,403.65f,"G04"));
        noteLibRangeMap.add(new NoteFrequencyRange(403.65f,427.65f,"GS4"));
        noteLibRangeMap.add(new NoteFrequencyRange(427.65f,453.08f,"A04"));
        noteLibRangeMap.add(new NoteFrequencyRange(453.08f,480.02f,"AS4"));
        noteLibRangeMap.add(new NoteFrequencyRange(480.02f,508.565f,"B04"));
        noteLibRangeMap.add(new NoteFrequencyRange(508.565f,538.81f,"C05"));
        noteLibRangeMap.add(new NoteFrequencyRange(538.81f,570.85f,"CS5"));
        noteLibRangeMap.add(new NoteFrequencyRange(570.85f,604.79f,"D05"));
        noteLibRangeMap.add(new NoteFrequencyRange(604.79f,640.755f,"DS5"));
        noteLibRangeMap.add(new NoteFrequencyRange(640.755f,678.86f,"E05"));
        noteLibRangeMap.add(new NoteFrequencyRange(678.86f,719.225f,"F05"));
        noteLibRangeMap.add(new NoteFrequencyRange(719.225f,761.99f,"FS5"));
        noteLibRangeMap.add(new NoteFrequencyRange(761.99f,807.3f,"G05"));
        noteLibRangeMap.add(new NoteFrequencyRange(807.3f,855.305f,"GS5"));
        noteLibRangeMap.add(new NoteFrequencyRange(855.305f,906.165f,"A05"));
        noteLibRangeMap.add(new NoteFrequencyRange(906.165f,960.05f,"AS5"));
        noteLibRangeMap.add(new NoteFrequencyRange(960.05f,1017.135f,"B05"));
        noteLibRangeMap.add(new NoteFrequencyRange(1017.135f,1077.6f,"C06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1077.6f,1141.7f,"CS6"));
        noteLibRangeMap.add(new NoteFrequencyRange(1141.7f,1209.6f,"D06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1209.6f,1281.5f,"DS6"));
        noteLibRangeMap.add(new NoteFrequencyRange(1281.5f,1357.7f,"E06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1357.7f,1438.45f,"F06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1438.45f,1524f,"FS6"));
        noteLibRangeMap.add(new NoteFrequencyRange(1524f,1614.6f,"G06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1614.6f,1710.6f,"GS6"));
        noteLibRangeMap.add(new NoteFrequencyRange(1710.6f,1812.35f,"A06"));
        noteLibRangeMap.add(new NoteFrequencyRange(1812.35f,1920.1f,"AS6"));
        noteLibRangeMap.add(new NoteFrequencyRange(1920.1f,2034.25f,"B06"));
        noteLibRangeMap.add(new NoteFrequencyRange(15.852f,16.838f,"C00"));
        noteLibRangeMap.add(new NoteFrequencyRange(16.838f,17.839f,"CS0"));
        noteLibRangeMap.add(new NoteFrequencyRange(17.839f,18.8995f,"D00"));
        noteLibRangeMap.add(new NoteFrequencyRange(18.8995f,20.0235f,"DS0"));
        noteLibRangeMap.add(new NoteFrequencyRange(20.0235f,21.2145f,"E00"));
        noteLibRangeMap.add(new NoteFrequencyRange(21.2145f,22.476f,"F00"));
        noteLibRangeMap.add(new NoteFrequencyRange(22.476f,23.8125f,"FS0"));
        noteLibRangeMap.add(new NoteFrequencyRange(23.8125f,25.2285f,"G00"));
        noteLibRangeMap.add(new NoteFrequencyRange(25.2285f,26.7285f,"GS0"));
        noteLibRangeMap.add(new NoteFrequencyRange(26.7285f,28.3175f,"A00"));
        noteLibRangeMap.add(new NoteFrequencyRange(28.3175f,30.0015f,"AS0"));
        noteLibRangeMap.add(new NoteFrequencyRange(30.0015f,31.7855f,"B00"));
        noteLibRangeMap.add(new NoteFrequencyRange(31.7855f,33.6755f,"C01"));
        noteLibRangeMap.add(new NoteFrequencyRange(33.6755f,35.678f,"CS1"));
        noteLibRangeMap.add(new NoteFrequencyRange(35.678f,37.7995f,"D01"));
        noteLibRangeMap.add(new NoteFrequencyRange(37.7995f,40.047f,"DS1"));
        noteLibRangeMap.add(new NoteFrequencyRange(40.047f,42.4285f,"E01"));
        noteLibRangeMap.add(new NoteFrequencyRange(42.4285f,44.9515f,"F01"));
        noteLibRangeMap.add(new NoteFrequencyRange(44.9515f,47.624f,"FS1"));
        noteLibRangeMap.add(new NoteFrequencyRange(47.624f,50.456f,"G01"));
        noteLibRangeMap.add(new NoteFrequencyRange(50.456f,53.4565f,"GS1"));
        noteLibRangeMap.add(new NoteFrequencyRange(53.4565f,56.635f,"A01"));
        noteLibRangeMap.add(new NoteFrequencyRange(56.635f,60.0025f,"AS1"));
        noteLibRangeMap.add(new NoteFrequencyRange(60.0025f,63.5705f,"B01"));
        noteLibRangeMap.add(new NoteFrequencyRange(63.5705f,67.351f,"C02"));
        noteLibRangeMap.add(new NoteFrequencyRange(67.351f,71.356f,"CS2"));
        noteLibRangeMap.add(new NoteFrequencyRange(71.356f,75.599f,"D02"));
        noteLibRangeMap.add(new NoteFrequencyRange(75.599f,80.0945f,"DS2"));
        noteLibRangeMap.add(new NoteFrequencyRange(80.0945f,84.857f,"E02"));
        noteLibRangeMap.add(new NoteFrequencyRange(84.857f,89.903f,"F02"));
        noteLibRangeMap.add(new NoteFrequencyRange(89.903f,95.249f,"FS2"));
        noteLibRangeMap.add(new NoteFrequencyRange(95.249f,100.9145f,"G02"));
        noteLibRangeMap.add(new NoteFrequencyRange(100.9145f,106.915f,"GS2"));
        noteLibRangeMap.add(new NoteFrequencyRange(106.915f,113.27f,"A02"));
        noteLibRangeMap.add(new NoteFrequencyRange(113.27f,120.005f,"AS2"));
        noteLibRangeMap.add(new NoteFrequencyRange(120.005f,127.14f,"B02"));
        noteLibRangeMap.add(new NoteFrequencyRange(2034.25f,2155.25f,"C07"));
        noteLibRangeMap.add(new NoteFrequencyRange(2155.25f,2283.4f,"CS7"));
        noteLibRangeMap.add(new NoteFrequencyRange(2283.4f,2419.15f,"D07"));
        noteLibRangeMap.add(new NoteFrequencyRange(2419.15f,2563f,"DS7"));
        noteLibRangeMap.add(new NoteFrequencyRange(2563f,2715.4f,"E07"));
        noteLibRangeMap.add(new NoteFrequencyRange(2715.4f,2876.9f,"F07"));
        noteLibRangeMap.add(new NoteFrequencyRange(2876.9f,3048f,"FS7"));
        noteLibRangeMap.add(new NoteFrequencyRange(3048f,3229.2f,"G07"));
        noteLibRangeMap.add(new NoteFrequencyRange(3229.2f,3421.2f,"GS7"));
        noteLibRangeMap.add(new NoteFrequencyRange(3421.2f,3624.65f,"A07"));
        noteLibRangeMap.add(new NoteFrequencyRange(3624.65f,3840.2f,"AS7"));
        noteLibRangeMap.add(new NoteFrequencyRange(3840.2f,4068.55f,"B07"));
        noteLibRangeMap.add(new NoteFrequencyRange(4068.55f,4310.45f,"C08"));
        noteLibRangeMap.add(new NoteFrequencyRange(4310.45f,4566.75f,"CS8"));
        noteLibRangeMap.add(new NoteFrequencyRange(4566.75f,4838.3f,"D08"));
        noteLibRangeMap.add(new NoteFrequencyRange(4838.3f,5126f,"DS8"));
        noteLibRangeMap.add(new NoteFrequencyRange(5126f,5430.85f,"E08"));
        noteLibRangeMap.add(new NoteFrequencyRange(5430.85f,5753.8f,"F08"));
        noteLibRangeMap.add(new NoteFrequencyRange(5753.8f,6095.9f,"FS8"));
        noteLibRangeMap.add(new NoteFrequencyRange(6095.9f,6458.4f,"G08"));
        noteLibRangeMap.add(new NoteFrequencyRange(6458.4f,6842.45f,"GS8"));
        noteLibRangeMap.add(new NoteFrequencyRange(6842.45f,7249.3f,"A08"));
        noteLibRangeMap.add(new NoteFrequencyRange(7249.3f,7680.35f,"AS8"));
        noteLibRangeMap.add(new NoteFrequencyRange(7680.35f,8137.05f,"B08"));
        noteLibRangeMap.add(new NoteFrequencyRange(8137.05f,8620.9f,"C09"));
        noteLibRangeMap.add(new NoteFrequencyRange(8620.9f,9133.55f,"CS9"));
        noteLibRangeMap.add(new NoteFrequencyRange(9133.55f,9676.7f,"D09"));
        noteLibRangeMap.add(new NoteFrequencyRange(9676.7f,10252.05f,"DS9"));
        noteLibRangeMap.add(new NoteFrequencyRange(10252.05f,10861.5f,"E09"));
        noteLibRangeMap.add(new NoteFrequencyRange(10861.5f,11507.5f,"F09"));
        noteLibRangeMap.add(new NoteFrequencyRange(11507.5f,12192f,"FS9"));
        noteLibRangeMap.add(new NoteFrequencyRange(12192f,12917f,"G09"));
        noteLibRangeMap.add(new NoteFrequencyRange(12917f,13685f,"GS9"));
        noteLibRangeMap.add(new NoteFrequencyRange(13685f,14498.5f,"A09"));
        noteLibRangeMap.add(new NoteFrequencyRange(14498.5f,15360.5f,"AS9"));
        noteLibRangeMap.add(new NoteFrequencyRange(15360.5f,16704f,"B09"));
    }

    //查找note
    public static String getNoteByFrequency(float frequency){
        String res = "";
        for(NoteFrequencyRange note: noteLibRangeMap){
            if(note.isFrequencyMatch(frequency))
                res = note.noteName;
        }
        return res;
    }
}
