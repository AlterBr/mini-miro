package com.alter.minimiro.frontend.handler

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateRequest(
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

data class GetOneRequest(
        @get:JsonProperty
        val id: String
) { constructor() : this("") }
data class DeleteRequest(
        @get:JsonProperty
        val id: String
) { constructor() : this("") }

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