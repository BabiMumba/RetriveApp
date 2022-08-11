package com.media.retrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save_btn.setOnClickListener {
            var nom = firstname.text.toString()
            var prenom = lastname.text.toString()

            saveFirestore(nom,prenom )

        }
        

    }

    private fun saveFirestore(firstname: String, lastname: String?) {

    }
}