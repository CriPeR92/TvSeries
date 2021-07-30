package com.example.tvseries.ui.episode

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.model.Episode

class EpisodeViewModel(application: Application) : BaseViewModel(application) {
    var episode = MutableLiveData<Episode>()
}