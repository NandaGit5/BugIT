package com.mobily.bugitapp.apis

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.mobily.bugitapp.utilities.AppConstants
import retrofit2.http.POST
import retrofit2.http.Query

interface BugITAPI {
    // POST request to submit a bug report
    @POST(AppConstants.APP_SCRIPT_WEB_APP_URL)
    fun submitBugReport(
        @Query("action") action: String,                 // Action parameter for the request
        @Query("uBugId") userId: String,                 // Bug ID of the report
        @Query("uDescription") userName: String,        // Description of the bug
        @Query("uImage") userImage: String,            // Image of the bug (as string)
        selectedImageUris: SnapshotStateList<String>,          // Success listener for the response
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener        // Error listener for the response){}
    ): StringRequest
}