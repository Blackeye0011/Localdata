package com.example.a8thpro;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextAge;
    private Button buttonSave, buttonDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDisplay = findViewById(R.id.buttonDisplay);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayData();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String username = editTextUsername.getText().toString().trim();
        String ageStr = editTextAge.getText().toString().trim();

        if (username.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Please enter username and age", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);

        editor.putString("username", username);
        editor.putInt("age", age);
        editor.putBoolean("is_logged_in", true);

        editor.apply();

        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

        editTextUsername.setText("");
        editTextAge.setText("");
    }

    private void displayData() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");
        int age = sharedPreferences.getInt("age", 0);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);

        String displayText = "Username: " + username + "\nAge: " + age + "\nIs Logged In: " + isLoggedIn;
        Toast.makeText(this, displayText, Toast.LENGTH_LONG).show();

        Log.d("MainActivity", "Username: " + username);
        Log.d("MainActivity", "Age: " + age);
        Log.d("MainActivity", "Is Logged In: " + isLoggedIn);
    }
}
