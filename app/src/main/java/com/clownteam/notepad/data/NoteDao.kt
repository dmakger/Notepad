package com.clownteam.notepad.data

import androidx.room.*
import com.clownteam.notepad.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES")
    fun getNotes(): List<NoteEntity>

    @Query("SELECT * FROM NOTES WHERE id = :id")
    fun getNoteById(id: Int): Note

    @Update
    fun updateNote(note: NoteEntity)

    @Insert
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)
}