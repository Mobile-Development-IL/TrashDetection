package com.infinitelearning.trashdetection.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitelearning.trashdetection.domain.DetectRepository
import com.infinitelearning.trashdetection.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val detectionRepository: DetectRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun uploadImage(image: File) = viewModelScope.launch {
        detectionRepository.uploadImage(image).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                }

                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            result = result.data,
                            success = true
                        )
                    }
                }
            }
        }
    }

    fun resetState() {
        _state.update {
            MainState()
        }
    }

    fun isBottomSheetShown() {
        _state.update {
            it.copy(
                isBottomSheetShown = true
            )
        }
    }

    fun isBottomSheetCancel() {
        _state.update {
            it.copy(
                isBottomSheetShown = false
            )
        }
    }
}