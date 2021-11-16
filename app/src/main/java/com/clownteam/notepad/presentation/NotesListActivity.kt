package com.clownteam.notepad.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.repository.NotesRepository
import kotlinx.android.synthetic.main.activity_notes_list.*

class NotesListActivity : AppCompatActivity() {

    private val repository: NotesRepository = NotesRepositoryImpl()
    private val adapterNotesItem = NotesListActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        initialization()
    }

    private fun initialization() {
        rvListNotes.layoutManager = LinearLayoutManager(this)
    }
}