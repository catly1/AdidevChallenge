package com.adidev.breakingbad.data

import kotlinx.serialization.Serializable

@Serializable
data class Character(val name: String, val img: String, val occupation: List<String>, val status: String, val nickname: String, val appearance: List<Int>): java.io.Serializable {
}