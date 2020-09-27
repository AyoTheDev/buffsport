package com.buffup.sdk.ui

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.buffup.sdk.R
import kotlinx.android.synthetic.main.buff_question.view.*

class BuffQuestion @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    init {
        visibility = View.INVISIBLE
        elevation = 10f
        inflate(context, R.layout.buff_question, this)
    }

    fun dismissProgressBar(){
        question_time_progress.visibility = View.GONE
    }

    fun setQuestionText(question: String) {
        question_time_progress.visibility = View.VISIBLE
        question_text.text = question
    }

    fun setCountDownText(seconds: String) {
        question_time.text = seconds
    }
}