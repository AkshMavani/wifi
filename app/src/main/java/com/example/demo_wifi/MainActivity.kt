package com.example.demo_wifi

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_wifi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }
    fun init(){
        binding.constraintLayout.setOnClickListener {
            val i=Intent(this, List::class.java)
            startActivity(i)
        }
        binding.constraintLayout1.setOnClickListener {
            val i=Intent(this,PasswordGenerate::class.java)
            startActivity(i)
        }
        binding.constraintLayout2.setOnClickListener {
            val i=Intent(this,SignalStrength::class.java)
            startActivity(i)
        }
        binding.constraintLayout5.setOnClickListener {
            val i=Intent(this,WifiInformation::class.java)
            startActivity(i)
        }
        binding.clAboutApp.setOnClickListener {
            val i=Intent(this,AboutApp::class.java)
            startActivity(i)
        }
    }
    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this,R.style.MyDialogTheme)
        //builder.setMessage("Do you want to exit ?")
        builder.setMessage(Html.fromHtml("<font color='#FF7F27'>Do you want to exit ?</font>"))
        builder.setTitle(Html.fromHtml("<font color='#FF7F27'>Alert</font>"))
        builder.setIcon(R.drawable.close)
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
            finish()
        }
        builder.setNegativeButton("No") { dialog: DialogInterface, which: Int ->
            dialog.cancel()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}