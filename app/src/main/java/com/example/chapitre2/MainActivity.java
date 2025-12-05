package com.example.chapitre2;


import static androidx.core.app.PendingIntentCompat.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chapitre2.databinding.ActivityMainBinding;
import com.example.chapitre2.ui.login.LoginActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView textView;
EditText greeting;
Button button;

ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
      ///  setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       /// textView = findViewById(R.id.greeting_text);
        textView = binding.greetingText;
      ///  greeting = findViewById(R.id.name_input);
        greeting = binding.nameInput;
      ///  button = findViewById(R.id.greet_button);
      ///  button = findViewById(R.id.greet_button);
        button = binding.greetButton;


    }

    public void greet(View view) {
        Intent intent = new Intent(this, ListContact.class);
        intent.putExtra("USER_ID", new Random().nextInt());
        intent.putExtra("USER_NAME", greeting.getText().toString());
        startActivity(intent);
        finish();
///
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", greeting.getText().toString());
        editor.apply();
       // Toast.makeText(this,"Hello Mr/Mrs: "+greeting.getText(),Toast.LENGTH_SHORT).show();
      /// textView.setText( textView.getText()+"\nHello Mr/Mrs: "+greeting.getText());
       Log.d("MainActivity","Hello Mr/Mrs: "+greeting.getText());
       Toast.makeText(this,"Hello Mr/Mrs: "+greeting.getText(),Toast.LENGTH_SHORT).show();
    }
}