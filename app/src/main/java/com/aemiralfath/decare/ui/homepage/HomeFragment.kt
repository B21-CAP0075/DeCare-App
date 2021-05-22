package com.aemiralfath.decare.ui.homepage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentHomeBinding
import com.aemiralfath.decare.ui.login.LoginActivity
import com.aemiralfath.decare.util.DummyBannerGenerator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFirebase()
        setupHeader()

        binding.imgUserProfileHeaderHome.setOnClickListener {
            signOut()
        }

        onFeatureClickListener()
    }

    private fun setupFirebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()

        signInClient = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun setupHeader() {
        setupGreetingMessage()
        setupBanner()
    }

    private fun setupGreetingMessage() {
        val user = firebaseAuth.currentUser
        val greeting =
            String.format(resources.getString(R.string.greeting_placeholder), user?.displayName)
        binding.tvGreetingHeaderHome.text = greeting
    }

    private fun setupBanner() {
        val listBanner = DummyBannerGenerator.generateDummyBanner()
        val bannerAdapter = BannerAdapter()
        bannerAdapter.setBanners(listBanner)

        val vp = binding.vpBannerHeaderHome
        val indicators = binding.indicatorBannerHeaderHome

        vp.adapter = bannerAdapter
        indicators.setViewPager(vp)
    }

    private fun signOut() {
        firebaseAuth.signOut()
        signInClient.signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
        activity?.onBackPressed()
    }

    private fun onFeatureClickListener() {
        with(binding) {
//            cardEarlyDetectionFeatureHome.setOnClickListener {
//                startActivity(Intent(binding.root.context, EarlyDetectionActivity::class.java))
//            }
//
//            cardScheduleFeatureHome.setOnClickListener {
//
//            }
//
//            cardExerciseFeatureHome.setOnClickListener {
//
//            }
//
//            cardRecapitulationFeatureHome.setOnClickListener {
//
//            }
//
//            cardGuideFeatureHome.setOnClickListener {
//
//            }
        }
    }

}