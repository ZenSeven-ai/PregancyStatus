package com.example.bitcamp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


// DO YOU KNOW WHEN YOUR PERIOD IS DUE?
class KnowPeriod:AppCompatActivity() {

    private lateinit var yes: Button
    private lateinit var no: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.know_period)

        yes = findViewById(R.id.yes)
        no = findViewById(R.id.no)

        yes.setOnClickListener {

            var intent: Intent = Intent(this, NotMissPeriod::class.java)
            this.startActivity(intent)

        }


        no.setOnClickListener {

            var intent = Intent(this, DontKnowPeriod::class.java)
            this.startActivity(intent)
        }



    }
}