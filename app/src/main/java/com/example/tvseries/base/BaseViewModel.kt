package com.example.tvseries.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tvseries.R

/**
 * BaseViewModel from wich all viewmodels will inherit
 */

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var errorMessage = application.getString(R.string.unexpected_error)
}