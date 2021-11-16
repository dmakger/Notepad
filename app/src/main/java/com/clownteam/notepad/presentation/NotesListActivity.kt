package com.clownteam.notepad.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import com.clownteam.notepad.presentation.adapters.NotesListAdapter
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NotesListActivity : AppCompatActivity(), NotesListAdapter.Listener {

    private val repository: NotesRepository = NotesRepositoryImpl()
    private val adapterNotesItem = NotesListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        rvListNotes.layoutManager = LinearLayoutManager(this)
        rvListNotes.adapter = adapterNotesItem

        fbtnAddNoteItem.setOnClickListener {
            NoteDetailedActivity.startActivity(this)
            rvListNotes.scrollToPosition(adapterNotesItem.lastItemIndex)
        }
    }

    override fun onStart() {
        super.onStart()
        val notes = ArrayList<Note>()

        adapterNotesItem.clear()
        MainScope().launch {
            notes.addAll(repository.getNotes())

            for (note in notes) {
                adapterNotesItem.addItem(note)
            }
        }
    }

    override fun onItemClick(note: Note) {
//        Toast.makeText(baseContext, note.title, Toast.LENGTH_LONG).show()
        val intent = Intent(baseContext, NoteDetailedActivity::class.java)
//        Toast.makeText(baseContext, note.id.toString(), Toast.LENGTH_SHORT).show()
        intent.putExtra(NoteDetailedActivity.EXTRA_ID_NOTE_ITEM, note.id)
        baseContext.startActivity(intent)
    }
}