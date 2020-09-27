package com.buffup.sdk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buffup.sdk.R
import com.buffup.sdk.model.Answer

class AnswersAdapter(private val listener: Listener) : RecyclerView.Adapter<AnswerViewHolder>() {

    private var items = mutableListOf<Answer>()

    fun update(list: List<Answer>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.buff_answer, parent, false)
        return AnswerViewHolder(view, listener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    interface Listener {
        fun onClick(position: Int)
    }
}