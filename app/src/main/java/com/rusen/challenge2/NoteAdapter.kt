package com.rusen.challenge2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rusen.challenge2.data.Note
import com.rusen.challenge2.data.Note.Companion.DIFF_CALLBACK
import com.rusen.challenge2.data.Priority
import com.rusen.challenge2.databinding.ItemNoteBinding

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvTitle.text = note.title
                tvDescription.text = note.description
                tvPriorityValue.text = note.priority.name
                val priorityColor = when (note.priority) {
                    Priority.LOW -> android.R.color.holo_green_dark
                    Priority.MEDIUM -> android.R.color.holo_blue_dark
                    Priority.HIGH -> android.R.color.holo_red_dark
                }
                tvPriorityValue.setTextColor(ContextCompat.getColor(root.context, priorityColor))
            }
        }
    }
}