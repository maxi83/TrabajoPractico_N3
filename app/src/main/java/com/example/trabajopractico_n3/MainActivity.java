package com.example.trabajopractico_n3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText monto1;
    private EditText monto2;
    private EditText resultado;
    private RadioButton dolEuro;
    private RadioButton eurDolar;
    private Button convertir;
    private MainActivityViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        inicializarVista();

        vm.getActivoActEditexDolar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                monto1.setEnabled(aBoolean);
                monto1.requestFocus();
            }
        });
        vm.getActivoActEditexEuro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                monto2.setEnabled(aBoolean);
                monto2.requestFocus();
            }
        });
        vm.getResulMutable().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                convertir.setText(s);
            }
        });

    }


    public void convertir(View v) {
        vm.convertir(convertir.isClickable(), monto1.getText().toString(), monto2.getText().toString());
    }
    private void inicializarVista() {
        monto1 = findViewById(R.id.monto1);
        monto2 = findViewById(R.id.monto2);
        dolEuro = findViewById(R.id.dolEuro);
        eurDolar = findViewById(R.id.eurDolar);
        resultado = findViewById(R.id.resultado);
        convertir = findViewById(R.id.convertir);

        monto1.requestFocus();

    }
}