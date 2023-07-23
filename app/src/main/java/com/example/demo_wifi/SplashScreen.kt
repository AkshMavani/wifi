package com.example.demo_wifi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_wifi.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {
    var progressStatus = 0
    var handler = Handler()
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread {
            while (progressStatus < 100) {
                progressStatus += 1
                handler.post {
                    binding.progressBar2.progress = progressStatus
                    binding.tvprogress.text = "$progressStatus%"

                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    Log.e("Exception is", "onCreate:$e " )
                }
            }
            if (progressStatus == 100) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }

        }.start()

           }
       }