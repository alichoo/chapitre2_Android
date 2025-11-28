package com.example.chapitre2

interface OnUserClickListener {
    fun onEditClick(user: ListContact.User)
    fun onDeleteClick(user: ListContact.User, position: Int)
}