package com.example.assigment1_1211089;

        import android.os.Bundle;
        import android.speech.tts.TextToSpeech;
        import android.widget.RadioGroup;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });

        final RadioGroup colorRadioGroup = findViewById(R.id.colorRadioGroup);
        colorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String colorName = "";
                switch (checkedId) {
                    case R.id.redRadioButton:
                        colorName = "Red";
                        break;
                    case R.id.greenRadioButton:
                        colorName = "Green";
                        break;
                    case R.id.blueRadioButton:
                        colorName = "Blue";
                        break;
                    case R.id.yellowRadioButton:
                        colorName = "Yellow";
                        break;
                    case R.id.orangeRadioButton:
                        colorName = "Orange";
                        break;
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
