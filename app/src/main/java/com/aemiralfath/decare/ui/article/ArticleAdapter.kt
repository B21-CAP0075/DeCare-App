package com.aemiralfath.decare.ui.article

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aemiralfath.decare.R
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.databinding.ItemArticleBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val listArticle = ArrayList<ArticleEntity>()

    fun setData(articles: List<ArticleEntity>) {
        if (articles.isEmpty()) return
        listArticle.clear()
        listArticle.addAll(articles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.bind(listArticle[position])
    }

    override fun getItemCount() = listArticle.size

    inner class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {

            with(binding) {
                Glide.with(root.context)
                    .load(article.banner)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPhotoItemArticle)

                tvTitleItemArticle.text = article.title
                tvDateItemArticle.text = article.date

                itemView.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(article.link)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}