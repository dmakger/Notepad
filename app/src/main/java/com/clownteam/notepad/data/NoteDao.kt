package com.clownteam.notepad.data

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES")
    fun getNotes(): List<NoteEntity>

    @Update
    fun updateNote(note: NoteEntity)

    @Insert
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)
}