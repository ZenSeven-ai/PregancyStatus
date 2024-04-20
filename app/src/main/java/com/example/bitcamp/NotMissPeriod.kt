package com.example.bitcamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NotMissPeriod:AppCompatActivity() {

    private lateinit var home:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.not_miss_period)

        home = findViewById(R.id.home)

        home.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)

        }


    }

}