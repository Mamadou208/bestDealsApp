package com.example.tb_laota.BestDeals.helper;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.util.Log;

import java.util.ArrayList;

public class RecognitionListenerImpl implements RecognitionListener {

    //We need a interface to preform a callback
    public interface Consumer<T> {
        void apply(T t);
    }

    private final Consumer<String> callback;

    public RecognitionListenerImpl(Consumer<String> callBack) {
        this.callback = callBack;
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> voiceResults = results.getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
        if (voiceResults == null) {
            Log.e(RecognitionListenerImpl.class.getName(), "No voice results");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String match : voiceResults) {
                sb.append(match).append("%20");
            }
            callback.apply(sb.toString());
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.d(RecognitionListenerImpl.class.getName(), "Ready for speech");
    }

    @Override
    public void onError(int error) {
        Log.d(RecognitionListenerImpl.class.getName(),
                "Error listening for speech: " + error);
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.d(RecognitionListenerImpl.class.getName(), "Speech starting");
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }
}
