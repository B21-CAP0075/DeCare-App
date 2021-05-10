package com.aemiralfath.decare.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.FragmentHomeBinding
import com.aemiralfath.decare.util.DummyBannerGenerator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val dummyUsername = "Dharma"

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

        setupHeader()
    }

    private fun setupHeader() {
        setupGreetingMessage()
        setupBanner()
    }

    private fun setupGreetingMessage() {
        val greeting =
            String.format(resources.getString(R.string.greeting_placeholder), dummyUsername)
        binding.layoutHeaderHome.tvGreetingHeaderHome.text = greeting
    }

    private fun setupBanner() {
        // setup banner
        val listBanner = DummyBannerGenerator.generateDummyBanner()
        val bannerAdapter = BannerAdapter()
        bannerAdapter.setBanners(listBanner)

        val vp = binding.layoutHeaderHome.vpBannerHeaderHome
        val indicators = binding.layoutHeaderHome.indicatorBannerHeaderHome

        vp.adapter = bannerAdapter
        indicators.setViewPager(vp)
    }

}