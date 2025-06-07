package com.dd2d.ori_android.presentation_main.view_model


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.dd2d.ori_android.presentation_main._navigation.MainScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  val route = savedStateHandle.toRoute<MainScreenRoute>()
}