package com.lfgtavora.movie_detail.core.di

import android.content.Context
import com.lfgtavora.movie_detail.data.mapper.MovieDetailMapper
import com.lfgtavora.movie_detail.domain.repository.MovieDetailRepositoryImpl
import com.lfgtavora.movie_detail.domain.usecase.IMovieDetailUseCase
import com.lfgtavora.movie_detail.domain.usecase.MovieDetailUseCaseImpl
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

object MovieDetailModule {

    lateinit var movieDetailKoin: KoinApplication
        private set

    var modules = listOf<Module>()

    private fun movieDetailKoinInit(context: Context) = koinApplication {
        androidLogger(Level.ERROR)
        androidContext(context)
        modules = listOf(
            useCaseModule,
            repositoryModule,
            viewModelModule
        )
        koin.unloadModules(modules)
        modules(modules)
    }

    fun init(context: Context) {
        movieDetailKoin = movieDetailKoinInit(context)
    }

}

val viewModelModule = module {
    viewModel { MovieDetailViewModel(get()) }
}

val repositoryModule = module {
    single<MovieDetailRepositoryImpl> { MovieDetailRepositoryImpl(MovieDetailMapper()) }
}

val useCaseModule = module {
    single<IMovieDetailUseCase> { MovieDetailUseCaseImpl(get()) }
}