package com.taoping.notes;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Note {
    public int noteIndex; //音符的序号
    public int interval; //每个音符被按下的时长，单位毫秒
    public float frequency; //识别模式下，记录识别的频率
    public String noteName; //音名
    public String intervalString;
    public String addTime;

    private static final Map<String, String> indexToNote = new HashMap<>();

    static {
        indexToNote.put("LOW0", "C01");
        indexToNote.put("LOW1", "CS1");
        indexToNote.put("LOW2", "D01");
        indexToNote.put("LOW3", "DS1");
        indexToNote.put("LOW4", "E01");
        indexToNote.put("LOW5", "F01");
        indexToNote.put("LOW6", "FS1");
        indexToNote.put("LOW7", "G01");
        indexToNote.put("LOW8", "GS1");
        indexToNote.put("LOW9", "A01");
        indexToNote.put("LOW10", "AS1");
        indexToNote.put("LOW11", "B01");
        indexToNote.put("LOW12", "C02");
        indexToNote.put("LOW13", "CS2");
        indexToNote.put("LOW14", "D02");
        indexToNote.put("LOW15", "DS2");
        indexToNote.put("LOW16", "E02");
        indexToNote.put("LOW17", "F02");
        indexToNote.put("LOW18", "FS2");
        indexToNote.put("LOW19", "G02");
        indexToNote.put("LOW20", "GS2");
        indexToNote.put("LOW21", "A02");
        indexToNote.put("LOW22", "AS2");
        indexToNote.put("LOW23", "B02");
        indexToNote.put("LOW24", "C03");
        indexToNote.put("LOW25", "CS3");
        indexToNote.put("LOW26", "D03");
        indexToNote.put("LOW27", "DS3");
        indexToNote.put("LOW28", "E03");
        indexToNote.put("LOW29", "F03");
        indexToNote.put("LOW30", "FS3");
        indexToNote.put("LOW31", "G03");
        indexToNote.put("LOW32", "GS3");
        indexToNote.put("LOW33", "A03");
        indexToNote.put("LOW34", "AS3");
        indexToNote.put("LOW35", "B03");
        indexToNote.put("MID0", "C04");
        indexToNote.put("MID1", "CS4");
        indexToNote.put("MID2", "D04");
        indexToNote.put("MID3", "DS4");
        indexToNote.put("MID4", "E04");
        indexToNote.put("MID5", "F04");
        indexToNote.put("MID6", "FS4");
        indexToNote.put("MID7", "G04");
        indexToNote.put("MID8", "GS4");
        indexToNote.put("MID9", "A04");
        indexToNote.put("MID10", "AS4");
        indexToNote.put("MID11", "B04");
        indexToNote.put("MID12", "C05");
        indexToNote.put("MID13", "CS5");
        indexToNote.put("MID14", "D05");
        indexToNote.put("MID15", "DS5");
        indexToNote.put("MID16", "E05");
        indexToNote.put("MID17", "F05");
        indexToNote.put("MID18", "FS5");
        indexToNote.put("MID19", "G05");
        indexToNote.put("MID20", "GS5");
        indexToNote.put("MID21", "A05");
        indexToNote.put("MID22", "AS5");
        indexToNote.put("MID23", "B05");
        indexToNote.put("MID24", "C06");
        indexToNote.put("MID25", "CS6");
        indexToNote.put("MID26", "D06");
        indexToNote.put("MID27", "DS6");
        indexToNote.put("MID28", "E06");
        indexToNote.put("MID29", "F06");
        indexToNote.put("MID30", "FS6");
        indexToNote.put("MID31", "G06");
        indexToNote.put("MID32", "GS6");
        indexToNote.put("MID33", "A06");
        indexToNote.put("MID34", "AS6");
        indexToNote.put("MID35", "B06");
        indexToNote.put("HIG0", "C07");
        indexToNote.put("HIG1", "CS7");
        indexToNote.put("HIG2", "D07");
        indexToNote.put("HIG3", "DS7");
        indexToNote.put("HIG4", "E07");
        indexToNote.put("HIG5", "F07");
        indexToNote.put("HIG6", "FS7");
        indexToNote.put("HIG7", "G07");
        indexToNote.put("HIG8", "GS7");
        indexToNote.put("HIG9", "A07");
        indexToNote.put("HIG10", "AS7");
        indexToNote.put("HIG11", "B07");
        indexToNote.put("HIG12", "C08");
        indexToNote.put("HIG13", "CS8");
        indexToNote.put("HIG14", "D08");
        indexToNote.put("HIG15", "DS8");
        indexToNote.put("HIG16", "E08");
        indexToNote.put("HIG17", "F08");
        indexToNote.put("HIG18", "FS8");
        indexToNote.put("HIG19", "G08");
        indexToNote.put("HIG20", "GS8");
        indexToNote.put("HIG21", "A08");
        indexToNote.put("HIG22", "AS8");
        indexToNote.put("HIG23", "B08");
        indexToNote.put("HIG24", "C09");
        indexToNote.put("HIG25", "CS9");
        indexToNote.put("HIG26", "D09");
        indexToNote.put("HIG27", "DS9");
        indexToNote.put("HIG28", "E09");
        indexToNote.put("HIG29", "F09");
        indexToNote.put("HIG30", "FS9");
        indexToNote.put("HIG31", "G09");
        indexToNote.put("HIG32", "GS9");
        indexToNote.put("HIG33", "A09");
        indexToNote.put("HIG34", "AS9");
        indexToNote.put("HIG35", "B09");
    }

    @SuppressLint("SimpleDateFormat")
    public Note(String keyLevel, int noteIndex, int interval) {
        this.addTime = new SimpleDateFormat("hh:mm:ss:SSS").format(new Date());
        this.noteIndex = noteIndex;
        this.interval = interval;
        if (interval > 10000)
            this.intervalString = "9999";
        else
            this.intervalString = ("0000".substring(String.valueOf(interval).length()) + interval);
        this.noteName = indexToNote.get(keyLevel + noteIndex);
    }

    @SuppressLint("SimpleDateFormat")
    public Note(String noteName, int interval, float frequency) {
        this.addTime = new SimpleDateFormat("hh:mm:ss:SSS").format(new Date());
        this.noteName = noteName;
        this.frequency = frequency;
        this.interval = interval;
        if (interval > 10000)
            this.intervalString = "9999";
        else
            this.intervalString = ("0000".substring(String.valueOf(interval).length()) + interval);
    }

}
