package com.example.bitcamp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


// HOME PAGE
class MainActivity : AppCompatActivity() {
    private lateinit var resources: Button
    private lateinit var find_tool:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resources = findViewById(R.id.resources)
        find_tool = findViewById(R.id.`when`)

        find_tool.setOnClickListener {
            var intent: Intent = Intent(this, MissedPeriod::class.java)
            this.startActivity(intent)

        }

        resources.setOnClickListener {
            var intent: Intent = Intent(this, ResourceMap::class.java)
            this.startActivity(intent)

        }
    }
}