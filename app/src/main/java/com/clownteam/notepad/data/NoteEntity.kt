package com.clownteam.notepad.data

import androidx.room.Entity
import com.clownteam.notepad.domain.model.Note

@Entity(tableName = "NOTES")
data class NoteEntity(
    val id: Int,
    val title: String,
    val content: String
)

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content
    )
}