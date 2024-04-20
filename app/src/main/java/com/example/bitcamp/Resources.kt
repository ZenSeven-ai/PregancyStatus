package com.example.bitcamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Resources:AppCompatActivity() {

    private lateinit var education:Button
    private lateinit var places:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resources)

        education = findViewById(R.id.education)
        places = findViewById(R.id.map)

        education.setOnClickListener {
            var intent: Intent = Intent(this, Education::class.java)
            this.startActivity(intent)

        }

        places.setOnClickListener {
            var intent: Intent = Intent(this, ResourceMap::class.java)
            this.startActivity(intent)


        }
    }
}