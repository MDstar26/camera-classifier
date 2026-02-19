package com.example.aibrowser

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val urlInput = findViewById<EditText>(R.id.urlInput)
        val goButton = findViewById<Button>(R.id.goButton)
        webView = findViewById(R.id.webView)

        setupWebView()
        urlInput.setText("https://www.example.com")

        goButton.setOnClickListener {
            loadUrl(urlInput.text.toString())
        }

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_back -> {
                    if (webView.canGoBack()) webView.goBack()
                    true
                }

                R.id.action_forward -> {
                    if (webView.canGoForward()) webView.goForward()
                    true
                }

                R.id.action_ai -> {
                    showAiAssistSheet()
                    true
                }

                R.id.action_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    true
                }

                else -> false
            }
        }

        loadUrl(urlInput.text.toString())
    }

    private fun setupWebView() {
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
    }

    private fun loadUrl(raw: String) {
        val normalized = if (raw.startsWith("http://") || raw.startsWith("https://")) raw else "https://$raw"
        webView.loadUrl(normalized)
    }

    private fun showAiAssistSheet() {
        val dialog = BottomSheetDialog(this)
        val content = layoutInflater.inflate(R.layout.bottomsheet_ai_assist, null)
        dialog.setContentView(content)

        content.findViewById<Button>(R.id.startSelectionButton).setOnClickListener {
            Toast.makeText(
                this,
                "Selection overlay skeleton ready. Integrate GeckoView/PixelCopy in next step.",
                Toast.LENGTH_LONG
            ).show()
            dialog.dismiss()
        }

        content.findViewById<Button>(R.id.openSettingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            dialog.dismiss()
        }

        dialog.show()
    }
}
