package com.aemiralfath.decare.di

import androidx.room.Room
import com.aemiralfath.decare.data.DecareRepository
import com.aemiralfath.decare.data.source.local.LocalDataSource
import com.aemiralfath.decare.data.source.local.room.DecareDatabase
import com.aemiralfath.decare.data.source.remote.RemoteDataSource
import com.aemiralfath.decare.data.source.remote.network.DecareApiService
import com.aemiralfath.decare.ui.article.ArticleViewModel
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<DecareDatabase>().decareDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DecareDatabase::class.java,
            "decare.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://decare-g2k7tjwhia-uc.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        retrofit.create(DecareApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { DecareRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { ArticleViewModel(get()) }
}