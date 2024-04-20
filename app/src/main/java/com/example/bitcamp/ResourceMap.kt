package com.example.bitcamp

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.Manifest
import android.location.Location
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.io.ObjectInput


class ResourceMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{
    private lateinit var map : GoogleMap
    private lateinit var health: Marker
    private lateinit var help: Marker
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat : Double = 0.0
    private var long : Double = 0.0
    private lateinit var cvsApi: String
    private lateinit var parentApi : String
    private lateinit var cvsM: Marker
    private lateinit var parentM: Marker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.resource_map)

        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map_fragment ) as SupportMapFragment
        fragment.getMapAsync( this )


    }


    override fun onMapReady(p0: GoogleMap) {
        Log.w("MainActivity", "Inside onMapReady")
        map = p0

        var builder = LatLngBounds.builder()

        val latLng = LatLng(38.987169759522544, -76.9446136468915)
        val options: CircleOptions = CircleOptions()
        options.center(latLng)
        options.radius(10.0)
        options.strokeWidth(5.0f)
        options.strokeColor(Color.RED)
        map.addCircle(options)
        builder.include(latLng)
        health = map.addMarker(MarkerOptions().position(latLng).title("UMD Health Center"))!!

        val latLng2 = LatLng(38.98316814959617, -76.94369150379234)
        val options2: CircleOptions = CircleOptions()
        options2.center(latLng2)
        options2.radius(10.0)
        options2.strokeWidth(5.0f)
        options2.strokeColor(Color.RED)
        map.addCircle(options2)
        help = map.addMarker(MarkerOptions().position(latLng2).title("UMD Help Center"))!!
        builder.include(latLng2)

        val latLng3 = LatLng(38.998238, -76.914052)
        val options3: CircleOptions = CircleOptions()
        options3.center(latLng3)
        options3.radius(10.0)
        options3.strokeWidth(5.0f)
        options3.strokeColor(Color.RED)
        map.addCircle(options3)
        builder.include(latLng3)
        health = map.addMarker(MarkerOptions().position(latLng).title("CVS"))!!


        val latLng4 = LatLng(38.906721, -77.000495)
        val options4: CircleOptions = CircleOptions()
        options4.center(latLng4)
        options4.radius(10.0)
        options4.strokeWidth(5.0f)
        options4.strokeColor(Color.RED)
        map.addCircle(options4)
        builder.include(latLng4)
        health = map.addMarker(MarkerOptions().position(latLng).title("Planned Parenthood"))!!

        val bounds = builder.build()
        var camera : CameraUpdate = CameraUpdateFactory.newLatLngBounds( bounds, 100)
        map.moveCamera( camera )



        p0.setOnInfoWindowClickListener(this)



    }


    override fun onInfoWindowClick(marker: Marker) {

        val build: AlertDialog.Builder = AlertDialog.Builder(this)
        var targetProducts:String = ""
        var sephoraProducts:String = ""


        build.setMessage(targetProducts).setTitle("Products available at Target")
            .setNegativeButton("Exit") { dialog, which ->
                //nothing
            }
        var target_message: AlertDialog = build.create()

        build.setMessage(sephoraProducts).setTitle("Products available at Sephora")
            .setNegativeButton("Exit") { dialog, which ->
                // nothing
            }

        var sephora_message: AlertDialog = build.create()

        if(marker.id == health.id){
            Log.w("Marker","Target was pressed")
            target_message.show()
        } else if(marker.id == help.id) {
            Log.w("Marker", "Sephora was pressed")
            sephora_message.show()
        }

    }
}

