package com.example.bitcamp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar


// SELECT DATE FOR UNPROTECTED SEX
class DontKnowPeriod:AppCompatActivity() {

    private lateinit var date: Button
    private lateinit var calculate: Button
    private var day_choose:Int = 0
    private var month_choose:Int = 0
    private var year_choose:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dont_know_period)

        date = findViewById(R.id.select_date)
        calculate = findViewById(R.id.calculate)

        date.setOnClickListener {
            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)

            // set the max day to today
            val maxDay = day
            val maxMonth = month
            val maxYear = year


            val datePickerDialog = DatePickerDialog(
                this,{ view, year, month, day ->
                    day_choose = day
                    month_choose = month
                    year_choose = year



                },
                year,
                month,
                day
            )

            datePickerDialog.show()
            c.set(maxYear, maxMonth-1, maxDay)
            datePickerDialog.datePicker.maxDate = c.timeInMillis



        }


        calculate.setOnClickListener {
            // make sure they actually select something to go to the next activity
            if(year_choose!=0){
                var intent: Intent = Intent(this, Calculate::class.java)
                intent.putExtra("Month", month_choose)
                intent.putExtra("Year", year_choose)
                intent.putExtra("Day", day_choose)

                intent.putExtra("Situation", "DontKnowPeriod")
                this.startActivity(intent)

            }



        }



    }
}