package com.example.demo_wifi

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_wifi.databinding.ActivityAboutAppBinding


class AboutApp : AppCompatActivity() {
    private lateinit var binding: ActivityAboutAppBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAboutAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tl5)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.wb.loadUrl("https://www.geeksforgeeks.org")
        binding.wb.settings.javaScriptEnabled = true
        binding.wb.webViewClient = WebViewClient()
    }
}