package com.clownteam.notepad.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.repository.NotesRepository

class NoteDetailedActivity : AppCompatActivity() {

    private val repository: NotesRepository = NotesRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}