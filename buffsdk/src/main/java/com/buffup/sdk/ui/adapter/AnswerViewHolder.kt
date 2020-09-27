package com.buffup.sdk.ui.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.buffup.sdk.R
import com.buffup.sdk.model.Answer
import kotlinx.android.synthetic.main.buff_answer.view.*

class AnswerViewHolder(itemView: View, private val listener: AnswersAdapter.Listener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    lateinit var context: Context

    fun bind(answer: Answer) {
        context = itemView.context
        itemView.setOnClickListener(this)
        itemView.answer_text.text = answer.title
        itemView.answer_parent.background = ContextCompat.getDrawable(context, R.drawable.light_bg)
        itemView.answer_text.setTextColor(ContextCompat.getColor(context, R.color.test_color_dark))
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
        itemView.answer_parent.background = ContextCompat.getDrawable(context, R.drawable.dark_bg)
        itemView.answer_text.setTextColor(ContextCompat.getColor(context, R.color.test_color_light))
    }
}