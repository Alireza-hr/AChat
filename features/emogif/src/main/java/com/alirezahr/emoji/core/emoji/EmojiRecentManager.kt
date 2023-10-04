package com.alirezahr.emoji.core.emoji

import android.content.Context
import androidx.lifecycle.asLiveData
import com.alirezahr.emoji.core.PreferenceDataStoreManager
import com.alirezahr.emoji.core.db.EmojiCategories


class EmojiRecentManager(private val context: Context) {
    private val currentRecentEmoji: ArrayList<Emoji>? = null

    private var prefDataStore: PreferenceDataStoreManager = PreferenceDataStoreManager(context)

    init {
    }

    private suspend fun getLastCategory() {
        prefDataStore.getPreference(
            PreferenceDataStoreConstants.KEY_PREF_PAGE, EmojiCategories.RECENT
        )
    }

    private suspend fun setLastCategory(emojiCategory: CharCategory) {
        prefDataStore.putPreference(
            PreferenceDataStoreConstants.KEY_PREF_PAGE, emojiCategory.value
        )
    }

    private suspend fun getRecentEmojis(): ArrayList<Emoji> {
        val emojiRecentList = ArrayList<Emoji>()

        val recentEmoji =
            prefDataStore.getPreference(PreferenceDataStoreConstants.KEY_PREF_RECENT, "")

        val w = recentEmoji.asLiveData().value.toString().split("~")
        for (i in w.indices) {
            emojiRecentList.add(Emoji(w[i]))
        }
        return emojiRecentList
    }

    private suspend fun add(emoji: Emoji) {
        for (current: Emoji in currentRecentEmoji!!) {
            if (emoji == current) return
        }
        currentRecentEmoji.add(0, emoji)

        addEmojiToRecent()
    }

    private suspend fun addEmojiToRecent() {
        val stringBuilder = StringBuilder()
        val limit = Math.min(currentRecentEmoji!!.size, 100)

        for (i in 0..limit) {
            stringBuilder.append(currentRecentEmoji[i].unicode)
            if (i < (limit - 1)) stringBuilder.append('~')
        }

        prefDataStore.putPreference(
            PreferenceDataStoreConstants.KEY_PREF_RECENT,
            stringBuilder.toString()
        )
    }

    suspend fun deleteRecent() {
        prefDataStore.clearAllPreference<String>()
    }
}