package com.alejo.rickmortyapp.ui.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5ccf92)

val PrimaryBlack = Color(0xFFFFFFFF)
val SecondaryBlack = Color(0xFFEAE8EF)
val TertiaryBlack = Color(0xFFFAFAFA)

val PrimaryWhite = Color(0xFF000000)
val SecondaryWhite = Color(0xFF302F2F)
val TertiaryWhite = Color(0xFF464646)

val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) PrimaryBlack else PrimaryWhite
val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) SecondaryBlack else SecondaryWhite
val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryBlack else TertiaryWhite
val DefaultTextColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color.White else Color.Black