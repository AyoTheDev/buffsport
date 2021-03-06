package com.buffup.sdk.model

data class Buff(
    val answers: List<Answer>,
    val author: Author,
    val client_id: Int,
    val created_at: String,
    val id: Int,
    val language: String,
    val priority: Int,
    val question: Question,
    val stream_id: Int,
    val time_to_show: Int
)