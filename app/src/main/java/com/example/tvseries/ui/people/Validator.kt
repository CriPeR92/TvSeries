package com.example.tvseries.ui.people

object Validator {

    fun validateInput(searchInput: String) : Boolean {
        return searchInput.isNotEmpty()
    }
}