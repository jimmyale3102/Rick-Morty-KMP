package com.alejo.rickmortyapp

import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.window.ComposeUIViewController
import com.alejo.rickmortyapp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }