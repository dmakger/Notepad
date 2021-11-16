package com.clownteam.notepad.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.repository.NotesRepository

class NotesListActivity : AppCompatActivity() {

    private val repository: NotesRepository = NotesRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)


    }
}