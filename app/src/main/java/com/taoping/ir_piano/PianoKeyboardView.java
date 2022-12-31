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
    private static final int NUM_KEYS = 36;
    private static final int KEYBOARD_CORNER_RADIUS = 10;
    private static int WHITE_KEY_WIDTH = 40;
    private static int WHITE_KEY_HEIGHT = 200;
    private static int BLACK_KEY_WIDTH = 30;
    private static int BLACK_KEY_HEIGHT = 120;
//    private static int KEY_SPACING = WHITE_KEY_WIDTH / 2;

    private static final Map<Integer, Integer> whiteKeyIndex = new HashMap<>();
    private static final Map<Integer, Integer> whiteKeyFullIndexReverse = new HashMap<>();

    private Paint whiteKeyPaint;
    private Paint blackKeyPaint;
    private Paint whiteKeyPressedPaint;
    private Paint blackKeyPressedPaint;
    //存储每个键的按下状态
    private int[] keyStates;

    public int pressedKey = -1;


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
        int whiteKeyNumber = ((index / 12 * 7) + (int)whiteKeyIndex.get(index % 12));
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
        int previousWhiteKeyIndex = ((index / 12 * 7) + (int)whiteKeyIndex.get((index-1) % 12));
        int x = previousWhiteKeyIndex * (WHITE_KEY_WIDTH + 10) + (WHITE_KEY_WIDTH - (BLACK_KEY_WIDTH - 10)/2);
//        Log.d(TAG, "drawBlackKey: previousWhiteKeyIndex: " + previousWhiteKeyIndex + ", black_x: " + x);
        int y = 0;
        if (keyStates[index] == 1) {
            canvas.drawRoundRect(x, y, x + BLACK_KEY_WIDTH, y + BLACK_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS,blackKeyPressedPaint);
        } else {
            canvas.drawRoundRect(x, y, x + BLACK_KEY_WIDTH, y + BLACK_KEY_HEIGHT,KEYBOARD_CORNER_RADIUS,KEYBOARD_CORNER_RADIUS,blackKeyPaint);
        }
    }

    private boolean isWhiteKey(int index) {
        // Black keys are at indices 1, 3, 6, 8, 10
        return index % 12 != 1 && index % 12 != 3 && index % 12 != 6 && index % 12 != 8 && index % 12 != 10;
    }

    public void pianoOnTouch(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN ) {
            int keyIndex = getKeyIndexAtPosition(event.getX(), event.getY());
            pressedKey = keyIndex;
            Log.d(TAG, "onTouch: pressed key index: " + keyIndex);
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
        if(whiteKeyIndex < 0 || whiteKeyIndex >= NUM_KEYS) {
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

    @Override
    protected void onSizeChanged(int w, int h, int old_w, int old_h) {
        super.onSizeChanged(w, h, old_w, old_h);
        WHITE_KEY_WIDTH = (w - 20 * 10) / 21; //每个白健中间隔10个像素的间隔，共有20个间隔
        BLACK_KEY_WIDTH = (int)(WHITE_KEY_WIDTH * 0.8);
        WHITE_KEY_HEIGHT = h;
        BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT * 2 / 3;
//        KEY_SPACING = WHITE_KEY_WIDTH / 2;
//        Log.d(TAG, "onSizeChanged: executed");
//        Log.d(TAG, "onSizeChanged: w: " + w + ", h: " + h);
//        Log.d(TAG, "onSizeChanged: white_w: " + WHITE_KEY_WIDTH + ", white_h: " + WHITE_KEY_HEIGHT);
//        Log.d(TAG, "onSizeChanged: black_w: " + BLACK_KEY_WIDTH + ", white_h: " + BLACK_KEY_HEIGHT);
    }

}
