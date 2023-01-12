package com.taoping.ir_piano;

public class Note {
    public int noteIndex; //音符的序号
    public int interval; //每个音符被按下的时长，单位毫秒

    public Note(int noteIndex, int interval){
        this.noteIndex = noteIndex;
        this.interval = interval;
    }
}
