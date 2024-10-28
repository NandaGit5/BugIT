package com.mobily.bugitapp.uiscreens

import android.annotation.SuppressLint
import android.graphics.BitmapFactory

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mobily.bugitapp.utilities.ImageUtilities.getResizedBitmap
import com.mobily.bugitapp.utilities.ImageUtilities.getStringImage
import com.mobily.bugitapp.utilities.MultipleImageContract
import com.mobily.bugitapp.viewmodels.BugITViewModel

var selectedImages = mutableStateListOf<Uri>()
var selectedImageUris = mutableStateListOf<String>()

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddBugScreen(navController: NavController) {
    var bugId by remember { mutableStateOf("") }
    var bugDescription by remember { mutableStateOf("") }
    var bugStatus by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = bugId,
            onValueChange = { bugId = it },
            label = { Text("Bug ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = bugDescription,
            onValueChange = { bugDescription = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = bugStatus,
            onValueChange = { bugStatus = it },
            label = { Text("Bug Status") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PickImages()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
               // submit bug
              //  navController.navigate("tasksList")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Submit Bug Report")
        }
        Spacer(modifier = Modifier.height(16.dp))
        SetImagesInGrid()
    }
}

@Composable
fun SetImagesInGrid() {
    if (selectedImages.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // Set number of columns
            contentPadding = PaddingValues(8.dp)
        ) {
            items(selectedImages) { uri ->
                ImageCard(
                    uri = uri,
                    onRemove = {
                        selectedImages.remove(uri) // Remove the image from the list
                    }
                )
            }
        }
    }
}
@Composable
fun ImageCard(uri: Uri, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(100.dp) // Set the size of each image
            .clip(RoundedCornerShape(8.dp))
    ) {
        // Using Coil to load the image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(uri)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // Crop the image to fit
        )

        // Button to remove the image
        IconButton(
            onClick = onRemove,
            modifier = Modifier
                .align(Alignment.TopEnd) // Align to the top end corner
                .size(24.dp) // Size of the remove button
        ) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Remove Image")
        }
    }
}
@Composable
fun PickImages() {
    var userBugImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var stringBugImage by remember { mutableStateOf<String?>(null) }
    val multipleImagePicker = rememberLauncherForActivityResult(MultipleImageContract()) { result ->
        result?.let { imageUris ->
            // Handle the selected image URIs here
            if (imageUris.isNotEmpty()) {
                selectedImages.addAll(imageUris)
                for (imageBugUri in imageUris) {
                    imageBugUri.path?.let { path ->
                        val bitmap = BitmapFactory.decodeFile(path)
                        Log.d("PickImages", "Bitmap path: $path")
                        if (bitmap != null) {
                            val resizedBitmap = getResizedBitmap(bitmap, 250)
                            stringBugImage = getStringImage(resizedBitmap)
                            userBugImage = resizedBitmap.asImageBitmap()
                            selectedImageUris.add(stringBugImage.toString())
                        } else {
                            // Handle the case where the bitmap could not be decoded
                        }
                    }?: run {
                        // Handle the case where the path is null
                    }
                }
                Log.d("PickImages", "Image URIs: $imageUris")
            }
            else{
                selectedImages.clear()
            }
        }
    }
    // Button to open the image picker
    Button(onClick = {
        multipleImagePicker.launch(null) // Launch the image picker
    }) {
        Text("Pick Images")
    }

}