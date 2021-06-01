package com.aemiralfath.decare.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.data.model.Banner
import com.aemiralfath.decare.databinding.ItemBannerBinding

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    private val listBanner = mutableListOf<Banner>()

    fun setBanners(banners: List<Banner>) {
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

    inner class ViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner) {
            with(binding) {
                imgPhotoBanner.setImageResource(banner.background)
                binding.tvTitleItemBanner.text = banner.title
                binding.tvBodyItemBanner.text = banner.body
            }
        }
    }
}