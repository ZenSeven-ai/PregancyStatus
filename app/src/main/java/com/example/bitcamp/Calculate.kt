package com.example.bitcamp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Date

class Calculate: AppCompatActivity() {


    private var day_choose:Int = 0
    private var month_choose:Int = 0
    private var year_choose:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculate)

        day_choose = intent.getIntExtra("Day", 0)
        month_choose = intent.getIntExtra("Month", 0)
        year_choose = intent.getIntExtra("Year", 0)

        var calendar:Calendar = Calendar.getInstance()
        val curr_date:Calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, day_choose)
        calendar.set(Calendar.MONTH, month_choose)
        calendar.set(Calendar.YEAR, year_choose)

        calendar.add(Calendar.DAY_OF_WEEK, 1)
        var first_day:Calendar = calendar
        calendar.add(Calendar.DAY_OF_WEEK, 7)
        var second_day:Calendar = calendar



        if(!first_day.after(curr_date) && !second_day.after(curr_date)){
            // both days are after todays date
            Log.w("CMSC", "You can take the test as soon as today$curr_date for best accuracy!")


        } else if (first_day.before(curr_date) && second_day.after(curr_date)){
            // the second day is after todays date
            Log.w("CMSC", "You can take the test as soon as today ${curr_date.toString()}, however for best results you should " +
                    "take it again 7 days after your missed period on ${second_day.toString()}")


        } else {
            // neither are after todays date
            Log.w("CMSC", "You can take the test on ${curr_date.toString()}, for the best accuracy you should take it again" +
                    "7 days after your missed period on ${second_day.toString()}")

        }











    }
}