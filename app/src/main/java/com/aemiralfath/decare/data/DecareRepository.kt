package com.aemiralfath.decare.data

import com.aemiralfath.decare.data.source.local.LocalDataSource
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.remote.RemoteDataSource
import com.aemiralfath.decare.data.source.remote.network.ApiResponse
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.util.ArticleMapper
import kotlinx.coroutines.flow.Flow

class DecareRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    fun getArticle(): Flow<Resource<List<ArticleEntity>>> =
        object : NetworkBoundResource<List<ArticleEntity>, ArticleResponse>() {
            override fun loadFromDB(): Flow<List<ArticleEntity>> {
                return localDataSource.getArticle()
            }

            override fun shouldFetch(data: List<ArticleEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<ArticleResponse>> {
                return remoteDataSource.getArticle()
            }

            override suspend fun saveCallResult(data: ArticleResponse) {
                val articleEntities = ArticleMapper.mapResponseToEntity(data)
                localDataSource.insertArticle(articleEntities)
            }

        }.asFlow()
}