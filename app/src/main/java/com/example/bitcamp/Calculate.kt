package com.example.bitcamp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar


// CALCULATES DAY TO TAKE TEST
class Calculate: AppCompatActivity() {


    private lateinit var home:Button
    private lateinit var tv:TextView
    private lateinit var tvasap:TextView
    private lateinit var tvaccur:TextView
    private var day_choose:Int = 0
    private var month_choose:Int = 0
    private var year_choose:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate)

        home = findViewById(R.id.home)
        tv = findViewById(R.id.results)
        tvasap = findViewById(R.id.tvasap)
        tvaccur = findViewById(R.id.tvaccurate)


        home.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        day_choose = intent.getIntExtra("Day", 0)
        month_choose = intent.getIntExtra("Month", 0)
        year_choose = intent.getIntExtra("Year", 0)

        var calendar:Calendar = Calendar.getInstance()
        val curr_date:Calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_MONTH, day_choose)
        calendar.set(Calendar.MONTH, month_choose)
        calendar.set(Calendar.YEAR, year_choose)

        val monthdate = SimpleDateFormat("MMM dd, yyyy")
        var curr_date_string:String = monthdate.format(curr_date.getTime())


        if(intent.getStringExtra("Situation").equals("MissedPeriod")){


            calendar.add(Calendar.DAY_OF_WEEK, 1)
            var first_day:Calendar = calendar.clone() as Calendar
            var first_day_string:String = monthdate.format(first_day.getTime())



            calendar.add(Calendar.DAY_OF_WEEK, 7)
            var second_day:Calendar = calendar
            var second_day_string:String = monthdate.format(second_day.getTime())




            var text:String = ""
            if(first_day.before(curr_date) && second_day.before(curr_date)){


                tvasap.text = curr_date_string
                tvaccur.text = curr_date_string


            } else if (first_day.before(curr_date) && second_day.after(curr_date)){
                // the second day is after todays date and the first is before


                tvasap.text = curr_date_string
                tvaccur.text = second_day_string



            } else {
                // neither are after todays date


                tvasap.text = first_day_string
                tvaccur.text = second_day_string



            }

        } else {

            calendar.add(Calendar.DAY_OF_WEEK, 21)
            var twentyone_day:Calendar = calendar.clone() as Calendar
            var twentyone_day_string:String = monthdate.format(twentyone_day.getTime())

            var text:String = ""



            if(twentyone_day.before(curr_date)){
                // before todays date


                tvasap.text = curr_date_string
                tvaccur.text = curr_date_string



            } else {


                tvasap.text = twentyone_day_string
                tvaccur.text = twentyone_day_string

                // not before todays date




            }


        }



    }
}