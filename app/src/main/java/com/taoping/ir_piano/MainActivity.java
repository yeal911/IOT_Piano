package com.taoping.ir_piano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private PianoKeyboardView keyboard;
    private TextView noteText;
    private TextView ipText;
    private TextView noteCoverText;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        @SuppressLint("UseSwitchCompatOrMaterialCode") final Switch transmissionSwitch = findViewById(R.id.switchWifiIR);
        final Button searchIPBtn = (Button) findViewById(R.id.searchReceiverBtn);
        final Button switchModeBtn = (Button) findViewById(R.id.switchBtn);
        keyboard = (PianoKeyboardView) findViewById(R.id.piano_keyboard_view);
        noteText = (TextView) findViewById(R.id.noteText);
        ipText = (TextView) findViewById(R.id.ipText);
        noteCoverText = (TextView) findViewById(R.id.coverTextView);
        RadioGroup toneCoverageRadio = (RadioGroup) findViewById(R.id.toneRadioGroup);
        transmissionSwitch.setOnClickListener(view -> {
            if(transmissionSwitch.isChecked()){
                transmissionSwitch.setText("   IR");
                ipText.setText("IR Sensor");
                searchIPBtn.setVisibility(View.INVISIBLE);
                showMessage("send notes via IR");
            }else{
                transmissionSwitch.setText("Wifi");
                ipText.setText(ReceiverSearcher.receiverIP + ":8888");
                searchIPBtn.setVisibility(View.VISIBLE);
                showMessage("send notes via WIFI");
            }
        });

        switchModeBtn.setOnClickListener(v -> {
            switchMode();
            // Perform action on click
            showMessage("Mode switched.");
        });

        searchIPBtn.setOnClickListener(v -> {
            searchReceiverIP();
            // Perform action on click
            showMessage("Searching for IP receiver...");
        });

        noteCoverText.setOnTouchListener((view, motionEvent) -> {
            return true;
        });
        keyboard.setOnTouchListener((view, motionEvent) -> {
            keyboard.pianoOnTouch(motionEvent);
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && keyboard.pressedKey != -1)
                notePressDown();
            else if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                notePressUp();
            return true;
        });

        toneCoverageRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 // checkedId is the RadioButton selected
                if(checkedId == R.id.lowToneRadioButton){
                    NoteQueue.keyboardToneLevel = "LOW";
                    coverUnreachableKeys();
                    showMessage("low tone keyboard.");
//                    Log.d("radiobutton", "LOW");
                }else if(checkedId == R.id.midToneRadioButton){
                    NoteQueue.keyboardToneLevel = "MID";
                    coverUnreachableKeys();
                    showMessage("middle tone keyboard.");
//                    Log.d("radiobutton", "MID");
                }else{
                    NoteQueue.keyboardToneLevel = "HIG";
                    coverUnreachableKeys();
                    showMessage("high tone keyboard.");
//                    Log.d("radiobutton", "HIG");
                }
            }});
    }

    //
    private void searchReceiverIP() {
        try {
            ReceiverSearcher.searchReceiver(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchMode(){
//        IRSender.switchMode(this);
    }

    public void setIpText(String ip){
        this.ipText.setText(ip);
        Looper.prepare();
        if(ip.startsWith("No"))
            showMessage("No IP receiver found.");
        else
            showMessage("Found a IP receiver " + ip);
        Looper.loop();
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
        NoteQueue.addNote(keyboard.pressedKey);
        //是不是用线程来做，否则主UI卡住
//        IRSender.sendIRNote(this, keyboard.pressedKey);
    }

    private void notePressUp(){
        ViewAnimator animator = findViewById(R.id.animator);
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animator.startAnimation(animation);
        animator.showNext();
    }

    private void showMessage(String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void coverUnreachableKeys(){
//        ViewGroup.LayoutParams coverTextParam = noteText.getLayoutParams();
//        coverTextParam.height = keyboard.getHeight();
//        coverTextParam.width = (keyboard.WHITE_KEY_WIDTH + 10) * 5;
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                (keyboard.WHITE_KEY_WIDTH + 10) * 5,
                keyboard.getHeight());
        params.leftToLeft = ConstraintLayout.LayoutParams.UNSET;
        params.rightToRight = ConstraintLayout.LayoutParams.UNSET;
        if(NoteQueue.keyboardToneLevel.equals("LOW")){
            noteCoverText.setVisibility(View.VISIBLE);
            params.leftToLeft = R.id.piano_keyboard_view;
            noteCoverText.setLayoutParams(params);
        }else if(NoteQueue.keyboardToneLevel.equals("HIG")){
            noteCoverText.setVisibility(View.VISIBLE);
            params.rightToRight = R.id.piano_keyboard_view;
            noteCoverText.setLayoutParams(params);
        }else{
            noteCoverText.setVisibility(View.INVISIBLE);
        }

    }
}