package com.aemiralfath.decare.util

import com.aemiralfath.decare.R

object DummyBannerGenerator {
    fun generateDummyBanner() : List<DummyBanner> {
        return mutableListOf<DummyBanner>().apply {
            add(DummyBanner(R.color.teal_200))
            add(DummyBanner(R.color.teal_700))
            add(DummyBanner(R.color.purple_200))
        }
    }
}