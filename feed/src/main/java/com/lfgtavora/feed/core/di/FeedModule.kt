package com.lfgtavora.feed.core.di

import android.content.Context
import com.lfgtavora.feed.data.mapper.MoviesMapper
import com.lfgtavora.feed.domain.repository.IMovieRepository
import com.lfgtavora.feed.domain.repository.MoviesRepositoryImpl
import com.lfgtavora.feed.domain.usecase.IMoviesUsecase
import com.lfgtavora.feed.domain.usecase.MoviesUseCaseImpl
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.networking.di.ktorConfigurationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object FeedModule {

    var modules: List<Module> = listOf()
    lateinit var feedKoin: KoinApplication
        private set

    private fun feedKoinInit(context: Context) = koinApplication {
        androidLogger(Level.ERROR)
        androidContext(context)
        modules = listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            ktorConfigurationModule
        )
        koin.unloadModules(modules)
        modules(modules)
    }

    fun init(context: Context) {
        feedKoin = feedKoinInit(context)
    }

    fun get(): KoinApplication = feedKoin
}

val viewModelModule = module {
    viewModel { FeedViewModel(get()) }
}

val repositoryModule = module {
    single<IMovieRepository> { MoviesRepositoryImpl(get(), MoviesMapper()) }
}

val useCaseModule = module {
    single<IMoviesUsecase> { MoviesUseCaseImpl(get()) }
}