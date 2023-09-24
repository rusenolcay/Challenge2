package com.rusen.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.rusen.challenge2.data.Note
import com.rusen.challenge2.data.Priority
import com.rusen.challenge2.databinding.ActivityMainBinding
import com.rusen.challenge2.databinding.DialogAddNoteBinding

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private val notes = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAdapter = NoteAdapter()
        binding.recyclerView.adapter = noteAdapter

        binding.floatingActionButton.setOnClickListener {
            showAddNoteDialog()
        }
    }

    private fun showAddNoteDialog() {
        val dialogBinding = DialogAddNoteBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()
        with(dialogBinding) {
            btnAdd.setOnClickListener {
                val note = createNote(
                    etTitle.text.toString(),
                    etDescription.text.toString(),
                    rgPriority.checkedRadioButtonId
                )
                notes.add(note)
                noteAdapter.submitList(notes.toList())
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun createNote(title: String, description: String, priorityId: Int): Note {
        val priority = when (priorityId) {
            R.id.rb_low -> Priority.LOW
            R.id.rb_medium -> Priority.MEDIUM
            R.id.rb_high -> Priority.HIGH
            else -> Priority.MEDIUM
        }
        return Note(title, description, priority)
    }
}