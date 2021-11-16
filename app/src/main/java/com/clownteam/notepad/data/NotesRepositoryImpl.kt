package com.clownteam.notepad.data

import com.clownteam.notepad.domain.model.Note
import com.clownteam.notepad.domain.repository.NotesRepository

class NotesRepositoryImpl : NotesRepository {

    override suspend fun getNotes(): List<Note> {
        TODO("Not yet implemented")
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