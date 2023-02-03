package com.taoping.tool;

public class NoteFrequencyRange {
    //前开后闭
    public float noteFrequencyStart;
    public float noteFrequencyEnd;
    public String noteName;
    public NoteFrequencyRange(float start, float end, String name){
        this.noteFrequencyStart = start;
        this.noteFrequencyEnd = end;
        this.noteName = name;
    }

    //检查特定频率是否在该note频率范围
    public boolean isFrequencyMatch(float frequency){
        return frequency >= this.noteFrequencyStart && frequency < this.noteFrequencyEnd;
    }
}
