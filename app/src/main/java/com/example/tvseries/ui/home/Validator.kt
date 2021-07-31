package com.example.tvseries.ui.home

object Validator {

    fun validateInput(searchInput: String): Boolean {
        return searchInput.isNotEmpty()
    }
}