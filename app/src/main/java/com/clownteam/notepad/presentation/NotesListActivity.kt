package com.clownteam.notepad.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import com.clownteam.notepad.presentation.adapters.NotesListAdapter
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.android.synthetic.main.dialog_create_note.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListActivity : AppCompatActivity(), CreateNoteDialogFragment.CreateNoteDialogListener {

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
            CreateNoteDialogFragment().show(supportFragmentManager, "Create Note")
        }
    }

    override fun onCreateNoteDialogPositiveClick(dialog: DialogFragment, noteTitle: String) {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val note = Note(
            0,
            title = noteTitle,
            content = "",
            date = dateFormat.format(currentTime)
        )

        MainScope().launch {
            repository.insertNote(note)
        }

        adapterNotesItem.addItem(note)
        rvListNotes.scrollToPosition(adapterNotesItem.lastItemIndex)
    }

    override fun onCreateNoteDialogNegativeClick(dialog: DialogFragment) { }
}

class CreateNoteDialogFragment : DialogFragment() {

    interface CreateNoteDialogListener {
        fun onCreateNoteDialogPositiveClick(dialog: DialogFragment, noteTitle: String)
        fun onCreateNoteDialogNegativeClick(dialog: DialogFragment)
    }

    private lateinit var listener: CreateNoteDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as CreateNoteDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement CreatePlaylistDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val view = requireActivity().layoutInflater.inflate(
                R.layout.dialog_create_note,
                null
            )

            builder.setView(view)
                .setTitle(R.string.create_note_dialog_title)
                .setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener { _, _ ->
                        val name = view.etNoteTitle.text.toString()
                        listener.onCreateNoteDialogPositiveClick(this, name)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        listener.onCreateNoteDialogNegativeClick(this)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}