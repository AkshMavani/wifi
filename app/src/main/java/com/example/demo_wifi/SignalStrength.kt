package com.example.demo_wifi

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlin.collections.List

class SignalStrength : AppCompatActivity() {
    private lateinit var binding: com.example.demo_wifi.databinding.ActivitySignalStrengthBinding
    private var wifiManager: WifiManager? = null
    private val arrayList = ArrayList<WifiList>()
    private var arr=ArrayList<WifiList>()
    private var signalLevel = 0
    private var q:Int = 0
    private val necessaryPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION)
    private var PERMISSION_ALL = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signal_strength)
        binding = com.example.demo_wifi.databinding.ActivitySignalStrengthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tl3)
        binding.LstRc2.layoutManager= LinearLayoutManager(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        if (!wifiManager!!.isWifiEnabled) {
            Toast.makeText(applicationContext, "Wifi is disabled..Making it enabled.", Toast.LENGTH_LONG).show()
            wifiManager!!.isWifiEnabled = true
        }


        if (hasPermissions(necessaryPermissions)) {
            scanWifi()
        } else {
            ActivityCompat.requestPermissions(this, necessaryPermissions,this.PERMISSION_ALL )
        }
    }
    fun hasPermissions(permissions: Array<String>?): Boolean {
        if (permissions != null) {
            for (p in permissions) {
                if (ActivityCompat.checkSelfPermission(this, p) !== PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        scanWifi()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            scanWifi()
        } else {
            showMessage("Permissions is not granted")
        }
    }
    fun scanWifi() {
        arrayList.clear()
        registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager!!.startScan()
        showMessage("Scanning...")
    }
    val wifiReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                arr.clear()
                val mScanResults: List<ScanResult> = wifiManager!!.scanResults
                for (scanResult in mScanResults) {
                    signalLevel = WifiManager.calculateSignalLevel(scanResult.level, 5)
                    Log.e("Freqancy", "onReceive:${signalLevel}")
                    if(signalLevel==4){
                        q=R.drawable.wabc
                    }
                    else if (signalLevel==3){
                        q=R.drawable.wifi1
                    }
                    else if (signalLevel==2){
                        q=R.drawable.wifi2
                    }
                    else{
                        q=R.drawable.wifi3
                    }
                    arr.add(WifiList(q,scanResult.SSID,signalLevel.toString()))

                }
            }
            Log.e("List is", "onReceive:$arr")
            val adapter=Rc(arr)
            binding.LstRc2.adapter=adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }
    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}