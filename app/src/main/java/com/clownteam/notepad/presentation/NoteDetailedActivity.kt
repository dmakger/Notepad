package com.clownteam.notepad.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import com.clownteam.notepad.domain.repository.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteDetailedActivity : AppCompatActivity() {

    private val repository: NotesRepository = TestRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}