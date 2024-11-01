package com.mobily.bugitapp.repository

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.mobily.bugitapp.apis.BugITAPI
import com.mobily.bugitapp.utilities.AppConstants.APP_SCRIPT_WEB_APP_URL
import javax.inject.Inject



class BugITRepository @Inject constructor(private val requestQueue: RequestQueue) : BugITAPI {
    override fun submitBugReport(
        action: String,                         // The action to perform (e.g., insert, update)
        userId: String,                         // The user ID associated with the bug report
        userName: String,                       // The user name associated with the bug report
        userImage: String, // The image associated with the bug report (in string format)
        selectedImageUris: SnapshotStateList<String>,    // Listener for successful response
        listener: Response.Listener<String>,
        errorListener: Response.ErrorListener   // Listener for error response){}
    ): StringRequest {
        val url = APP_SCRIPT_WEB_APP_URL
        // URL of the Google Apps Script web app
        val stringRequest = object : StringRequest(Method.POST, url, listener, errorListener) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["action"] = action
                params["uBugId"] = userId
                params["uDescription"] = userName
                params["uImage"] = userImage
                return params
            }
        }

        // Set the retry policy for the request
        val socketTimeout = 30000
        val policy = DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        stringRequest.retryPolicy = policy

        // Add the request to the request queue

        requestQueue.add(stringRequest)

        // Return the StringRequest object
        return stringRequest
    }

}
