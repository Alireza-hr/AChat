package com.alirezahr.emoji.core.emoji

interface EmojiSelectListener {
    fun emoticonSelected(emoticon: Emoji?)

    fun onBackPressSpace()
}
