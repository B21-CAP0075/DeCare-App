package com.aemiralfath.decare.ui.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aemiralfath.decare.R
import com.aemiralfath.decare.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}