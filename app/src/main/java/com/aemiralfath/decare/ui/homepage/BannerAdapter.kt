package com.aemiralfath.decare.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.databinding.ItemBannerBinding
import com.aemiralfath.decare.util.DummyBanner

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    private val listBanner = mutableListOf<DummyBanner>()

    fun setBanners(banners: List<DummyBanner>) {
        listBanner.clear()
        listBanner.addAll(banners)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerAdapter.ViewHolder {
        val view =
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerAdapter.ViewHolder, position: Int) {
        holder.bind(listBanner[position])
    }

    override fun getItemCount() = listBanner.size

    inner class ViewHolder(private val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: DummyBanner) {
            with(binding) {
                imgPhotoBanner.setImageResource(banner.photo)
            }
        }
    }
}