package com.media.retrive

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("loading in...")
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)

        save_btn.setOnClickListener {
            var nom = firstname.text.toString()
            var prenom = lastname.text.toString()
            saveFirestore(nom,prenom )

        }
        

    }

    private fun saveFirestore(firstname: String, lastname: String) {


        if (firstname.isEmpty() || lastname.isEmpty()){
            Toast.makeText(this, "Champs requis", Toast.LENGTH_SHORT).show()
        }
        else{

            sendInfo()

        }

    }

    private fun sendInfo() {
        progressDialog.show()
        var db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["nom"] = firstname
        user["prenom"] = lastname
        db.collection("users")

            .add(user)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Enregistres avec succer", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Enregistres echouer", Toast.LENGTH_SHORT).show()

            }
    }
}