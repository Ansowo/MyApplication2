package com.example.myapplication2

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.myapplication2.ui.theme.MyApplication2Theme
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import android.Manifest;
import android.content.pm.PackageManager
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.map.MapLoadStatistics


@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {

    private lateinit var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("cb379ab9-c564-41ca-bd72-3e4e63a2a243")
        MapKitFactory.initialize(this)
        setContentView(R.layout.mapview)
        mapView = findViewById(R.id.mapView)
        mapView.map.move(CameraPosition(Point(56.850001, 53.205517), 13.0f, 8.0f, 0.0f), Animation(Animation.Type.SMOOTH, 5f),null)
        var mapKit: MapKit = MapKitFactory.getInstance()
        requestLocationPermission()
        var locationonmapkit = mapKit.createUserLocationLayer(mapView.mapWindow)
        locationonmapkit.isVisible = true
    }


    private fun requestLocationPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            return
        }
    }


    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication2Theme {
        Greeting("Android")
    }
}