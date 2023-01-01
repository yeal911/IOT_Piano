package com.taoping.ir_piano;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PianoKeyboardView keyboard;
    private TextView noteText;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        @SuppressLint("UseSwitchCompatOrMaterialCode") final Switch transmissionSwitch = findViewById(R.id.switchWifiIR);
        final Button searchIPBtn = (Button) findViewById(R.id.searchReceiverBtn);
        final Button switchModeBtn = (Button) findViewById(R.id.switchBtn);
        final EditText ipText = (EditText) findViewById(R.id.ipText);
        keyboard = (PianoKeyboardView) findViewById(R.id.piano_keyboard_view);
        noteText = (TextView) findViewById(R.id.noteText);

        transmissionSwitch.setOnClickListener(view -> {
            if(transmissionSwitch.isChecked()){
                transmissionSwitch.setText("   IR");
                ipText.setVisibility(View.INVISIBLE);
                searchIPBtn.setVisibility(View.INVISIBLE);
            }else{
                transmissionSwitch.setText("Wifi");
                ipText.setVisibility(View.VISIBLE);
                searchIPBtn.setVisibility(View.VISIBLE);
            }
        });

        switchModeBtn.setOnClickListener(v -> {
            switchMode();
            // Perform action on click
            Toast.makeText(MainActivity.this, "btn is clicked!", Toast.LENGTH_SHORT).show();
        });

        searchIPBtn.setOnClickListener(v -> {
            searchReceiverIP();
            // Perform action on click
            Toast.makeText(MainActivity.this, "btn is clicked!", Toast.LENGTH_SHORT).show();
        });

        keyboard.setOnTouchListener((view, motionEvent) -> {
            keyboard.pianoOnTouch(motionEvent);
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && keyboard.pressedKey != -1)
                notePressDown();
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                notePressUp();
            return true;
        });


    }

    //
    private void searchReceiverIP() {
    }

    private void switchMode(){
//        IRSender.switchMode(this);
    }

    private void notePressDown(){
        ViewGroup.LayoutParams noteTextParam = noteText.getLayoutParams();
        if(keyboard.isWhiteKey(keyboard.pressedKey))
            noteTextParam.width = PianoKeyboardView.WHITE_KEY_WIDTH;
        else
            noteTextParam.width = PianoKeyboardView.BLACK_KEY_WIDTH;
        noteText.setLayoutParams(noteTextParam);
        ViewAnimator animator = findViewById(R.id.animator);
        animator.clearAnimation();
        noteText.setText(PianoKeyboardView.keyNoteName.get(keyboard.pressedKey % 12));
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) noteText.getLayoutParams();
        params.leftMargin = keyboard.getXByKeyIndex();
        noteText.setLayoutParams(params);

        //是不是用线程来做，否则主UI卡住
//        IRSender.sendIRNote(this, keyboard.pressedKey);
    }

    private void notePressUp(){
        ViewAnimator animator = findViewById(R.id.animator);
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animator.startAnimation(animation);
        animator.showNext();
    }
}