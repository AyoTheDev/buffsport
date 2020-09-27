package com.buffup.app.ui.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.buffup.sdk.model.Buff
import com.ayo.movies.di.ViewModelFactory
import com.ayo.movies.utils.Resource
import com.buffup.app.R
import com.buffup.app.ui.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//todo should use viewbinding
class FullscreenActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        observeViewModel()
        buff_view.setVideoPath("https://buffup-public.s3.eu-west-2.amazonaws.com/video/toronto+nba+cut+3.mp4")
        buff_view.setOnClickListener {
            buff_view.startVideo()
            viewModel.loadBuffs()
        }
    }

    private fun observeViewModel(){
        viewModel.buff.observe(this, Observer { handleBuff(it) })
    }

    private fun handleBuff(resource: Resource<Buff>) {
        when(resource){
            is Resource.Success -> buff_view.updateBuff(resource.data)
            is Resource.Failure -> handleFailure(resource)
        }
    }

    private fun handleFailure(resource: Resource.Failure<*>) {
        //todo show snackbar for failure
    }

}
