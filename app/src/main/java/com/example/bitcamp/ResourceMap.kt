package com.example.bitcamp

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.Manifest
import android.content.Intent
import android.location.Location
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
import org.json.JSONObject
import java.io.IOException
import java.io.ObjectInput


class ResourceMap : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{
    private lateinit var map : GoogleMap
    private lateinit var health: Marker
    private lateinit var help: Marker
    private lateinit var pp: Marker
    private lateinit var cvs: Marker
    private lateinit var home:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.resource_map)
        home = findViewById(R.id.home)

        var fragment : SupportMapFragment =
            supportFragmentManager.findFragmentById( R.id.map_fragment ) as SupportMapFragment
        fragment.getMapAsync( this )

        home.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }


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

        val latLng3 = LatLng(38.979921, -76.938317)
        val options3: CircleOptions = CircleOptions()
        options3.center(latLng3)
        options3.radius(10.0)
        options3.strokeWidth(5.0f)
        options3.strokeColor(Color.RED)
        map.addCircle(options3)
        cvs = map.addMarker(MarkerOptions().position(latLng3).title("CVS"))!!
        builder.include(latLng3)



        val latLng4 = LatLng(38.906721, -77.000495)
        val options4: CircleOptions = CircleOptions()
        options4.center(latLng4)
        options4.radius(10.0)
        options4.strokeWidth(5.0f)
        options4.strokeColor(Color.RED)
        map.addCircle(options4)
        pp = map.addMarker(MarkerOptions().position(latLng4).title("Planned Parenthood"))!!
        builder.include(latLng4)

        val bounds = builder.build()
        var camera : CameraUpdate = CameraUpdateFactory.newLatLngBounds( bounds, 100)


        map.moveCamera( camera )


        p0.setOnInfoWindowClickListener(this)



    }


    override fun onInfoWindowClick(marker: Marker) {

        val build: AlertDialog.Builder = AlertDialog.Builder(this)
        var help_string:String ="Address: South Campus Dining Hall, Room #3105, College Park, MD 20740 \n\nContact Information: 301-314-4357 \n\nHours: Mon-Thur 4PM-12AM, Fri 4PM-8PM \n\n" +
                "Services Provided:\n\u2022 Peer Counseling Hotline\n\u2022 Free Pregnancy Tests\n\u2022 Free Sexual Wellness Items"
        var health_string:String ="Address: 140 Campus Dr, College Park, MD 20742 \n\nContact Information: 301-314-8180\n\nHours: Mon-Fri 8AM-5PM, Sat 9AM-1PM\n\n" +
                "Services Provided:\n" +
                "• Free Emergency Contraception\n" +
                "• Free Birth Control Consultations\n" +
                "• Free Sexual Wellness Items\n" +
                "• Reproductive Health Consultations"
        var planned_string:String = "Address: 1225 4th St NE, Washington, DC 20002 \n\nContact Information: 202-347-8500\n\nHours: Sun-Mon 8AM-5:30PM, Tue-Wed 8AM-6:30PM, Thur-Sat 8AM-4:30PM\n\n" +
                "Services Provided:\n" +
                "• Birth Control Consultations\n" +
                "• Reproductive Health Consultations\n" +
                "• Abortion Services\n" +
                "• STI Testing and Treatment\n" +
                "• Pregnancy Testing and Planning\n"
        var cvs_string:String = "Address: 7300 Baltimore Ave, College Park, MD 20740 \n\nContact Information: 301-277-6114\n\nHours: 24/7\n\n" +
                "Services Provided:\n" +
                "• Birth Control Consultations\n" +
                "• STI Testing and Treatment\n" +
                "• Plan B One-Step Emergency Contraceptive Tablet ($50) \n"


        build.setMessage(health_string).setTitle("University of Maryland's Health Center")
            .setNegativeButton("Exit") { dialog, which ->
                //nothing
            }
        var health_message: AlertDialog = build.create()

        build.setMessage(help_string).setTitle("University of Maryland's Help Center")
            .setNegativeButton("Exit") { dialog, which ->
                // nothing
            }

        var help_message: AlertDialog = build.create()


        build.setMessage(planned_string).setTitle("Planned Parenthood")
            .setNegativeButton("Exit") { dialog, which ->
                //nothing
            }
        var planned_message: AlertDialog = build.create()

        build.setMessage(cvs_string).setTitle("CVS")
            .setNegativeButton("Exit") { dialog, which ->
                // nothing
            }

        var cvs_message: AlertDialog = build.create()

        if(marker.id == health.id){
            health_message.show()
        } else if(marker.id == help.id) {
            help_message.show()
        } else if(marker.id == cvs.id){
            cvs_message.show()
        } else if(marker.id == pp.id){
            planned_message.show()
        }

    }
}

