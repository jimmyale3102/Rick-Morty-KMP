package com.alejo.rickmortyapp.data.remote.response

data class InfoResponse(
    val pages: Int,
    val next: String?,
    val prev: String?
)