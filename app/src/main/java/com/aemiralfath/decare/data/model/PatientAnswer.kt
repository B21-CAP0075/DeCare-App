package com.aemiralfath.decare.data.model

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

/*
    tidak menyimpan jawaban nomor 3, 4, 5, 7, 8, dan 9 karena
    tidak akan disimpan dan langsung dinilai oleh sistem
 */
data class PatientAnswer(
    var firstAnswer: MutableList<String> = mutableListOf(),
    var secondAnswer: MutableList<String> = mutableListOf(),
    var sixthAnswer: MutableList<String> = mutableListOf(),
    var tenthAnswer: String = "",
    var eleventhAnswer:  Drawable = ColorDrawable(Color.TRANSPARENT)
)
