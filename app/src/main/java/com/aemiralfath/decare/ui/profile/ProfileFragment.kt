package com.aemiralfath.decare.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentProfileBinding
import com.aemiralfath.decare.ui.login.LoginActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFirebase()

        val user = firebaseAuth.currentUser
        binding.tvProfileName.text = user?.displayName
        binding.tvProfileEmail.text = user?.email

        binding.btnSignOut.setOnClickListener {
            signOut()
        }

        Glide.with(view).load(user?.photoUrl)
            .placeholder(R.drawable.ic_baseline_person_24)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.imgProfilePhoto)
    }

    private fun setupFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        signInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun signOut() {
        firebaseAuth.signOut()
        signInClient.signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
        activity?.finish()
    }


}