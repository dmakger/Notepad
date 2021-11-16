package com.clownteam.notepad.presentation

import android.content.Intent
import android.os.Bundle
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
    private var adapterNotesItem: NotesListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
    }

    override fun onStart() {
        super.onStart()

        adapterNotesItem = NotesListAdapter(this)

        rvListNotes.layoutManager = LinearLayoutManager(this)
        rvListNotes.adapter = adapterNotesItem

        fbtnAddNoteItem.setOnClickListener {
            NoteDetailedActivity.startActivity(this)
            rvListNotes.scrollToPosition(adapterNotesItem!!.lastItemIndex)
        }

        val notes = ArrayList<Note>()

        MainScope().launch {
            notes.addAll(repository.getNotes())

            adapterNotesItem?.clear()

            for (note in notes) {
                adapterNotesItem?.addItem(note)
            }
        }
    }

    override fun onItemClick(note: Note) {
        val intent = Intent(baseContext, NoteDetailedActivity::class.java)
        intent.putExtra(NoteDetailedActivity.EXTRA_ID_NOTE_ITEM, note.id)
        startActivity(intent)
    }
}