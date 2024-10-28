package com.mobily.bugitapp.models

data class BugData(
    val bugId: String,
    val bugDescription: String,
    val imageUris: List<String>,
    val status: String
)
data class UploadRequest(
    val bugId: String,
    val bugDescription: String,
    val bugStatus: String,
    val imageUris: List<String>
)

data class UploadResponse(
    val success: Boolean,
    val message: String
)