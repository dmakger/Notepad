package com.clownteam.notepad.domain.model

data class Note (
    val id: Int,
    val title: String,
    val content: String,
    val date: String
)