package com.pad.signature;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pad.signature.databinding.ActivitySignatureBinding;

public class SignatureActivity extends AppCompatActivity {

    private ActivitySignatureBinding signatureBinding;
    private SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signatureBinding = ActivitySignatureBinding.inflate(getLayoutInflater());
        View view = signatureBinding.getRoot();
        setContentView(view);

        signatureView = new SignatureView(this);
        signatureBinding.frameLayoutContainer.addView(signatureView);

        signatureBinding.btnSign.setOnClickListener(v -> {
            Bitmap bitmap = SignatureView.loadBitmapFromView(signatureBinding.frameLayoutContainer);
            signatureBinding.ivImage.setImageBitmap(bitmap);
        });

        signatureBinding.btnClear.setOnClickListener(v -> {
            signatureView.clear();
            signatureBinding.ivImage.setImageBitmap(null);
        });

        signatureBinding.btnSave.setOnClickListener(v -> Toast.makeText(this, "Sorry :)", Toast.LENGTH_SHORT).show());
    }
}