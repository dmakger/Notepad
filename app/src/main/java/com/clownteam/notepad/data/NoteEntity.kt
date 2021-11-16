package com.clownteam.notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.clownteam.notepad.domain.model.Note

@Entity(tableName = "NOTES")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val date: String
) {
    companion object {

        fun fromNote(note: Note): NoteEntity {
            return NoteEntity(
                id = note.id,
                title = note.title,
                content = note.content,
                date = note.date
            )
        }
    }
}

fun NoteEntity.toNote(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content,
        date = this.date
    )
}