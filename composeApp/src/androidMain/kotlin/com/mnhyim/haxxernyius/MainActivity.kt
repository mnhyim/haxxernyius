package com.mnhyim.haxxernyius

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.ui.Modifier
import com.mnhyim.haxxernyius.theme.HaxxernyiusTheme
import com.mnhyim.haxxernyius.ui.home.Home

class MainActivity : ComponentActivity() {
    private var isDarkMode = false
    private val transparentBarStyle = SystemBarStyle.auto(
        lightScrim = Color.TRANSPARENT,
        darkScrim = Color.TRANSPARENT,
        detectDarkMode = { isDarkMode },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isDarkMode = isDarkThemeActive()

        enableEdgeToEdge(transparentBarStyle)
        setContent {
            HaxxernyiusTheme {
                Home(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val newIsDarkMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        if (isDarkMode != newIsDarkMode) {
            isDarkMode = newIsDarkMode
            enableEdgeToEdge()
        }
    }

    private fun isDarkThemeActive(): Boolean {
        return (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }
}
