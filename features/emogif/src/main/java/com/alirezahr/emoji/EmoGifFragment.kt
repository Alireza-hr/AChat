package com.alirezahr.emoji

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.alirezahr.emoji.Common.contentView
import com.alirezahr.emoji.core.emoji.EmojiProvider
import com.alirezahr.emoji.core.emoji.EmojiSelectListener
import com.alirezahr.emoji.core.emoji.gif.GifProvider
import com.alirezahr.emoji.core.emoji.gif.GifSelectListener


class EmoGifFragment : Fragment() {

    private var emojiSelectListener: EmojiSelectListener? = null
    val TAG_EMOTICON_FRAGMENT = "tag_emoticon_fragment"
    val TAG_GIF_FRAGMENT = "tag_gif_fragment"
    private val TAG_EMOTICON_SEARCH_FRAGMENT = "tag_emoticon_search_fragment"
    private val TAG_GIF_SEARCH_FRAGMENT = "tag_gif_search_fragment"
    private val KEY_CURRENT_FRAGMENT = "current_fragment"
    private var isEmotjisEnable = false
    private var isGifsEnable = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = contentView {
        MainScreen()
    }

    @Preview
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            val context = LocalContext.current
            Spacer()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            emojiSelectListener?.onBackPressSpace()

                            val keyEvent = KeyEvent(
                                0,
                                0,
                                0,
                                KeyEvent.KEYCODE_DEL,
                                0,
                                0,
                                0,
                                0,
                                KeyEvent.KEYCODE_ENDCALL
                            )
                            (context as Activity).dispatchKeyEvent(keyEvent)
                        },
                    painter = painterResource(id = R.drawable.emoji_x),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(), Alignment.TopCenter
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.ic_smiley),
                            contentDescription = null
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_gif),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Spacer() {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(color = MaterialTheme.colors.background)
        )
    }

    class EmojiConfig {
        internal var emojiProvider: EmojiProvider? = null

        internal var emojiSelectListener: EmojiSelectListener? = null

        constructor()
        constructor(
            emojiProvider: EmojiProvider?,
            emojiSelectListener: EmojiSelectListener?
        ) {
            this.emojiProvider = emojiProvider
            this.emojiSelectListener = emojiSelectListener
        }

        fun setEmojiSelectListener(emoticonSelectListener: EmojiSelectListener?): EmojiConfig {
            emojiSelectListener = emoticonSelectListener
            return this
        }

        fun setEmojiProvider(emojiProvider: EmojiProvider?): EmojiConfig {
            this.emojiProvider = emojiProvider
            return this
        }
    }

    class GIFConfig(val gifProvider: GifProvider) {

        internal var gifSelectListener: GifSelectListener? = null

        fun setGifSelectListener(gifSelectListener: GifSelectListener?): GIFConfig {
            this.gifSelectListener = gifSelectListener
            return this
        }
    }

    fun setEmojiProvider(emojiProvider: EmojiProvider?) {
//        emojiFragment.setEmoticonProvider(emoticonProvider)
//        mEmoticonSearchFragment.setEmoticonProvider(emoticonProvider)
    }

    fun setEmoticonSelectListener(emojiSelectListener: EmojiSelectListener?) {
//        mEmoticonSelectListener = emoticonSelectListener
//        mEmoticonFragment.setEmoticonSelectListener(emoticonSelectListener)
//        mEmoticonSearchFragment.setEmoticonSelectListener(emoticonSelectListener)
    }


    fun setEmojiSelectListener(emojiSelectListener: EmojiSelectListener?) {
//        this.emojiSelectListener = emojiSelectListener
//        mEmoticonFragment.setEmoticonSelectListener(emojiSelectListener)
//        mEmoticonSearchFragment.setEmoticonSelectListener(emojiSelectListener)
    }


    companion object {
        fun newInstance(emojiConfig: EmojiConfig?, gifConfig: GIFConfig?): EmoGifFragment {
            if (emojiConfig == null && gifConfig == null) {
                throw IllegalStateException("must be one of emotion or gif should be active")
            }

            return EmoGifFragment().apply {
                if (emojiConfig != null) {
                    this.isEmotjisEnable = true
                    this.setEmojiProvider(emojiConfig.emojiProvider)
                    this.setEmojiSelectListener(emojiConfig.emojiSelectListener)
                }

                if (gifConfig != null) {
                    this.isGifsEnable = true
                    this.setGifProvider(gifConfig.gifProvider)
                    this.setGifListener(gifConfig.gifSelectListener)
                }
            }
        }
    }

    private fun setGifListener(gifSelectListener: GifSelectListener?) {
//        mGifFragment.setGifSelectListener(gifSelectListener)
//        mGifSearchFragment.setGifSelectListener(gifSelectListener)
    }

    private fun setGifProvider(gifProvider: GifProvider) {
//        mGifFragment.setGifProvider(gifProvider)
//        mGifSearchFragment.setGifProvider(gifProvider)
    }
}