package com.taoping.ir_piano;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private PianoKeyboardView keyboard;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        final Button btn = (Button) findViewById(R.id.switchBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMode();
                // Perform action on click
                Toast.makeText(MainActivity.this, "btn is clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        keyboard = (PianoKeyboardView) findViewById(R.id.piano_keyboard_view);
        keyboard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                keyboard.pianoOnTouch(view, motionEvent);
//                Log.d(TAG, "onTouch: pressed Key:" + keyboard.pressedKey + ": " + motionEvent.getAction());
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && keyboard.pressedKey != -1)
                    notePressDown();
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    notePressUp();
                return true;
            }
        });


    }

    private void switchMode(){
        IRSender.switchMode(this);
    }

    private void notePressDown(){
        IRSender.sendIRNote(this, keyboard.pressedKey);
    }

    private void notePressUp(){
        IRSender.sendIRMute(this);
    }


}