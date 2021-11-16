package com.clownteam.notepad

import android.app.Application
import android.content.Context

class NotesApplication : Application() {
    companion object {
        private lateinit var currentInstance: NotesApplication

        val context: Context
            get() = currentInstance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        currentInstance = this
    }
}