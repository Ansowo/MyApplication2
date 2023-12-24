package com.example.myapplication2


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("cb379ab9-c564-41ca-bd72-3e4e63a2a243")
        MapKitFactory.initialize(this)
        setContentView(R.layout.mapview)
        mapView = findViewById(R.id.mapView)
        mapView.map.move(
            CameraPosition(Point(56.850001, 53.205517), 13.0f, 8.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5f),
            null
        )
        var mapKit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()
        var locationonmapkit = mapKit.createUserLocationLayer(mapView.mapWindow)
        locationonmapkit.isVisible = true


    }

    fun toProfile(view: View){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }



    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
            return
        }
    }



    /*override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }*/
}