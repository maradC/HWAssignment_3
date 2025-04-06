package com.example.hwassignment_3

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class WorkerRepository(private val application: Application) {

    private val workerList = mutableStateListOf<Worker>()
    val appName = mutableStateOf("Worker.io")
    val appDeveloperName = mutableStateOf("Chris M")
    val appVersion = mutableStateOf("3.9.2")

     val myScope = CoroutineScope(Dispatchers.IO)

    init {
        myScope.launch {
            workersFromFile()
        }
    }

    fun getWorkers(): List<Worker> = workerList

    private suspend fun workersFromFile() {
        val fileName = "workers.txt"
        try {
            println("Attempting to open file: $fileName")
            val inputStream = application.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                line?.let {
                    val parts = it.split(",")
                    if (parts.size >= 3) {
                        val worker = Worker(
                            name = parts[0],
                            hoursWorked = parts[1],
                            rate = parts[2].toDouble()
                        )
                        CoroutineScope(Dispatchers.Main).launch {
                            workerList.add(worker)
                        }
                    }
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error reading  file: ${e.message}")
        }
    }
}