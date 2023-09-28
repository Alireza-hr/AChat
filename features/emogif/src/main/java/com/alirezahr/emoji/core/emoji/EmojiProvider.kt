package com.alirezahr.emoji.core.emoji

import androidx.annotation.DrawableRes


interface EmojiProvider {

    @DrawableRes
    fun getIcon(unicode: String?): Int

    fun hasEmojiIcon(unicode: String?): Boolean
}