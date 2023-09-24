package com.rusen.challenge2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.rusen.challenge2.R
import com.rusen.challenge2.data.Note
import com.rusen.challenge2.data.Priority
import com.rusen.challenge2.databinding.DialogAddNoteBinding
import com.rusen.challenge2.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private lateinit var noteAdapter: NoteAdapter
    private val notes = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        noteAdapter = NoteAdapter()
        binding.recyclerView.adapter = noteAdapter

        binding.floatingActionButton.setOnClickListener {
            showAddNoteDialog()
        }
        return binding.root
    }

    private fun showAddNoteDialog() {
        val dialogBinding = DialogAddNoteBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .create()
        with(dialogBinding) {
            btnAdd.setOnClickListener {
                if (etTitle.text.isNullOrEmpty() || etDescription.text.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        getString(R.string.warning_message),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
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