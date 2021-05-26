package com.aemiralfath.decare.ui.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.Resource
import com.aemiralfath.decare.databinding.ActivityYogaBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class YogaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYogaBinding

    private val viewModel: YogaViewModel by viewModel()
    private lateinit var yogaAdapter: YogaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYogaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()

        binding.btnNextYoga.setOnClickListener {
            if (binding.vpYoga.currentItem + 1 < yogaAdapter.itemCount) {
                binding.vpYoga.currentItem = binding.vpYoga.currentItem + 1
            }else {
                finish()
            }
        }
    }

    private fun setupViewPager() {
        yogaAdapter = YogaAdapter()

        viewModel.yogas.observe(this, {
            if (it != null) {
                when(it) {
                    is Resource.Error -> {
                        Log.d("YOGA", "ERROR")
                    }
                    is Resource.Loading -> {
                        Log.d("YOGA", "LOADING")
                    }
                    is Resource.Success -> {
                        Log.d("YOGA", "SUCCESS")
                        it.data?.let { yogas -> yogaAdapter.setYoga(yogas) }
                    }
                }
            }
        })

        binding.vpYoga.adapter = yogaAdapter

        binding.vpYoga.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setButtonState(position)
            }
        })
    }

    private fun setButtonState(index: Int) {
        if (index == yogaAdapter.itemCount - 1) {
            binding.btnNextYoga.text = resources.getString(R.string.finish_yoga)
        }
    }
}