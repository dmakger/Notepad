package com.clownteam.notepad.domain.repository

import com.clownteam.notepad.domain.model.Note

interface NotesRepository {

    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun updateNote(note: Note)
}