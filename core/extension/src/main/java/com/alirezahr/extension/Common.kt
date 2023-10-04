package com.alirezahr.extension

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

object Common {
    final fun Fragment.requireContentView(
        compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
        context: Context = requireContext(),
        content: @Composable () -> Unit
    ): ComposeView {
        return ComposeView(context).apply {
            setViewCompositionStrategy(compositionStrategy)
            setContent(content)
        }
    }

    final fun Fragment.contentView(
        compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
        context: Context? = getContext(),
        content: @Composable () -> Unit
    ): ComposeView? {
        context ?: return null
        return ComposeView(context).apply {
            setViewCompositionStrategy(compositionStrategy)
            setContent(content)
        }
    }
}