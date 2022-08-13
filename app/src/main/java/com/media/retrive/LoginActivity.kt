package com.media.retrive

import android.app.Dialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.media.retrive.Model.User
import com.media.retrive.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var dialog: Dialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val uid =auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnSave.setOnClickListener {
            showProgressBar()
            val nom = binding.nom.text.toString()
            val prenom = binding.prenom.text.toString()
            val bio = binding.bio.text.toString()

            val user = User(nom,prenom,bio)
            if (uid != null){
                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){
                        uploadeProfil()

                    }else{
                        Toast.makeText(this, "Modification echouer", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }

    private fun uploadeProfil() {
        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.man}")
        storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri)
            .addOnSuccessListener {
                hideProgressbar()
            Toast.makeText(this, "image telecharger", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
                hideProgressbar()
            Toast.makeText(this, "image non telecharger", Toast.LENGTH_SHORT).show()
        }

    }
    private fun showProgressBar(){
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.dialogue_wait)
        dialog.show()
    }
    private fun hideProgressbar(){
        dialog.dismiss()
    }

}