package com.example.bitcamp

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Education : AppCompatActivity() {

    lateinit var front_anim:AnimatorSet
    lateinit var back_anim: AnimatorSet
    lateinit var home:Button
    var isFront1 =true
    var isFront2 =true
    var isFront3 =true
    var isFront4 =true
    var isFront5 = true
    var isFront6 = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.education)

        home = findViewById(R.id.home)

        // we now need to modify the camera scale
        var scale = applicationContext.resources.displayMetrics.density

        val front1 = findViewById<TextView>(R.id.card_front_1) as TextView
        val back1 =findViewById<TextView>(R.id.card_back_1) as TextView

        val front2 = findViewById<TextView>(R.id.card_front_2) as TextView
        val back2 =findViewById<TextView>(R.id.card_back_2) as TextView

        val front3 = findViewById<TextView>(R.id.card_front_3) as TextView
        val back3 =findViewById<TextView>(R.id.card_back_3) as TextView

        val front4 = findViewById<TextView>(R.id.card_front_4) as TextView
        val back4 =findViewById<TextView>(R.id.card_back_4) as TextView


        val front5 = findViewById<TextView>(R.id.card_front_5) as TextView
        val back5 =findViewById<TextView>(R.id.card_back_5) as TextView


        val front6 = findViewById<TextView>(R.id.card_front_6) as TextView
        val back6 =findViewById<TextView>(R.id.card_back_6) as TextView


        var listfront = listOf(front1, front2, front3, front4, front5, front6)
        var listback = listOf(back1, back2, back3, back4, back5, back6)
        var isFront = mutableListOf(isFront1, isFront2, isFront3, isFront4, isFront5, isFront6)

        // Now we will set the front animation
        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.animator.back_animator) as AnimatorSet

        home.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)



        }



        for(i in 0..5){
            listfront[i].cameraDistance = 8000 * scale
            listback[i].cameraDistance = 8000 * scale

            listfront[i].setOnClickListener{
                if(isFront[i])
                {
                    front_anim.setTarget(listfront[i]);
                    back_anim.setTarget(listback[i]);
                    front_anim.start()
                    back_anim.start()
                    isFront[i] = false

                }
                else
                {
                    front_anim.setTarget(listback[i])
                    back_anim.setTarget(listfront[i])
                    back_anim.start()
                    front_anim.start()
                    isFront[i] =true

                }
            }


            listback[i].setOnClickListener{
                if(isFront[i])
                {
                    front_anim.setTarget(listfront[i]);
                    back_anim.setTarget(listback[i]);
                    front_anim.start()
                    back_anim.start()
                    isFront[i] = false

                }
                else
                {
                    front_anim.setTarget(listback[i])
                    back_anim.setTarget(listfront[i])
                    back_anim.start()
                    front_anim.start()
                    isFront[i] =true

                }
            }

        }





    }
}