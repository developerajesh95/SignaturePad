package com.pad.signature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.pad.signature.databinding.ActivityBarcodeScanBinding;

public class BarcodeScanActivity extends AppCompatActivity {

    private ActivityBarcodeScanBinding barcodeScanBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barcodeScanBinding = ActivityBarcodeScanBinding.inflate(getLayoutInflater());
        View view = barcodeScanBinding.getRoot();
        setContentView(view);
    }
}