package com.example.weatherapp

import android.Manifest.permission
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.fragments.Fragment_Welcome
import com.example.weatherapp.viewModel.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : AppCompatActivity() {

    private val ACCESS_FINE_LOCATION_PERMISSION = 1
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = FusedLocationProviderClient(this)
        weatherViewModel = this.run { ViewModelProvider(this).get(WeatherViewModel::class.java) }
        // weatherViewModel.loadBloodBankList(91.2, 23.79)
        Check_Permmission();
    }

    fun firstScreen() {
        //Fragment Initlization
        val fragmentWelcome = Fragment_Welcome()
        val firstFragmentManager = supportFragmentManager
        val firstFragmentTransaction = firstFragmentManager.beginTransaction()
        firstFragmentTransaction.replace(
                R.id.mainActivity,
                fragmentWelcome,
                "WelCome"
        ).addToBackStack(null).commit()
    }


    fun getLocation() {
        if (ActivityCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Check_Permmission();
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                println("Location= " + location.latitude)
                val latitdue = location.latitude
                val longitude = location.longitude

                weatherViewModel.loadBloodBankList(longitude, latitdue)
            }
        }
    }


        fun Check_Permmission() {
            if (ContextCompat.checkSelfPermission(
                            this,
                            permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
            ) {
                getLocation();
                // weatherViewModel.loadBloodBankList(91.2, 23.79)
                RunThread()
                // Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_SHORT).show();
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.CAMERA)) {
                    AlertDialog.Builder(this)
                            .setTitle("Permission needed")
                            .setMessage("Camera permission is needed")
                            .setPositiveButton(
                                    "Ok"
                            ) { dialogInterface, i ->
                                ActivityCompat.requestPermissions(
                                        this@MainActivity,
                                        arrayOf(permission.ACCESS_FINE_LOCATION),
                                        ACCESS_FINE_LOCATION_PERMISSION
                                )
                            }
                            .create().show()
                } else {
                    ActivityCompat.requestPermissions(
                            this,
                            arrayOf(permission.ACCESS_FINE_LOCATION),
                            ACCESS_FINE_LOCATION_PERMISSION
                    )
                }
            }
        }


        override fun onRequestPermissionsResult(
                requestCode: Int,
                permissions: Array<String?>,
                grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == ACCESS_FINE_LOCATION_PERMISSION) {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT).show()
                    RunThread()

                } else {
                    finish()
                    System.exit(0)
                    Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
                    ActivityCompat.requestPermissions(
                            this,
                            arrayOf(permission.CAMERA),
                            ACCESS_FINE_LOCATION_PERMISSION
                    )
                }
            }
        }

        private fun RunThread() {
            val timer: Thread = object : Thread() {
                override fun run() {
                    try {
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    } finally {
                        firstScreen()
                    }
                    super.run()
                }
            }
            timer.start()
        }



}

