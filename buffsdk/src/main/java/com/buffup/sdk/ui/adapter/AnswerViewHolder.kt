package com.buffup.sdk.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.buffup.sdk.model.Answer
import kotlinx.android.synthetic.main.buff_answer.view.*

class AnswerViewHolder(itemView: View, private val listener: AnswersAdapter.Listener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bind(answer: Answer) {
        itemView.setOnClickListener(this)
        itemView.apply {
            answer_text.text = answer.title
        }
    }

    override fun onClick(v: View?) {
        listener.onClick(layoutPosition)
    }
}