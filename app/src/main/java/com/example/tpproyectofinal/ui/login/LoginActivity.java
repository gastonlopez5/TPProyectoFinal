package com.example.tpproyectofinal.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tpproyectofinal.MainActivity;
import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.MainActivity;
import com.example.tpproyectofinal.R;
import com.example.tpproyectofinal.ui.inmuebles.InmueblesViewModel;

public class LoginActivity extends AppCompatActivity {
    private Button logeo;
    private EditText email;
    private EditText password;
    private TextView tvError;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);

        logeo = (Button)findViewById(R.id.button);
        email = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        tvError = findViewById(R.id.tvError);

        vm.getMsg1().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent logeo = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(logeo);
            }
        });

        vm.getMsg2().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });

        logeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.validar(email.getText().toString(), password.getText().toString());
            }
        });
    }



}
