package com.infinitelearning.trashdetection.presentation.screen.main

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.infinitelearning.trashdetection.R
import com.infinitelearning.trashdetection.presentation.screen.main.components.CustomLoading
import com.infinitelearning.trashdetection.presentation.screen.main.components.ResultBottomSheet
import com.infinitelearning.trashdetection.utils.toFile

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    if (state.isBottomSheetShown) {
        state.result?.let {
            ResultBottomSheet(
                predict = it.classCategory ?: "",
                score = it.accuracy ?: "",
                description = it.description ?: "",
                onDismiss = {
                    viewModel.resetState()
                    viewModel.isBottomSheetCancel()
                }
            )
        }
    }

    LaunchedEffect(state.success) {
        if (state.success) {
            viewModel.isBottomSheetShown()
        }
    }

    LaunchedEffect(key1 = state.error) {
        state.error?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    if (state.loading) {
        CustomLoading()
    }

    MainContent(
        onImagePickerClick = {
            launcher.launch("image/*")
        },
        onAnalysisClick = {
            imageUri?.toFile(context)?.let { file ->
                viewModel.uploadImage(file)
            }
        },
        imageUri = imageUri,
        context = context,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    imageUri: Uri?,
    onImagePickerClick: () -> Unit,
    onAnalysisClick: () -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Trash Detection")
                }
            )
        },
        bottomBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedButton(
                    onClick = onImagePickerClick,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Pilih Gambar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onAnalysisClick() },
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Analisis")
                }
            }
        }
    ) { innerPadding ->
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUri ?: R.drawable.empty)
                .build(),
            contentDescription = "Picture",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}