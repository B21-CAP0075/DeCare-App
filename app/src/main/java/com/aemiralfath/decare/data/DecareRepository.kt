package com.aemiralfath.decare.data

import com.aemiralfath.decare.data.source.local.LocalDataSource
import com.aemiralfath.decare.data.source.local.entity.ArticleEntity
import com.aemiralfath.decare.data.source.local.entity.PredictionEntity
import com.aemiralfath.decare.data.source.remote.RemoteDataSource
import com.aemiralfath.decare.data.source.remote.network.ApiResponse
import com.aemiralfath.decare.data.source.remote.response.article.ArticleResponse
import com.aemiralfath.decare.util.Mapper
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

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
                val articleEntities = Mapper.mapResponseToEntityArticle(data)
                localDataSource.insertArticle(articleEntities)
            }

        }.asFlow()

    fun getPrediction(json: JsonObject): Flow<Resource<PredictionEntity>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = remoteDataSource.getPrediction(json).first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(Mapper.mapResponseToEntityPrediction(apiResponse.data)))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<PredictionEntity>(apiResponse.errorMessage))
            }
        }
    }
}