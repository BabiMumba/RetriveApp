package com.media.retrive.ggl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.media.retrive.R

class GoogleAuth : AppCompatActivity() {
    private lateinit var mAut:FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object{
        private const val RC_SIGN_IN = 120
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_authentification)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        mAut = FirebaseAuth.getInstance()
    }
    private fun signinn(){
        val signiIntent = googleSignInClient.signInIntent
        startActivityForResult(signiIntent, RC_SIGN_IN)
    }
}