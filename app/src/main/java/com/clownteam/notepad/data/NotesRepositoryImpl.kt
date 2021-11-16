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
            notes.addAll(AppDatabase.getInstance(NotesApplication.context).noteDao().getNotes())
        }

        return notes
    }

    override suspend fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }
}