package com.taoping.record;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.writer.WriterProcessor;

public class PitchAnalyzer {
    private static final AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);
    private static final TarsosDSPAudioFormat tarsosDSPAudioFormat = new TarsosDSPAudioFormat(TarsosDSPAudioFormat.Encoding.PCM_SIGNED, 22050, 2 * 8, 1, 2 * 1, 22050, ByteOrder.BIG_ENDIAN.equals(ByteOrder.nativeOrder()));

    public static void recordAndAnalyze() throws FileNotFoundException {
        String filename = "recorded_sound.wav";
        File sdCard = Environment.getExternalStorageDirectory();
        File file = new File(sdCard, filename);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
        AudioProcessor recordProcessor = new WriterProcessor(tarsosDSPAudioFormat, randomAccessFile);
        dispatcher.addAudioProcessor(recordProcessor);

//        PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {
//            @Override
//            public void handlePitch(PitchDetectionResult res, AudioEvent e){
//                final float pitchInHz = res.getPitch();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        pitchTextView.setText(pitchInHz + "");
//                    }
//                });
//            }
//        };
    }

}
