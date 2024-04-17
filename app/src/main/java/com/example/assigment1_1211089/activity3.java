package com.example.assigment1_1211089; // Corrected package declaration

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class activity3 extends AppCompatActivity {
    TextToSpeech tts;
    private RadioButton redRadioButton;
    private RadioButton greenRadioButton;
    private RadioButton blueRadioButton;
    private RadioButton yellowRadioButton;
    private RadioButton orangeRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english); // Corrected layout reference

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });

        // Initialize RadioButtons
        RadioButton[] radioButtons = new RadioButton[5];
        int[] radioButtonIds = {R.id.redRadioButton, R.id.greenRadioButton, R.id.blueRadioButton, R.id.yellowRadioButton, R.id.orangeRadioButton};
        String[] colorNames = {"Red", "Green", "Blue", "Yellow", "Orange"};

        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = findViewById(radioButtonIds[i]);
        }

        final RadioGroup colorRadioGroup = findViewById(R.id.radioGroup); // Corrected to use the correct ID
        colorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String colorName = "";
                for (int i = 0; i < radioButtons.length; i++) {
                    if (checkedId == radioButtonIds[i]) {
                        colorName = colorNames[i];
                        break;
                    }
                }
                // Speak color name
                tts.speak(colorName, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
