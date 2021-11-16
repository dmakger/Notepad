package com.clownteam.notepad.data

import androidx.room.Entity
import com.clownteam.notepad.domain.model.Note

@Entity(tableName = "NOTES")
data class NoteEntity(
    val id: Int,
    val title: String,
    val content: String
) {
    companion object {

        fun fromNote(note: Note): NoteEntity {
            return NoteEntity(
                id = note.id,
                title = note.title,
                content = note.content
            )
        }
    }
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content
    )
}