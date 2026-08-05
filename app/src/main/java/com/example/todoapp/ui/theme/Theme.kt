package com.example.todoapp.ui.theme

import android.app.Activity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(themeMode: ThemeMode, content: @Composable () -> Unit) {
    val colorScheme = when (themeMode) {
        ThemeMode.PENCIL   -> lightColorScheme(
            primary    = PencilPrimary,
            secondary  = PencilSecondary,
            background = PencilBackground,
            surface    = PencilSurface,
            tertiary   = PencilAccent
        )
        ThemeMode.CLASSIC  -> lightColorScheme(
            primary    = ClassicPrimary,
            secondary  = ClassicSecondary,
            background = ClassicBackground,
            surface    = ClassicSurface
        )
        ThemeMode.TICKTICK -> lightColorScheme(
            primary    = FlowPrimary,
            secondary  = FlowSecondary,
            background = FlowBackground,
            surface    = FlowSurface,
            tertiary   = FlowGreen
        )
        ThemeMode.AGRO     -> lightColorScheme(
            primary    = AgroPrimary,
            secondary  = AgroSecondary,
            background = AgroBackground,
            surface    = AgroSurface,
            tertiary   = AgroAccent
        )
    }

    val typography = when (themeMode) {
        ThemeMode.PENCIL   -> PencilTypography
        ThemeMode.CLASSIC  -> ClassicTypography
        ThemeMode.TICKTICK -> FlowTypography
        ThemeMode.AGRO     -> AgroTypography
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = typography, content = content)
}
