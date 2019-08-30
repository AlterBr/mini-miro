package com.alter.minimiro.frontend.handler

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class CreateRequest(
        @get:JsonProperty
        val date: LocalDate,

        @get:JsonProperty
        val mapX: Int,

        @get:JsonProperty
        val mapY: Int,

        @get:JsonProperty
        val height: Int,

        @get:JsonProperty
        val width: Int,

        @get:JsonProperty
        val level: Int
)

data class ActionByIdRequest(@get:JsonProperty val id: String)

data class UpdateRequest(
        @get:JsonProperty
        val id: String,

        @get:JsonProperty
        val mapX: Int?,

        @get:JsonProperty
        val mapY: Int?,

        @get:JsonProperty
        val height: Int?,

        @get:JsonProperty
        val width: Int?,

        @get:JsonProperty
        val level: Int?
)