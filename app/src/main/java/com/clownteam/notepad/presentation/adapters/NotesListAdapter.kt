package com.clownteam.notepad.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clownteam.notepad.R
import com.clownteam.notepad.domain.model.Note
import kotlinx.android.synthetic.main.listitem_notes.view.*

class NotesListAdapter : RecyclerView.Adapter<NotesListAdapter.MyViewHolder>() {

    private val list = mutableListOf<Note>()

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.listitem_notes, parent, false)
    ){
        fun bind(note: Note) = with(itemView) {
            txtTitle.text = note.title
            txtDate.text = note.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = list.size

}