package com.mobily.bugitapp.viewmodels

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.mobily.bugitapp.repository.BugITRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BugITViewModel @Inject constructor(private val bugTrackerRepository: BugITRepository) : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


/**
     * Submits a bug report to the bug tracking system.
     * @param bugId The ID of the user submitting the bug report.
     * @param description The name of the user submitting the bug report.
     * @param userImage The image associated with the bug report, encoded as a string.
     */

    fun submitBugReport(
        bugId: String,
        description: String,
        userImage: String,
        selectedImageUris: SnapshotStateList<String>
    ) {
        _isLoading.value = true
        val listener = Response.Listener<String> { response ->
            _response.value = response
            _isLoading.value = false
        }

        val errorListener = Response.ErrorListener { error ->
            _response.value = error.message
            _isLoading.value = false
        }

        val action = "insert" // Set the action here as needed
        bugTrackerRepository.submitBugReport(action, bugId, description, userImage, selectedImageUris,listener, errorListener)
    }
}
