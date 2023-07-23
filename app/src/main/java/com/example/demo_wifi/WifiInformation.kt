package com.example.demo_wifi

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.List


class WifiInformation : AppCompatActivity() {
    private lateinit var binding: com.example.demo_wifi.databinding.ActivityWifiInformationBinding
    private var wifiManager: WifiManager? = null
    private val necessaryPermissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_information)
        binding = com.example.demo_wifi.databinding.ActivityWifiInformationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.tl4)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
      wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager!!.connectionInfo
        val ssid = wifiInfo.ssid
        val ip=wifiInfo.ipAddress
        val macadd=wifiInfo.macAddress
        val ls=wifiInfo.linkSpeed
        val rss=wifiInfo.rssi
        val rlp=wifiInfo.rxLinkSpeedMbps/60
        binding.txtssid.text = ": $ssid"
        binding.txtip.text = ": $ip"
        binding.txtmacaddress.text = ": $macadd"
        binding.txtlinkspeed.text = ": $ls"
        binding.txtrss.text = ": $rss"
     binding.txtnetworkspeed.text= ":$rlp"
        Log.e("ssid", "ssid:$ssid ")
        Log.e("ip", "ip:$ip ")
        Log.e("macadd", "macadd:$macadd ")
        Log.e("ls", "ls:$ls ")
        Log.e("rss", "rss:$rss ")
        Log.e("rlp", "ssid:$ssid ")

        val mScanResults: List<ScanResult> = wifiManager!!.scanResults
        for (scanResult in mScanResults) {
           binding.txtcfl.text=  ": "+scanResult.frequency.toString()
        }

    }
}