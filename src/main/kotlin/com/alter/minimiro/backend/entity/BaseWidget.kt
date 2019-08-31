package com.alter.minimiro.backend.entity

import java.time.LocalDateTime

data class BaseWidget(val id: String, var date: LocalDateTime, var mapX: Int, var mapY: Int, var height: Int, var width: Int, var level: Int)