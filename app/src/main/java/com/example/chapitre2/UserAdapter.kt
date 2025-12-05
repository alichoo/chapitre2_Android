package com.example.chapitre2

import com.example.chapitre2.OnUserClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val userList: MutableList<ListContact.User>,
    private val listener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.user_name_textview)
        val editButton: ImageButton = view.findViewById(R.id.edit_button)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_button)

        init {
            // Lier les clics à l'interface
            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onEditClick(userList[position])
                }
            }
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDeleteClick(userList[position], position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleviw, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = userList[position].id.toString() +" "+userList[position].name
    }

    override fun getItemCount() = userList.size
}