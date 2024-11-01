package com.alejo.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform