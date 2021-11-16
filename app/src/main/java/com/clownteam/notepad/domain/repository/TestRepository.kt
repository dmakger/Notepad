package com.clownteam.notepad.domain.repository

import com.clownteam.notepad.domain.model.Note

class TestRepository : NotesRepository {

    override suspend fun getNotes(): List<Note> {
        return listOf(Note(0, "qwe", "qwe"))
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