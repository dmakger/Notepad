package com.clownteam.notepad.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clownteam.notepad.R
import com.clownteam.notepad.data.NotesRepositoryImpl
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import kotlinx.android.synthetic.main.activity_note_detailed.*
import kotlinx.android.synthetic.main.activity_notes_list.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailedActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE_ITEM = "note_item"
        const val EXTRA_ID_NOTE_ITEM = "id_note_item"
        fun startActivity(activity: AppCompatActivity) {
            val intent = Intent(activity, NoteDetailedActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private val repository: NotesRepository = NotesRepositoryImpl()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detailed)

        id = intent.getIntExtra(EXTRA_ID_NOTE_ITEM, 0)

        var note: Note

        if (id != 0) {
            MainScope().launch {
                note = repository.getNoteById(id)
                etTitle.setText(note.title)
                etDescription.setText(note.content)
            }
        }
    }

    /**
     * Добавляем в activity_add_new_todo_item меню menu_add_todo_item
     * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_add_note_item, menu)
        return true
    }

    /**
     * Обрабатываем нажатие на кнопки меню menu_add_todo_item
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var note: Note? = null
        when (item.itemId) {
            R.id.action_done -> {
                if (etTitle.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter title", Toast.LENGTH_LONG).show()
                    return true
                }

                note = Note(
                    id,
                    etTitle.text.toString(),
                    etDescription.text.toString(),
                    getCurrentDate()
                )

                MainScope().launch {
                    if (id == 0) {
                        repository.insertNote(note!!)
                    } else {
                        repository.updateNote(note!!)
                    }
                }

                finish()
            }

            R.id.action_delete -> {
                if (id != 0) {
                    MainScope().launch {
                        note = repository.getNoteById(id)

                        repository.deleteNote(note!!)
                    }
                }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCurrentDate(): String {
        val currentTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}