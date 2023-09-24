package com.rusen.challenge2.data

import androidx.recyclerview.widget.DiffUtil

data class Note(
    val title: String,
    val description: String,
    val priority: Priority,
    var done: Boolean = false
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Note> =
            object : DiffUtil.ItemCallback<Note>() {
                override fun areItemsTheSame(oldItem: Note, newItem: Note) =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Note, newItem: Note) =
                    oldItem == newItem
            }
    }
}