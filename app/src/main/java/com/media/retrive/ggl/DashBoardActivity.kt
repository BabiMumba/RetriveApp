package com.media.retrive.ggl

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.media.retrive.AdmodActivity
import com.media.retrive.R
import kotlinx.android.synthetic.main.activity_dash.*

class DashBoardActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        id_user.text = currentUser?.uid
        name_user.text = currentUser?.displayName
        email_user.text = currentUser?.email

        Glide.with(this)
            .load(currentUser?.photoUrl)
            .placeholder(R.drawable.idea)
            .error(R.drawable.man)
            .into(image_user)


        signOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this,GoogleAuth::class.java)
            startActivity(intent)
            finish()
        }
        Native.setOnClickListener {
            Intent(this,AdmodActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}