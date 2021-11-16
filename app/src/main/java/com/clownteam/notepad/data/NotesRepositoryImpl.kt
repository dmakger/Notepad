package com.clownteam.notepad.data

import com.clownteam.notepad.NotesApplication
import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepositoryImpl : NotesRepository {

    override suspend fun getNotes(): List<Note> {
        val notes = ArrayList<Note>()

        withContext(Dispatchers.IO) {
            notes.addAll(
                AppDatabase.getInstance(NotesApplication.context).noteDao().getNotes()
                    .map { it.toNote() }
            )
        }

        return notes
    }

    override suspend fun getNoteById(id: Int): Note {
        val note: Note

        withContext(Dispatchers.IO) {
            note = AppDatabase.getInstance(NotesApplication.context).noteDao().getNoteById(id)
        }

        return note
    }

    override suspend fun insertNote(note: Note) {
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance(NotesApplication.context).noteDao()
                .insertNote(NoteEntity.fromNote(note))
        }
    }

    override suspend fun deleteNote(note: Note) {
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance(NotesApplication.context).noteDao()
                .deleteNote(NoteEntity.fromNote(note))
        }
    }

    override suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance(NotesApplication.context).noteDao()
                .updateNote(NoteEntity.fromNote(note))
        }
    }
}