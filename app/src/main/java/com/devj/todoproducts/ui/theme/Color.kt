package com.devj.todoproducts.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

//region Light Theme Colors
val primaryLight = Color(0xFF006E1C)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFB6F2AF)
val onPrimaryContainerLight = Color(0xFF000000)
val inversePrimaryLight = Color(0xFF8EDFA3)
val secondaryLight = Color(0xFF36855E)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFC0FFD8)
val onSecondaryContainerLight = Color(0xFF000000)
val tertiaryLight = Color(0xFF00658C)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFC5E7FF)
val onTertiaryContainerLight = Color(0xFF000000)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF000000)
val backgroundLight = Color(0xFFFCFCFC)
val onBackgroundLight = Color(0xFF111111)
val surfaceLight = Color(0xFFFCFCFC)
val onSurfaceLight = Color(0xFF111111)
val surfaceVariantLight = Color(0xFFF3F3F3)
val onSurfaceVariantLight = Color(0xFF393939)
val outlineLight = Color(0xFF919191)
val outlineVariantLight = Color(0xFFD1D1D1)
val scrimLight = Color(0xFF000000)
val surfaceDimLight = Color(0xFFE0E0E0)
val surfaceBrightLight = Color(0xFFFDFDFD)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFF8F8F8)
val surfaceContainerLight = Color(0xFFF3F3F3)
val surfaceContainerHighLight = Color(0xFFEDEDED)
val surfaceContainerHighestLight = Color(0xFFE7E7E7)
val inverseSurfaceLight = Color(0xFF2A2A2A)
val onInverseSurfaceLight = Color(0xFFF1F1F1)

//endregion

//region Dark Theme Colors

val primaryDark = Color(0xFF7EDB7B)
val onPrimaryDark = Color(0xFF000000)
val primaryContainerDark = Color(0xFF005313)
val onPrimaryContainerDark = Color(0xFFFFFFFF)
val inversePrimaryDark = Color(0xFF3C623B)
val primaryFixedDark = Color(0xFFBFE9CA)
val primaryFixedDimDark = Color(0xFF8CD49F)
val onPrimaryFixedDark = Color(0xFF000902)
val onPrimaryFixedVariantDark = Color(0xFF001B07)
val secondaryDark = Color(0xFFA3F4C5)
val onSecondaryDark = Color(0xFF000000)
val secondaryContainerDark = Color(0xFF003822)
val onSecondaryContainerDark = Color(0xFFFFFFFF)
val tertiaryDark = Color(0xFF87CFFB)
val onTertiaryDark = Color(0xFF000000)
val tertiaryContainerDark = Color(0xFF004C6A)
val onTertiaryContainerDark = Color(0xFFFFFFFF)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF000000)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFFFFF)
val backgroundDark = Color(0xFF080808)
val onBackgroundDark = Color(0xFFF1F1F1)
val surfaceDark = Color(0xFF080808)
val onSurfaceDark = Color(0xFFF1F1F1)
val surfaceVariantDark = Color(0xFF151515)
val onSurfaceVariantDark = Color(0xFFCACACA)
val outlineDark = Color(0xFF777777)
val outlineVariantDark = Color(0xFF414141)
val scrimDark = Color(0xFF000000)
val surfaceDimDark = Color(0xFF060606)
val surfaceBrightDark = Color(0xFF2C2C2C)
val surfaceContainerLowestDark = Color(0xFF010101)
val surfaceContainerLowDark = Color(0xFF0E0E0E)
val surfaceContainerDark = Color(0xFF151515)
val surfaceContainerHighDark = Color(0xFF1D1D1D)
val surfaceContainerHighestDark = Color(0xFF282828)
val inverseSurfaceDark = Color(0xFFE8E8E8)
val onInverseSurfaceDark = Color(0xFF2A2A2A)
//endregion

//region Color Schemes

val LightColorScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    inversePrimary = inversePrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
    inverseSurface = inverseSurfaceLight,
)

val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    inversePrimary = inversePrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
    inverseSurface = inverseSurfaceDark,
)

//endregion