package com.media.retrive.ggl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.media.retrive.R

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        Handler().postDelayed({

            if (user != null){
                val dashbordIntent = Intent(this, DashBoardActivity::class.java)
                startActivity(dashbordIntent)
            }else{
                val signinIntent = Intent(this,GoogleAuth::class.java)
                startActivity(signinIntent)
            }

        },2000)

    }
}