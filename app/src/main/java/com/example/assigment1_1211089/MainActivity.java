package com.example.assigment1_1211089;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPassword;
    private EditText edtEmail;
    private RadioGroup languageRadioGroup;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPreferences();
    }

    private void setupViews() {
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        languageRadioGroup = findViewById(R.id.radioGroup);
        languageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1) {
                    // Launch activity2
                    Intent intent = new Intent(MainActivity.this, activity2.class);
                    startActivity(intent);
                } else if (checkedId == R.id.radioButton2) { // Changed to radioButton3 for RadioButton3
                    // Launch activity3
                    Intent intent = new Intent(MainActivity.this, activity3.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setupSharedPreferences() {
        prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void btnLoginOnClick(View view) {
        String name = edtName.getText().toString();
        String password = edtPassword.getText().toString();
        String email = edtEmail.getText().toString();

        // Dummy authentication (replace with your actual authentication logic)
        if (name.equals("fatima") && password.equals("fatimamurra")) {
            // Save credentials to shared preferences
            saveCredentials(name, password);
            // Show a welcome message
            clearFields();
            Toast.makeText(this, "Welcome, " + name + "!", Toast.LENGTH_SHORT).show();
        } else {
            // Show an error message if authentication fails
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCredentials(String name, String password) {
        editor.putString("name", name);
        editor.putString("password", password);
        editor.apply();
    }

    private void clearFields() {
        edtName.setText("");
        edtPassword.setText("");
        edtEmail.setText("");
    }
}
