package com.example.notes.adapter

//import kotlinx.android.synthetic.main.item_rv_notes.view.*
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Entity.Notes
import com.example.notes.databinding.ItemRvNotesBinding
import kotlinx.coroutines.flow.Flow
import kotlin.collections.ArrayList


class NotesAdapter(private var ListNotes: ArrayList<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var listener:OnItemClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemRvNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ListNotes.size
    }
//
//    fun setData(arrNotesList: List<Notes>){
//        ListNotes = arrNotesList as ArrayList<Notes>
//    }
//
//    fun setData(arrNotesList: Flow<List<Notes>>){
//        ListNotes = arrNotesList as ArrayList<Notes>
//    }
//
//    fun setOnClickListener(listener1: OnItemClickListener){
//        listener = listener1
//    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder.binding) {
            val note = ListNotes[position]
            itemtvTitle.text = note.title
            itemtvDesc.text = note.noteText
            itemtvDateTime.text = note.dateTime

            if (note.color != null) {
                itemcardView.setCardBackgroundColor(Color.parseColor(note.color))
            } else {
                itemcardView.setCardBackgroundColor(Color.parseColor(com.example.notes.R.color.ColorLightBlack.toString()))
            }

            if (note.webLink != "") {
                itemtvWebLink.text = note.webLink
                itemtvWebLink.visibility = View.VISIBLE
            } else {
                itemtvWebLink.visibility = View.GONE
            }

            itemcardView.setOnClickListener {
                listener!!.onClicked(note.id!!)
            }

        }
    }

    class NotesViewHolder(val binding: ItemRvNotesBinding) : RecyclerView.ViewHolder(binding.root){
//        val tvTitle : TextView = view.findViewById(R.id.itemtvTitle)
//        val tvDesc : TextView = view.findViewById(R.id.itemtvDesc)
//        val tvDateTime : TextView = view.findViewById(R.id.itemtvDateTime)
//        val cardView : CardView = view.findViewById(R.id.itemcardView)
//        val tvDateTimeWebLink : TextView = view.findViewById(R.id.itemtvWebLink)

    }


    interface OnItemClickListener{
        fun onClicked(noteId:Int)
    }

}