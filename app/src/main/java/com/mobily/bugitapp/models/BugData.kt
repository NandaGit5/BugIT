package com.mobily.bugitapp.models

data class BugData(
    val bugId: String,
    val bugDescription: String,
    val imageUris: List<String>,
    val status: String
)