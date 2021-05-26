package com.aemiralfath.decare.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.data.source.local.entity.YogaEntity
import com.aemiralfath.decare.databinding.ItemYogaBinding
import com.bumptech.glide.Glide

class YogaAdapter : RecyclerView.Adapter<YogaAdapter.ViewHolder>(){

    private val listYoga = ArrayList<YogaEntity>()

    fun setYoga(yogas: List<YogaEntity>) {
        if (yogas.isEmpty()) return
        listYoga.clear()
        listYoga.addAll(yogas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YogaAdapter.ViewHolder {
        val view = ItemYogaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: YogaAdapter.ViewHolder, position: Int) {
        holder.bind(listYoga[position])
    }

    override fun getItemCount() = listYoga.size

    inner class ViewHolder(val binding: ItemYogaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(yoga: YogaEntity) {
            Glide.with(binding.root.context)
                .load(yoga.gambar)
                .into(binding.imgPhotoItemYoga)
            binding.tvTitleItemYoga.text = yoga.namaPose
            binding.tvDescriptionItemYoga.text = yoga.deskripsi
        }
    }
}