package com.example.hwassignment_3

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailListViewModel(private val workerRepository: WorkerRepository, private val targetWorkerName:String) : ViewModel() {

val name = mutableStateOf(" ")
val hoursWorked = mutableStateOf(" ")
val rate = mutableStateOf(0.0)
val pay = mutableStateOf(0.0)
    init {
        val worker = workerRepository.getWorkers().find { it.name == targetWorkerName }

        worker?.let {
            name.value = it.name
            hoursWorked.value = it.hoursWorked
            rate.value = it.rate

            try {
                val hours = it.hoursWorked.toDouble()
                pay.value = hours * it.rate
            }catch (e: NumberFormatException){
                pay.value = 0.0
            }
        }
    }

}