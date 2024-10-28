package com.mobily.bugitapp.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class MultipleImageContract : ActivityResultContract<Void?, List<Uri>?>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        return Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true) // Allow multiple selections
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): List<Uri>? {
        if (resultCode == Activity.RESULT_OK && intent != null) {
            val clipData = intent.clipData
            val imageUris = mutableListOf<Uri>()

            // Check if multiple images were selected
            if (clipData != null) {
                for (i in 0 until clipData.itemCount) {
                    imageUris.add(clipData.getItemAt(i).uri)
                }
            } else {
                // Single image selected
                intent.data?.let { imageUris.add(it) }
            }

            return imageUris // Return list of URIs
        }
        return null
    }
}
