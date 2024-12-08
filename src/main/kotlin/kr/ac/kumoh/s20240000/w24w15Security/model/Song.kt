package kr.ac.kumoh.s20240000.w24w15Security.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "songs")
data class Song(
    @Id val id: String? = null,
    val title: String,
    val singer: String,
    val rating: Int,
    val lyrics: String
)