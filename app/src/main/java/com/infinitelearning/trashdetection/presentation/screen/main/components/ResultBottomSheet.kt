package com.infinitelearning.trashdetection.presentation.screen.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultBottomSheet(
    predict: String,
    score: String,
    description: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(onDismissRequest = onDismiss, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Jenis Sampah : ")
                    withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                        append(predict)
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    append("Presentase : ")
                    withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                        append(score)
                    }
                }
            )
            Text(
                text = buildAnnotatedString {
                    append("Keterangan : ")
                    withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                        append(description)
                    }
                }
            )
        }
    }
}