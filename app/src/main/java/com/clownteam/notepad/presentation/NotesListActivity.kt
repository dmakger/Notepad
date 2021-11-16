package com.clownteam.notepad.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import com.clownteam.notepad.presentation.adapters.NotesListAdapter
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NotesListActivity : AppCompatActivity() {

    private val repository: NotesRepository = NotesRepositoryImpl()
    private val adapterNotesItem = NotesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        initialization()
    }

    private fun initialization() {
        rvListNotes.layoutManager = LinearLayoutManager(this)
        rvListNotes.adapter = adapterNotesItem

        val notes = ArrayList<Note>()

        MainScope().launch {
            notes.addAll(repository.getNotes())

            for (note in notes) {
                adapterNotesItem.addItem(note)
            }
        }

        fbtnAddNoteItem.setOnClickListener {
            val note = Note(0, "QWE", "qwe", "25 04")

            MainScope().launch {
                repository.insertNote(note)
            }

            adapterNotesItem.addItem(note)
            rvListNotes.scrollToPosition(adapterNotesItem.lastItemIndex)
        }
    }
}