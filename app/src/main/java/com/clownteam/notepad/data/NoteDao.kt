package com.clownteam.notepad.data

import android.provider.ContactsContract
import androidx.room.*
import com.clownteam.notepad.domain.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES")
    fun getNotes(): List<Note>

    @Update
    fun updateNote(note: Note)

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}