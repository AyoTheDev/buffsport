package com.buffup.sdk.ui

import android.R.attr
import android.content.Context
import android.os.CountDownTimer
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buffup.sdk.R
import com.buffup.sdk.model.Buff
import com.buffup.sdk.ui.adapter.AnswersAdapter


class BuffView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var countDownTimer: CountDownTimer

    private val adapter by lazy { AnswersAdapter(getAdapterListener()) }

    private val videoView by lazy { getChildAt(3) as VideoView }

    private val sender = BuffSender(context)
    private val question = BuffQuestion(context)
    private val answers = RecyclerView(context)

    init {
        setUpAnswers()
        setUpQuestion()
        setUpSender()
    }

    /**
     * public methods
     */

    fun updateBuff(buff: Buff) {
        sender.visibility = View.VISIBLE
        question.visibility = View.VISIBLE

        val timeToShow: Long = (buff.time_to_show * 1000).toLong()
        countDownTimer = object : CountDownTimer(timeToShow, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val countDownString = (millisUntilFinished / 1000).toString()
                question.setCountDown(countDownString)
            }

            override fun onFinish() {
                animateOut()
            }

        }
        val senderName = "${buff.author.first_name} ${buff.author.last_name}"
        sender.setName(senderName)
        question.apply {
            setQuestion(buff.question.title)
            setCountDown(buff.time_to_show.toString())
        }
        adapter.update(buff.answers)
        animateIn()
        countDownTimer.start()
    }

    fun setVideoPath(path: String) = videoView.setVideoPath(path)

    fun startVideo() = videoView.start()


    /**
     * Set up view components
     */

    private fun setUpSender() {
        sender.id = generateViewId()
        sender.dismissBuff = { animateOut() }
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        params.setMargins(15, attr.top, attr.right, 5)
        sender.layoutParams = params
        addView(sender)

        val set = ConstraintSet()
        set.clone(this)
        set.connect(sender.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(sender.id, ConstraintSet.BOTTOM, question.id, ConstraintSet.TOP)
        set.applyTo(this)
    }

    private fun setUpQuestion() {
        question.id = generateViewId()
        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(15, attr.top, attr.right, 5)
        question.layoutParams = params
        addView(question)

        val set = ConstraintSet()
        set.clone(this)
        set.connect(question.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(question.id, ConstraintSet.BOTTOM, answers.id, ConstraintSet.TOP)
        set.applyTo(this)
    }

    private fun setUpAnswers() {
        val layoutManager = LinearLayoutManager(context)
        answers.layoutManager = layoutManager
        answers.elevation = 10f
        answers.id = generateViewId()

        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.setMargins(15, attr.top, attr.right, 15)
        answers.layoutParams = params
        addView(answers)

        val set = ConstraintSet()
        set.clone(this)
        set.connect(answers.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(answers.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        set.applyTo(this)

        answers.adapter = adapter
    }

    /**
     * Handle animations
     */

    private fun animateIn(){
        val animation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.entry_anim)
        animation.fillAfter = true

        sender.startAnimation(animation)
        question.startAnimation(animation)
        answers.startAnimation(animation)
    }

    private fun animateOut(){
        val animation: Animation =
            AnimationUtils.loadAnimation(context, R.anim.exit_anim)
        animation.fillAfter = true

        sender.startAnimation(animation)
        question.startAnimation(animation)
        answers.startAnimation(animation)
    }

    /**
     * get answer listener
     */

    private fun getAdapterListener() : AnswersAdapter.Listener {
        return object : AnswersAdapter.Listener {
            override fun onClick(position: Int) {
                //todo should add a state to show which item is clicked
                countDownTimer.cancel()
                question.dismissProgressBar()

                val handler = Handler()
                handler.postDelayed({
                    animateOut()
                    handler.removeCallbacksAndMessages(null)
                }, 2000)
            }
        }
    }
}