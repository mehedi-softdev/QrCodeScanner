package com.mehedisoftdev.qrcodescanner

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.mehedisoftdev.qrcodescanner.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.barcodeScanner.statusView.text = "Place barcode inside rectangle shape."

        // Check for camera permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.CAMERA), 1)
        } else {
            binding.barcodeScanner.decodeContinuous(callback)
        }
    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult) {
            // Handle the result here
            val qrCodeText = result.text
            val intent = Intent(this@MainActivity, QrCodeResultActivity::class.java)
            intent.putExtra("RESULT", qrCodeText);
            startActivity(intent)
        }

        override fun possibleResultPoints(resultPoints: List<com.google.zxing.ResultPoint>) {
            // Do nothing
        }
    }

    override fun onResume() {
        super.onResume()
        binding.barcodeScanner.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.barcodeScanner.pause()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.barcodeScanner.decodeContinuous(callback)
        } else {
            // Handle permission denial
            Toast.makeText(this@MainActivity, "Camera Permission denied.", Toast.LENGTH_SHORT)
                .show()
        }
    }


}