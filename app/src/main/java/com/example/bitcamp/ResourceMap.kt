package com.example.bitcamp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

class ResourceMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{
    private lateinit var map : GoogleMap
    private lateinit var target: Marker
    private lateinit var sephora: Marker



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.resource_map)
        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map_fragment ) as SupportMapFragment
        fragment.getMapAsync( this )

    }

    override fun onMapReady(p0: GoogleMap) {

        Log.w( "MainActivity", "Inside onMapReady" )
        map = p0


        val builder = LatLngBounds.Builder()
            var latLng = LatLng(38.987320,-76.944656)
            var options : CircleOptions = CircleOptions( )
            options.center( latLng )
            options.radius( 100.0 )
            options.strokeWidth( 10.0f )
            options.strokeColor( Color.RED )
            map.addCircle( options )
            builder.include(latLng)
            target = map.addMarker( MarkerOptions( ).position( latLng ).title( "UMD Health Center") )!!




            var latLng2 = LatLng(38.987530,-76.941370)
            var options2 : CircleOptions = CircleOptions( )
            options2.center( latLng2 )
            options2.radius( 100.0 )
            options2.strokeWidth( 10.0f )
            options2.strokeColor( Color.RED )
            map.addCircle( options2 )
            sephora = map.addMarker( MarkerOptions( ).position( latLng2 ).title( "UMD Help Center") )!!
            builder.include(latLng2)



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

        if(marker.id == target.id){
            Log.w("Marker","Target was pressed")
            target_message.show()
        } else if(marker.id == sephora.id) {
            Log.w("Marker", "Sephora was pressed")
            sephora_message.show()
        }

    }




}