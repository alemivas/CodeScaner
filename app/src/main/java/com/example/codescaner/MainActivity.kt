package com.example.codescaner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.codescaner.ui.theme.CodeScanerTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {

    private val scanLauncher = registerForActivityResult(
        ScanContract()
    ) { result ->
        if (result.contents == null) {

        } else {
            Toast.makeText(
                this,
                "Scan data: ${result.contents}", Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeScanerTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            scan()
                        }
                    ) {
                        Text(text = "Scan")
                    }
                }
            }
        }
    }

    private fun scan() {
        val options = ScanOptions()
//        options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        options.setPrompt("Scan a barcode")
        options.setCameraId(0) // Use a specific camera of the device
        options.setBeepEnabled(true)
        options.setBarcodeImageEnabled(true)
        scanLauncher.launch(options)
    }
}