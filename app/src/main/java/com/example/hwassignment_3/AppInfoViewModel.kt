package com.example.hwassignment_3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AppInfoViewModel(private val workerRepository: WorkerRepository) : ViewModel() {
    // Mutable state variables for app information
    val appName = mutableStateOf(workerRepository.appName.value)
    val appDeveloperName = mutableStateOf(workerRepository.appDeveloperName.value)
    val appVersion = mutableStateOf(workerRepository.appVersion.value)
}