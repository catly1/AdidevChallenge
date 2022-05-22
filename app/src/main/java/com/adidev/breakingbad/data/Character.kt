package com.adidev.breakingbad.data

import kotlinx.serialization.Serializable

@Serializable
data class Character(val name: String, val img: String): java.io.Serializable {
}