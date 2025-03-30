package com.example.hwassignment_3

import android.app.Application

class MyApplication : Application(){
    companion object {
        lateinit var x: WorkerRepository
    }
    override fun onCreate() {
        super.onCreate()
// Perform initialization tasks here
// Setup global resources, libraries, etc.
        x = WorkerRepository(this)
    }
}