package com.example.bitcamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


// HAVE YOU MISSED YOUR PERIOD
class MissedPeriod : AppCompatActivity() {

    private lateinit var yes:Button
    private lateinit var no:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.missed_period)

        yes = findViewById(R.id.yes)
        no = findViewById(R.id.no)

        no.setOnClickListener {

            var intent: Intent = Intent(this, KnowPeriod::class.java)
            this.startActivity(intent)
        }

        yes.setOnClickListener {
            var intent: Intent = Intent(this, DayMissed::class.java)
            this.startActivity(intent)
        }
    }
}