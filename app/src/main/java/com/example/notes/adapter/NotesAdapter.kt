package com.example.notes.adapter

//import kotlinx.android.synthetic.main.item_rv_notes.view.*
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Entity.Notes
import com.example.notes.R
import com.example.notes.databinding.ItemRvNotesBinding
import java.util.*


class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private lateinit var binding: ItemRvNotesBinding
    var listener:OnItemClickListener? = null
    var arrList = ArrayList<Notes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesList: List<Notes>){
        arrList = arrNotesList as ArrayList<Notes>
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.tvTitle.text = arrList[position].title
        holder.tvDesc.text = arrList[position].noteText
        holder.tvDateTime.text = arrList[position].dateTime

        if (arrList[position].color != null){
            holder.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))
        }else{
            holder.cardView.setCardBackgroundColor(Color.parseColor(com.example.notes.R.color.ColorLightBlack.toString()))
        }

        if (arrList[position].webLink != ""){
            holder.tvWebLink.text = arrList[position].webLink
            holder.tvWebLink.visibility = View.VISIBLE
        }else{
            holder.tvWebLink.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
            listener!!.onClicked(arrList[position].id!!)
        }

    }

    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val tvTitle : TextView = view.findViewById(R.id.itemtvTitle)
        val tvDesc : TextView = view.findViewById(R.id.itemtvDesc)
        val tvDateTime : TextView = view.findViewById(R.id.itemtvDateTime)
        val cardView : CardView = view.findViewById(R.id.itemcardView)
        val tvWebLink : TextView = view.findViewById(R.id.itemtvWebLink)

    }


    interface OnItemClickListener{
        fun onClicked(noteId:Int)
    }

}