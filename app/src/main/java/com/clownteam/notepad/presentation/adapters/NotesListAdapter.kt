package com.clownteam.notepad.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clownteam.notepad.R
import com.clownteam.notepad.domain.model.Note
import kotlinx.android.synthetic.main.listitem_notes.view.*

class NotesListAdapter(private val listener: Listener) :
    RecyclerView.Adapter<NotesListAdapter.MyViewHolder>() {

    private val list = mutableListOf<Note>()

    interface Listener {
        fun onItemClick(note: Note)
    }


    val lastItemIndex: Int
        get() = list.lastIndex

    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.listitem_notes, parent, false)
    ) {
        fun bind(note: Note) = with(itemView) {
            txtTitle.text = note.title
            txtDate.text = note.date

            setOnClickListener { listener.onItemClick(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun addItem(item: Note) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun clear() {
        list.clear()
    }
}