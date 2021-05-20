package com.aemiralfath.decare.util

import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse

object ArticleMapper {
    fun mapResponseToEntity(input: ArticleResponse) : List<ArticleEntity> {
        val entities = ArrayList<ArticleEntity>()
        input.map {
            val articleEntity = ArticleEntity(
                id = it.id,
                banner = it.banner,
                date = it.date,
                link = it.link,
                title = it.title
            )
            entities.add(articleEntity)
        }
        return entities
    }
}