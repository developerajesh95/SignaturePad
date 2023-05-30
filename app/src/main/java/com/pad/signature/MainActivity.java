package com.pad.signature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pad.signature.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        setUpSignatureButton();
        setUpBarcodeScanButton();
    }

    private void setUpSignatureButton(){
        mainBinding.btnSignature.setOnClickListener(view -> gotToSignatureActivity());
    }

    private void setUpBarcodeScanButton(){
        mainBinding.btnBarcode.setOnClickListener(view -> gotToBarcodeActivity());
    }

    private void gotToSignatureActivity(){
        Intent signatureIntent = new Intent(this, SignatureActivity.class);
        startActivity(signatureIntent);
    }

    private void gotToBarcodeActivity(){
        Intent barcodeIntent = new Intent(this, BarcodeScanActivity.class);
        startActivity(barcodeIntent);
    }
}