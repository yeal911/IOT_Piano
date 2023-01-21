package com.taoping.record;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

public class VoiceAnalyzer {

    public static void recordAndDetect(Activity activity) {
//        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
//        PitchDetectionHandler pdh = new PitchDetectionHandler() {
//            @Override
//            public void handlePitch(PitchDetectionResult res, AudioEvent e) {
//                final float pitchInHz = res.getPitch();
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        processPitch(pitchInHz);
//                    }
//                });
//            }
//        };
//        AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
//        dispatcher.addAudioProcessor(pitchProcessor);
//
//        Thread audioThread = new Thread(dispatcher, "Audio Thread");
//        audioThread.start();
    }

    public static void processPitch(float pitchInHz) {
        Log.d("VoiceAnalyzer", "processPitch: " + pitchInHz);

        if (pitchInHz >= 110 && pitchInHz < 123.47) {
            //A
            Log.d("VoiceAnalyzer", "processPitch: A");
        } else if (pitchInHz >= 123.47 && pitchInHz < 130.81) {
            //B
            Log.d("VoiceAnalyzer", "processPitch: B");
        } else if (pitchInHz >= 130.81 && pitchInHz < 146.83) {
            //C
            Log.d("VoiceAnalyzer", "processPitch: C");
        } else if (pitchInHz >= 146.83 && pitchInHz < 164.81) {
            //D
            Log.d("VoiceAnalyzer", "processPitch: D");
        } else if (pitchInHz >= 164.81 && pitchInHz <= 174.61) {
            //E
            Log.d("VoiceAnalyzer", "processPitch: E");
        } else if (pitchInHz >= 174.61 && pitchInHz < 185) {
            //F
            Log.d("VoiceAnalyzer", "processPitch: F");
        } else if (pitchInHz >= 185 && pitchInHz < 196) {
            //G
            Log.d("VoiceAnalyzer", "processPitch: G");
        }
    }

//    private static final int SAMPLE_RATE = 44100;
//    private static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
//    private static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
//    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT);
//
//    private void startRecording() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, BUFFER_SIZE);
//        if (audioRecord.getState() != AudioRecord.STATE_INITIALIZED) {
//            // Do something when the initialization failed
//        } else {
//            audioRecord.startRecording();
//        }
//        TarsosDSPAudioFormat format = new TarsosDSPAudioFormat(SAMPLE_RATE, 16, 1, true, false);
//        PitchDetectionHandler pdh = new PitchDetectionHandler() {
//            @Override
//            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
//                final float pitchInHz = pitchDetectionResult.getPitch();
//                // Do something with the pitch here
//                System.out.println(pitchInHz);
//            }
//        };
//        PitchProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, SAMPLE_RATE, BUFFER_SIZE, pdh);
//
//
//        while (true) {
//            short[] buffer = new short[BUFFER_SIZE];
//            int result = audioRecord.read(buffer, 0, BUFFER_SIZE);
//            if (result < 0) {
//                throw new RuntimeException("Reading of audio buffer failed: " + getBufferReadFailureReason(result));
//            }
//            AudioEvent audioEvent = new AudioEvent(format);
//            audioEvent.s
//            pitchProcessor.process(audioEvent);
//        }
//    }
//
//    private String getBufferReadFailureReason(int errorCode) {
//        switch (errorCode) {
//            case AudioRecord.ERROR_INVALID_OPERATION:
//                return "ERROR_INVALID_OPERATION";
//            case AudioRecord.ERROR_BAD_VALUE:
//                return "ERROR_BAD_VALUE";
//            case AudioRecord.ERROR_DEAD_OBJECT:
//                return "ERROR_DEAD_OBJECT";
//            default:
//                return "Unknown (" + errorCode + ")";
//        }
//    }
}