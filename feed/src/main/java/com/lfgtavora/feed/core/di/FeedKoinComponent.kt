package com.lfgtavora.feed.core.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface FeedKoinComponent: KoinComponent {
    override fun getKoin(): Koin = FeedModule.get().koin
}