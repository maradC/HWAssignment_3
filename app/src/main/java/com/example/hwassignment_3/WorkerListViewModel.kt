package com.example.hwassignment_3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WorkerListViewModel(private val workerRepository: WorkerRepository) : ViewModel() {
    val workers = workerRepository.getWorkers()
}