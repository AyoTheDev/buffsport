package com.buffup.sdk.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.buffup.sdk.R
import com.buffup.sdk.utils.ImageLoaderUtils
import kotlinx.android.synthetic.main.buff_sender.view.*

class BuffSender @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var dismissBuff: () -> Unit

    init {
        visibility = View.INVISIBLE
        elevation = 10f
        inflate(context, R.layout.buff_sender, this)
        buff_close.setOnClickListener { dismissBuff() }
    }

    fun loadSenderProfileImage(path: String) {
        ImageLoaderUtils.loadImage(context, path, sender_image)
    }

    fun setName(name: String) {
        sender_name.text = name
    }
}