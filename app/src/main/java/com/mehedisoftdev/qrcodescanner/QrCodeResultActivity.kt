package com.mehedisoftdev.qrcodescanner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mehedisoftdev.qrcodescanner.databinding.ActivityQrCodeResultBinding

class QrCodeResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrCodeResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrCodeResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultText = intent.getStringExtra("RESULT") ?: "System error"
        binding.result.text = resultText
    }
}