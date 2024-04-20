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
    private lateinit var target: Marker
    private lateinit var sephora: Marker
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat : Double = 0.0
    private var long : Double = 0.0
    private lateinit var cvsApi: String
    private lateinit var parentApi : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.resource_map)

        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map_fragment ) as SupportMapFragment
        fragment.getMapAsync( this )

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
    }


    override fun onMapReady(p0: GoogleMap) {
        Log.w( "MainActivity", "Inside onMapReady" )
        map = p0
        // ask permissions
        cvsApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$lat,$long&radius=20000&keyword=cvs&key=AIzaSyA3W1FbsGloJqirmFGUUCDrazn-gv5rw9k"
        parentApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$lat,$long&radius=20000&keyword=planned_parenthood|cvs&key=AIzaSyA3W1FbsGloJqirmFGUUCDrazn-gv5rw9k"
        val builder = LatLngBounds.Builder()

        val client = OkHttpClient()
        Log.i("STOPHERE2",lat.toString())
        val request = Request.Builder().url(cvsApi).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("Response", "Response from server")
                if(!response.isSuccessful){
                    Log.e("Error", "err")
                }else{
                    val body = response?.body?.string()
                    val jsonObject = JSONObject(body)
                    val jsonArray = jsonObject.getJSONArray("results")
                    if(jsonArray.length() > 0){
                        val res : JSONObject = jsonArray.getJSONObject(0)
                        // Extract the "name" field
                        val name = res.getString("name")

                        // Extract the "opening_hours" object
                        val openingHoursObject = res.optJSONObject("opening_hours")

                        // Extract the "open_now" field
                        val openNow = openingHoursObject?.optBoolean("open_now", false) ?: false

                        // Extract the "geometry" object
                        val geometryObject = res.optJSONObject("geometry")

                        // Extract the "location" object from the "geometry" object
                        val locationObject = geometryObject?.optJSONObject("location")

                        // Extract the "lat" and "lng" fields from the "location" object
                        val lat = locationObject?.optDouble("lat", 0.0) ?: 0.0
                        val lng = locationObject?.optDouble("lng", 0.0) ?: 0.0
                        Log.i("JSON Data", "Name: $name")
                        Log.i("JSON Data", "Open Now: $openNow")
                        Log.i("JSON Data", "Latitude: $lat")
                        Log.i("JSON Data", "Longitude: $lng")
                    }


                }
            }

        })

        val requestTwo = Request.Builder().url(cvsApi).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("Response", "Response from server")
                if(!response.isSuccessful){
                    Log.e("Error", "err")
                }else{
                    val body = response?.body?.string()
                    Log.i("Test", body.toString())

                }
            }

        })



        var latLng = LatLng(38.987169759522544,-76.9446136468915)
        var options : CircleOptions = CircleOptions( )
        options.center( latLng )
        options.radius( 10.0 )
        options.strokeWidth( 5.0f )
        options.strokeColor( Color.RED )
        map.addCircle( options )
        builder.include(latLng)
        target = map.addMarker( MarkerOptions( ).position( latLng ).title( "UMD Health Center") )!!



        var latLng2 = LatLng(38.98316814959617,-76.94369150379234)
        var options2 : CircleOptions = CircleOptions( )
        options2.center( latLng2 )
        options2.radius( 10.0 )
        options2.strokeWidth( 5.0f )
        options2.strokeColor( Color.RED )
        map.addCircle( options2 )
        sephora = map.addMarker( MarkerOptions( ).position( latLng2 ).title( "UMD Help Center") )!!
        builder.include(latLng2)

        val bounds = builder.build()


        var camera : CameraUpdate = CameraUpdateFactory.newLatLngBounds( bounds, 100)
        map.moveCamera( camera )


        p0.setOnInfoWindowClickListener(this)
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1001)

        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations, this can be null.
                if (location != null) {
                    // Do something with the location
                    lat = location.latitude
                    long = location.longitude
                } else {
                    // Location is null, handle accordingly
                }
            }
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

        if(marker.id == target.id){
            Log.w("Marker","Target was pressed")
            target_message.show()
        } else if(marker.id == sephora.id) {
            Log.w("Marker", "Sephora was pressed")
            sephora_message.show()
        }

    }


}