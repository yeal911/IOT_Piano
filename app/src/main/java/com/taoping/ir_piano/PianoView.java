package com.taoping.ir_piano;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PianoView extends View {
    private static final String TAG = PianoView.class.getSimpleName();

    public PianoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    Bitmap whiteKey, blackKey;
    Paint paint = new Paint();

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (whiteKey == null) {
            whiteKey = BitmapFactory.decodeResource(getResources(), R.drawable.white_key);
        }
        if (blackKey == null) {
            blackKey = BitmapFactory.decodeResource(getResources(), R.drawable.black_key);
        }

        int whiteKeys = 10;
        int blackKeys = 10;


        // draw white keys
        for (int i = 0; i < whiteKeys; i++) {
            canvas.drawBitmap(whiteKey, i * whiteKey.getWidth(), 0, paint);
        }
        // draw black keys
        for (int i = 0; i < blackKeys; i++) {
            if (i != 3 && i != 7) {
                canvas.drawBitmap(blackKey, i * blackKey.getWidth() + blackKey.getWidth() * 0.5f, 0, paint);
            }
        }
    }
}