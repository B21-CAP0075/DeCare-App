package com.aemiralfath.decare.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.ActivityLoginBinding
import com.aemiralfath.decare.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN: Int = 9001
        private const val TAG: String = "LoginActivity"
    }

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signInButton.setOnClickListener {
            signIn()
        }

        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build()

        signInClient = GoogleSignIn.getClient(this, gso)

        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                } else {
                    Log.w(TAG, "Google sign in failed Null")
                }
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoole:" + account.id)
        val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)


        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                binding.progressBar.visibility = View.GONE
                Log.d(TAG, "signInWithCredential:success")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener(this) {
                Log.w(TAG, "signInWithCredential", it)
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    this@LoginActivity, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    private fun signIn() {
        binding.progressBar.visibility = View.VISIBLE
        val signInIntent: Intent = signInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
}