package com.aemiralfath.decare.util

import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.PredictionEntity
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.data.source.remote.response.prediction.PredictionResponse

object Mapper {
    fun mapResponseToEntityArticle(input: ArticleResponse): List<ArticleEntity> {
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

    fun mapResponseToEntityPrediction(input: PredictionResponse): PredictionEntity =
        PredictionEntity(
            prediction = input.prediction,
            confident = input.confident
        )

}