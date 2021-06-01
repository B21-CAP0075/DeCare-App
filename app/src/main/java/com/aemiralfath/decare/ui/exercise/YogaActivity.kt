package com.aemiralfath.decare.ui.exercise

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
            } else {
                finish()
            }
        }
    }

    private fun setupViewPager() {
        yogaAdapter = YogaAdapter()

        viewModel.yogas.observe(this, {
            if (it != null) {
                when (it) {
                    is Resource.Error -> {
                        Log.d("YOGA", "ERROR")
                        showLoading(false)
                        showError(true)
                    }
                    is Resource.Loading -> {
                        Log.d("YOGA", "LOADING")
                        showLoading(true)
                        showError(false)
                    }
                    is Resource.Success -> {
                        Log.d("YOGA", "SUCCESS")
                        showLoading(false)
                        showError(false)
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.vpYoga.visibility = View.GONE
            binding.progressBarYoga.visibility = View.VISIBLE
        } else {
            binding.vpYoga.visibility = View.VISIBLE
            binding.progressBarYoga.visibility = View.GONE
        }
    }

    private fun showError(state: Boolean) {
        if (state) {
            binding.vpYoga.visibility = View.GONE
            binding.viewEmpty.root.visibility = View.VISIBLE
        } else {
            binding.vpYoga.visibility = View.VISIBLE
            binding.viewEmpty.root.visibility = View.GONE
        }
    }
}