package com.example.dinosaurs.ui.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinosaursPhoto(
    val name: String,
    val length: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
