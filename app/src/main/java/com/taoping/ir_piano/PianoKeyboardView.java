package com.taoping.ir_piano;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class PianoKeyboardView extends View {
    public static final int NUM_KEYS = 36;
    public static final int KEYBOARD_CORNER_RADIUS = 10;
    public static int WHITE_KEY_WIDTH = 40;
    public static int WHITE_KEY_HEIGHT = 200;
    public static int BLACK_KEY_WIDTH = 30;
    public static int BLACK_KEY_HEIGHT = 120;
//    private static int KEY_SPACING = WHITE_KEY_WIDTH / 2;
    public static final Map<Integer, String> keyNoteName = new HashMap<>();

    private static final Map<Integer, Integer> whiteKeyIndex = new HashMap<>();
    private static final Map<Integer, Integer> whiteKeyFullIndexReverse = new HashMap<>();

    private Paint whiteKeyPaint;
    private Paint blackKeyPaint;
    private Paint whiteKeyPressedPaint;
    private Paint blackKeyPressedPaint;
    //存储每个键的按下状态
    private int[] keyStates;

    public int pressedKey = -1;
    public int pressedAreaWhiteKey = -1;

    public PianoKeyboardView(Context context) {
        this(context, null);
    }

    public PianoKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PianoKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        whiteKeyPaint = new Paint();
        whiteKeyPaint.setColor(Color.WHITE);
        whiteKeyPaint.setStyle(Paint.Style.FILL);
        whiteKeyPressedPaint = new Paint();
        whiteKeyPressedPaint.setColor(Color.LTGRAY);
        whiteKeyPressedPaint.setStyle(Paint.Style.FILL);
        blackKeyPaint = new Paint();
        blackKeyPaint.setColor(Color.BLACK);
        blackKeyPaint.setStyle(Paint.Style.FILL);
        blackKeyPressedPaint = new Paint();
        blackKeyPressedPaint.setColor(Color.DKGRAY);
        blackKeyPressedPaint.setStyle(Paint.Style.FILL);
        keyStates = new int[NUM_KEYS];

        //白健为0,2,4,5,7,9,11
        whiteKeyIndex.put(0,0);
        whiteKeyIndex.put(2,1);
        whiteKeyIndex.put(4,2);
        whiteKeyIndex.put(5,3);
        whiteKeyIndex.put(7,4);
        whiteKeyIndex.put(9,5);
        whiteKeyIndex.put(11,6);

        whiteKeyFullIndexReverse.put(0, 0);
        whiteKeyFullIndexReverse.put(1, 2);
        whiteKeyFullIndexReverse.put(2, 4);
        whiteKeyFullIndexReverse.put(3, 5);
        whiteKeyFullIndexReverse.put(4, 7);
        whiteKeyFullIndexReverse.put(5, 9);
        whiteKeyFullIndexReverse.put(6, 11);

        keyNoteName.put(0,"C");
        keyNoteName.put(1,"C#");
        keyNoteName.put(2,"D");
        keyNoteName.put(3,"D#");
        keyNoteName.put(4,"E");
        keyNoteName.put(5,"F");
        keyNoteName.put(6,"F#");
        keyNoteName.put(7,"G");
        keyNoteName.put(8,"G#");
        keyNoteName.put(9,"A");
        keyNoteName.put(10,"A#");
        keyNoteName.put(11,"B");

//        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < NUM_KEYS; i++) {
            if (isWhiteKey(i)) {
                drawWhiteKey(canvas, i);
            }
        }
        for (int i = 0; i < NUM_KEYS; i++) {
            if (!isWhiteKey(i)) {
                drawBlackKey(canvas, i);
            }
        }
    }

    private void drawWhiteKey(Canvas canvas, int index) {
        int whiteKeyNumber = ((index / 12 * 7) + whiteKeyIndex.get(index % 12));
        int x = whiteKeyNumber * (WHITE_KEY_WIDTH + 10);
//        Log.d(TAG, "drawWhiteKey: index: " + index + ", white key number: " + whiteKeyNumber + ", x: " + x);
        int y = 0;
        if (keyStates[index] == 1) {
            canvas.drawRoundRect(x, y, x + WHITE_KEY_WIDTH, y + WHITE_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS, whiteKeyPressedPaint);
        } else {
            canvas.drawRoundRect(x, y, x + WHITE_KEY_WIDTH, y + WHITE_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS, whiteKeyPaint);
        }
    }

    private void drawBlackKey(Canvas canvas, int index) {
        //黑键的x坐标都是位于前一个白健加一半的位置
        int previousWhiteKeyIndex = ((index / 12 * 7) + whiteKeyIndex.get((index-1) % 12));
        int x = previousWhiteKeyIndex * (WHITE_KEY_WIDTH + 10) + (WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH - 10)/2);
//        Log.d(TAG, "drawBlackKey: previousWhiteKeyIndex: " + previousWhiteKeyIndex + ", black_x: " + x);
        int y = 0;
        if (keyStates[index] == 1) {
            canvas.drawRoundRect(x, y, x + BLACK_KEY_WIDTH, y + BLACK_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS,blackKeyPressedPaint);
        } else {
            canvas.drawRoundRect(x, y, x + BLACK_KEY_WIDTH, y + BLACK_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS,blackKeyPaint);
        }
    }

    public boolean isWhiteKey(int index) {
        // Black keys are at indices 1, 3, 6, 8, 10
        return index % 12 != 1 && index % 12 != 3 && index % 12 != 6 && index % 12 != 8 && index % 12 != 10;
    }

    public void pianoOnTouch(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN ) {
            int keyIndex = getKeyIndexAtPosition(event.getX(), event.getY());
            pressedKey = keyIndex;
//            Log.d(TAG, "onTouch: pressed key index: " + keyIndex);
            if (keyIndex != -1) {
                keyStates[keyIndex] = 1;
                invalidate();
            }
        } else if (action == MotionEvent.ACTION_UP) {
            pressedKey = -1;
            resetKeyStates();
            invalidate();
        }
    }

    private int getKeyIndexAtPosition(float x, float y) {
        int whiteKeyIndex = (int) (x / (WHITE_KEY_WIDTH + 10));
        pressedAreaWhiteKey = whiteKeyIndex;
        if(whiteKeyIndex < 0 || whiteKeyIndex >= NUM_KEYS) {
            pressedAreaWhiteKey = -1;
            return -1;
        }
        if(y < BLACK_KEY_HEIGHT){
            int tmp = getBlackX(whiteKeyIndex);
            int tmp1 = getBlackX(whiteKeyIndex - 1);
            if(x >= tmp && x <= tmp + BLACK_KEY_WIDTH && !isWhiteKey(getWhiteKeyIndex(whiteKeyIndex) + 1)){
                return getWhiteKeyIndex(whiteKeyIndex)  + 1;
            }else if(x >= tmp1 && x <= tmp1 + BLACK_KEY_WIDTH && !isWhiteKey(getWhiteKeyIndex(whiteKeyIndex) - 1)){
                return getWhiteKeyIndex(whiteKeyIndex) - 1;
            }else
                return getWhiteKeyIndex(whiteKeyIndex);
        }else
            return getWhiteKeyIndex(whiteKeyIndex);
    }

    //return full white key index(considering black key, total is 36) based on only white key index (considering only white keys)
    private int getWhiteKeyIndex(int index){
        return index / 7 * 12 + whiteKeyFullIndexReverse.get(index % 7);
    }

    //根据白健序号，获取上面黑键的x坐标
    private int getBlackX(int whiteIndex){
        return whiteIndex * (WHITE_KEY_WIDTH + 10) + (WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH - 10)/2);
    }

    private void resetKeyStates() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keyStates[i] = 0;
        }
    }

    //根据键的序号，返回该键的x坐标值
    public int getXByKeyIndex(){
        if(isWhiteKey(this.pressedKey)){
            return this.pressedAreaWhiteKey * (WHITE_KEY_WIDTH+10) ; //每个白键中间隔10dp，键盘左边margin也是10
        }else{
            return (((this.pressedKey-1)/12*7+whiteKeyIndex.get((this.pressedKey-1) % 12)) * (WHITE_KEY_WIDTH+10) ) + (WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH - 10)/2);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int old_w, int old_h) {
        super.onSizeChanged(w, h, old_w, old_h);
        WHITE_KEY_WIDTH = (w - 20 * 10) / 21; //每个白健中间隔10个像素的间隔，共有20个间隔
        BLACK_KEY_WIDTH = (int)(WHITE_KEY_WIDTH * 0.8);
        WHITE_KEY_HEIGHT = h;
        BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT * 2 / 3;
    }

}
