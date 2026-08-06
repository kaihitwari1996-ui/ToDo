
# ULTRA PRO THEME SYSTEM - Following the research doc exactly

# Color.kt - Single brand color system with priority hierarchy
content = '''package com.example.todoapp.ui.theme

import androidx.compose.ui.graphics.Color

// Brand - Single accent (TickTick/Things 3 style)
val Brand = Color(0xFF6D4AFF)
val BrandLight = Color(0xFF9B87FF)
val BrandDark = Color(0xFF5538CC)

// Backgrounds - Ample whitespace
val Background = Color(0xFFFAFAFA)
val Surface = Color(0xFFFFFFFF)
val SurfaceElevated = Color(0xFFF5F5F7)

// Text - High contrast hierarchy
val TextPrimary = Color(0xFF1A1A2E)
val TextSecondary = Color(0xFF6B7280)
val TextTertiary = Color(0xFF9CA3AF)
val TextInverse = Color(0xFFFFFFFF)

// Priority - Red → Orange → Yellow → Green (descending urgency)
val PriorityUrgent = Color(0xFFEF4444)
val PriorityHigh = Color(0xFFF97316)
val PriorityMedium = Color(0xFFEAB308)
val PriorityLow = Color(0xFF22C55E)

// Status
val StatusPending = Color(0xFF9CA3AF)
val StatusInProgress = Color(0xFF3B82F6)
val StatusDone = Color(0xFF22C55E)

// Category palette (generated, not rainbow)
val CatWork = Color(0xFF8B5CF6)
val CatPersonal = Color(0xFFEC4899)
val CatStudy = Color(0xFF14B8A6)
val CatHealth = Color(0xFFF97316)
val CatFinance = Color(0xFF3B82F6)

// Dark mode
val DarkBackground = Color(0xFF0F0F1A)
val DarkSurface = Color(0xFF1A1A2E)
val DarkSurfaceElevated = Color(0xFF252542)
'''
with open("/mnt/agents/output/todo-app-ultra/app/src/main/java/com/example/todoapp/ui/theme/Color.kt", "w") as f:
    f.write(content)

# Type.kt - Legible, scanable typography
content = '''package com.example.todoapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
'''
with open("/mnt/agents/output/todo-app-ultra/app/src/main/java/com/example/todoapp/ui/theme/Type.kt", "w") as f:
    f.write(content)

# Theme.kt
content = '''package com.example.todoapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = Brand,
    onPrimary = TextInverse,
    primaryContainer = Brand.copy(alpha = 0.12f),
    onPrimaryContainer = BrandDark,
    secondary = StatusDone,
    onSecondary = TextInverse,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceElevated,
    onSurfaceVariant = TextSecondary,
    error = PriorityUrgent,
    onError = TextInverse,
    outline = TextTertiary.copy(alpha = 0.5f)
)

private val DarkColors = darkColorScheme(
    primary = BrandLight,
    onPrimary = TextInverse,
    primaryContainer = Brand.copy(alpha = 0.2f),
    onPrimaryContainer = BrandLight,
    secondary = StatusDone,
    onSecondary = TextInverse,
    background = DarkBackground,
    onBackground = Color(0xFFE0E0E0),
    surface = DarkSurface,
    onSurface = Color(0xFFE0E0E0),
    surfaceVariant = DarkSurfaceElevated,
    onSurfaceVariant = Color(0xFF9E9E9E),
    error = PriorityUrgent,
    onError = TextInverse,
    outline = Color(0xFF5C5C5C)
)

@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
'''
with open("/mnt/agents/output/todo-app-ultra/app/src/main/java/com/example/todoapp/ui/theme/Theme.kt", "w") as f:
    f.write(content)

print("Ultra theme system done")
