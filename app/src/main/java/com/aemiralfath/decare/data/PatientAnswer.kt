package com.aemiralfath.decare.data

import android.graphics.Bitmap

/*
    tidak menyimpan jawaban nomor 3, 4, 5, 7, 8, dan 9 karena
    tidak akan disimpan dan langsung dinilai oleh sistem
 */
data class PatientAnswer(
    var firstAnswer: MutableList<String> = mutableListOf(),
    var secondAnswer: MutableList<String> = mutableListOf(),
    var sixthAnswer: MutableList<String> = mutableListOf(),
    var tenthAnswer: String = "",
    var eleventhAnswer: Bitmap = Bitmap.createBitmap(1, 1 , Bitmap.Config.ARGB_8888) // ini empty bitmap untuk nilai defaultnya
)
