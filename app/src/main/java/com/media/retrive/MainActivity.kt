package com.media.retrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nom = firstname.text.toString()
        var prenom = lastname.text.toString()

        save_btn.setOnClickListener {
            saveFirestore(nom,prenom )

            firstname.setText("")
            lastname.setText("")


        }
        

    }

    private fun saveFirestore(firstname: String, lastname: String) {

        var db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["nom"] = firstname
        user["prenom"] = lastname
        db.collection("users")
            .add(user)
            .addOnSuccessListener {

                Toast.makeText(this, "Enregistres avec succer", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Enregistres echouer", Toast.LENGTH_SHORT).show()

            }
    }
}