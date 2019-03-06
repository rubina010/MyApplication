package com.example.myapplication.ui.dashboard

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard_view.*
import timber.log.Timber
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    @Inject
    lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mLocation: Location
    private lateinit var mLocationManager: LocationManager
    private lateinit var mLocationRequest: LocationRequest
    private var isPermissionGranted: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_view)
        initialize()
    }

    private fun initialize() {
        generate_button.setOnClickListener {
            Timber.i("this is called")
            dashboardViewModel.getWeather()
            dashboardViewModel.weatherData.observe(this, Observer {
                main.text = it[0].main
                description.text = it[0].description
                val iconUrl = "http://openweathermap.org/img/w/" + it[0].icon + ".png"
                //Glide.with(this).load(iconUrl).into(weather_icon)
                Glide.with(this).load("http://openweathermap.org/img/w/04d.png").into(weather_icon)
            })
        }
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        checkLocation()

    }

    private fun checkLocation(): Boolean {
        Timber.i("this is checklocation")
        if (!isLocationEnabled()) {
            showAlert()
        }
        return isLocationEnabled()
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun showAlert() {
        Timber.i("this is show alert")
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                .setTitle("Enable Location")
                .setMessage("Your Location Setting is set to off. please enable the settings")
                .setPositiveButton("Location Settings") { dialog, which ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
                .setNegativeButton("Cancel") { dialog, which -> }
        dialog.show()
    }

    override fun onLocationChanged(location: Location?) {
        Timber.i("this is locationchanged")
        Timber.i("location ${location!!.latitude} ${location!!.longitude}")
        latitude.text = location!!.latitude.toString()
        logtitude.text = location.longitude.toString()
    }

    override fun onConnected(p0: Bundle?) {
        Timber.i("this is onConnected")
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true
            return
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    , 0)
        }
        startLocationUpdates()
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        if (mLocation == null) {
            startLocationUpdates()
        }
        if (mLocation != null) {
            Timber.i("this is called")
        } else {
            Toast.makeText(this, "Location not detected", Toast.LENGTH_LONG)
        }
    }

    private fun startLocationUpdates() {
        Timber.i("this is startLocationupdates")
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(2 * 1000)
                .setFastestInterval(2000)
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION))
                != PackageManager.PERMISSION_GRANTED) {

        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        isPermissionGranted = false
        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    isPermissionGranted = true
                }
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Timber.e("error ${p0.errorMessage}")
    }

    override fun onConnectionSuspended(p0: Int) {
        mGoogleApiClient.connect()
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient.connect()
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient.isConnected)
            mGoogleApiClient.disconnect()
    }
}