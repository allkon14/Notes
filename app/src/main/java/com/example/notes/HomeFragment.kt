package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.DataBase.NotesDatabase
import com.example.notes.Entity.Notes
import com.example.notes.adapter.NotesAdapter
import com.example.notes.databinding.FragmentCreateNoteBinding
import com.example.notes.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.SearchView
import android.widget.Toast
import java.util.Locale
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
//    private lateinit var binding: FragmentHomeBinding
//
//    var arrNotes = List<Notes>()
//    var notesAdapter: NotesAdapter = NotesAdapter()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//
//        }
//        binding = FragmentHomeBinding.inflate(layoutInflater)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)
//    }
//
//    companion object {
//
//        @JvmStatic
//        fun newInstance() =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.homeRecyclerView.setHasFixedSize(true)
//        binding.homeRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//
//         launch {
//            context?.let {
//                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
////                notesAdapter!!.setData(notes)
////                arrNotes = notes as List<Notes>
//                binding.homeRecyclerView.adapter = notesAdapter
//            }
//        }
//
//        notesAdapter!!.setOnClickListener(onClicked)
//
//        binding.fabBtnCreateNote.setOnClickListener {
//            replaceFragment(CreateNoteFragment.newInstance(),false)
//
//        }
//
////        search_view.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
////            override fun onQueryTextSubmit(p0: String?): Boolean {
////                return true
////            }
////
////            override fun onQueryTextChange(p0: String?): Boolean {
////
////                var tempArr = ArrayList<Notes>()
////
////                for (arr in arrNotes){
////                    if (arr.title!!.toLowerCase(Locale.getDefault()).contains(p0.toString())){
////                        tempArr.add(arr)
////                    }
////                }
////
////                notesAdapter.setData(tempArr)
////                notesAdapter.notifyDataSetChanged()
////                return true
////            }
////
////        })
//
//
//    }
//
//    private val onClicked = object :NotesAdapter.OnItemClickListener{
//        override fun onClicked(notesId: Int) {
//
//
//            var fragment :Fragment
//            var bundle = Bundle()
//            bundle.putInt("noteId",notesId)
//            fragment = CreateNoteFragment.newInstance()
//            fragment.arguments = bundle
//
//            replaceFragment(fragment,false)
//        }
//
//    }
//
//    fun replaceFragment(fragment:Fragment, istransition:Boolean){
//        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
//
//        if (istransition){
//            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
//        }
//        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
//    }
private lateinit var binding: FragmentHomeBinding
var arrNotes = ArrayList<Notes>()
    var notesAdapter: NotesAdapter = NotesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.homeRecyclerView.setHasFixedSize(true)
//
//        binding.homeRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//
//
//            context?.let {
//                launch{
//                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
//                notesAdapter!!.setData(notes)
//                arrNotes = notes as ArrayList<Notes>
//                binding.homeRecyclerView.adapter = notesAdapter}
//            }
//
//
//        notesAdapter!!.setOnClickListener(onClicked)


        binding.fabBtnCreateNote.setOnClickListener{
            //                arrNotes = notes as ArrayList<Notes>
//            Toast.makeText(this@HomeFragment, "", Toast.LENGTH_SHORT).show()
//            Toast.makeText(this@HomeFragment, "", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@HomeFragment, CreateNoteFragment::class.java)
//            startActivity(Intent(this@HomeFragment, CreateNoteFragment::class.java))
//            replaceFragment(CreateNoteFragment.newInstance(),false)
            replaceFragment(CreateNoteFragment.newInstance(), false)

        }


        binding.searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                var tempArr = ArrayList<Notes>()

                for (arr in arrNotes){
                    if (arr.title!!.toLowerCase(Locale.getDefault()).contains(p0.toString())){
                        tempArr.add(arr)
                    }
                }

                notesAdapter.setData(tempArr)
                notesAdapter.notifyDataSetChanged()
                return true
            }

        })


    }


    private val onClicked = object :NotesAdapter.OnItemClickListener{
        override fun onClicked(notesId: Int) {


            var fragment :Fragment
            var bundle = Bundle()
            bundle.putInt("noteId",notesId)
            fragment = CreateNoteFragment.newInstance()
            fragment.arguments = bundle

            replaceFragment(fragment,false)
        }

    }


    fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

}