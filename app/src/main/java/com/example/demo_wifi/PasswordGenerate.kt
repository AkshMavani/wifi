package com.example.demo_wifi

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demo_wifi.databinding.ActivityPasswordGenerateBinding


class PasswordGenerate : AppCompatActivity() {
    private lateinit var binding: com.example.demo_wifi.databinding.ActivityPasswordGenerateBinding
    var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordGenerateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tl2)
        var count:Int=1

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val passwordGenerate=Pwdgenerate()

        binding.txtPlus.setOnClickListener {
          for(i in count.toString()){
              if (count<=1){
                 break
              }
              else{
              count--
              binding.txtnum.text=count.toString()
              }
          }

        }
        binding.txtMinus.setOnClickListener {
            count++
            binding.txtnum.text=count.toString()
        }

        binding.txtpwdgenerate.setOnClickListener {
            try {
                 password= passwordGenerate.generateRandomPassword(count,binding.ReminderUpperCase.isChecked,binding.ReminderLowercase.isChecked,binding.ReminderNumber.isChecked,binding.ReminderSymbol.isChecked)!!
                binding.txtpwd.text=password
                Log.e("password", "onCreate:$password ")
            }catch (e:Exception){
                Toast.makeText(this,"select one switch",Toast.LENGTH_LONG).show()
            }
        }
        binding.igpaste.setOnClickListener {
            val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label.toString(), password.toString())
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this,"Password copy",Toast.LENGTH_LONG).show()
        }
    }
}