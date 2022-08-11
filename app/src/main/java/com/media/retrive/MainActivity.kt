package com.media.retrive

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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

        ReadFirestore()
    }

    private fun delete(ch1:EditText,ch2:EditText){
        ch1.setText("")
        ch2.setText("")
    }
    private fun saveFirestore(nom: String, prenom: String) {
        ReadFirestore()
        if (firstname.text.toString().isEmpty() || lastname.text.toString().isEmpty()){
            Toast.makeText(this, "Champs requis", Toast.LENGTH_SHORT).show()
        }
        else{
            progressDialog.show()
            var db = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["nom"] = nom
            user["prenom"] = prenom
            db.collection("users")
                .add(user)
                .addOnSuccessListener {

                    progressDialog.dismiss()
                    Toast.makeText(this, "Enregistres avec succer", Toast.LENGTH_SHORT).show()
                    delete(firstname,lastname)
                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Enregistres echouer", Toast.LENGTH_SHORT).show()

                }

        }




    }
    private fun ReadFirestore(){
        var db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener {
                val result:StringBuffer = StringBuffer()
                if (it.isSuccessful){
                    for (document in it.result ){

                        result.append(document.data.getValue("nom")).append("")
                            .append(document.data.getValue("prenom")).append("\n\n")
                    }
                    txtResult.setText(result)
                }
            }
    }
}