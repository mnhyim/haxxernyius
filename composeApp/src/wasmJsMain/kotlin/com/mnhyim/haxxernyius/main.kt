package com.mnhyim.haxxernyius

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.mnhyim.haxxernyius.di.initKoin
import com.mnhyim.haxxernyius.ui.home.Home
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()
    ComposeViewport(document.body!!) {
        Home()
    }
}