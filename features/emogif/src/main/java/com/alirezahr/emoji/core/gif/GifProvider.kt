package com.alirezahr.emoji.core.emoji.gif

import androidx.annotation.WorkerThread


interface GifProvider {

    fun getTrendingGifs(limit: Int): List<Gif?>?

    fun searchGifs(limit: Int, query: String): List<Gif?>?
}