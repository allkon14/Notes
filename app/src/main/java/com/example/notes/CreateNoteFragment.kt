package com.example.notes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import android.widget.Toast
import com.example.notes.DataBase.NotesDatabase
import com.example.notes.Entity.Notes
import com.example.notes.databinding.FragmentCreateNoteBinding
import java.text.SimpleDateFormat
import java.util.Date


class CreateNoteFragment : BaseFragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    var selectedColor = "#171C26"
    var currentDate:String? = null
    lateinit var tvDateTime: Date
    private var READ_STORAGE_PERM = 123
    private var REQUEST_CODE_IMAGE = 456
    private var selectedImagePath = ""
    private var webLink = ""
    private var noteId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        noteId = requireArguments().getInt("noteId",-1)
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

        currentDate = sdf.format(Date())
//        colorView.setBackgroundColor(Color.parseColor(selectedColor))
        Log.i("Inform","aht")
        binding.tvDateTime.text = currentDate

        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.imgDone.setOnClickListener(){
            saveNote()
        }
    }

    private fun saveNote(){

        if (binding.etNoteTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Title is Required",Toast.LENGTH_SHORT).show()
        }
        else if (binding.etNoteSubTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Sub Title is Required",Toast.LENGTH_SHORT).show()
        }

        else if (binding.etNoteDesc.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Description is Required",Toast.LENGTH_SHORT).show()
        }

        else {


            launch{
            var notes = Notes()
            notes.title = binding.etNoteTitle.text.toString()
            notes.subTitle = binding.etNoteSubTitle.text.toString()
            notes.noteText = binding.etNoteDesc.text.toString()
            notes.dateTime = currentDate
            notes.color = selectedColor
            notes.imgPath = selectedImagePath
            notes.webLink = webLink
            context?.let {
                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                binding.etNoteTitle.setText("")
                binding.etNoteSubTitle.setText("")
                binding.etNoteDesc.setText("")
                binding.layoutImage.visibility = View.GONE
                binding.imgNote.visibility = View.GONE
                binding.tvWebLink.visibility = View.GONE
                requireActivity().supportFragmentManager.popBackStack()
            }
            }
        }

    }

}