package com.aemiralfath.decare.util

import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.model.Banner

object BannerGenerator {
    fun generateDummyBanner() : List<Banner> {
        return mutableListOf<Banner>().apply {
            add(Banner(R.drawable.banner_1, "Apa itu Demensia ?", "Demensia adalah penyakit yang mengakibatkan penurunan daya ingat dan cara berpikir"))
            add(Banner(R.drawable.banner_2, "DeCare hadir!", "Lakukan deteksi dini penyakit demensia dan tingkatkan kualitas hidup anda"))
            add(Banner(R.drawable.banner_3, "Siapkah untuk menggunakan DeCare?", "Yuk! Cek kesehatan kamu di DeCare"))
        }
    }
}