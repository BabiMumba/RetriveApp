package com.media.retrive.ggl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.media.retrive.R
import kotlinx.android.synthetic.main.google_authentification.*

class GoogleAuth : AppCompatActivity() {
    private lateinit var mAut: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 120
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_authentification)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAut = FirebaseAuth.getInstance()
        sign_in.setOnClickListener {
            sign_in()
        }
    }

    private fun sign_in() {
        val signiIntent = googleSignInClient.signInIntent
        startActivityForResult(signiIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful){
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("se connecter","firebase authentification"+account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                }catch (e:Exception){


                }

            }else{

            }
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        mAut.signInWithCredential(credential)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){

                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                }else{

                }
            }

    }
}