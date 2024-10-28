package com.mobily.bugitapp.retrofits

import com.mobily.bugitapp.models.UploadRequest
import com.mobily.bugitapp.models.UploadResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("google-sheets-endpoint")
    suspend fun uploadData(
        @Body requestBody: UploadRequest
    ): Response<UploadResponse>
}