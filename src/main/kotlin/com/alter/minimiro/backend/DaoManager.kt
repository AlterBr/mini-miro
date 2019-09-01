package com.alter.minimiro.backend

import com.alter.minimiro.backend.entity.BaseWidget
import java.time.LocalDateTime
import java.util.*

object DaoManager {
    fun create(mapX: Int?, mapY: Int?, height: Int?, width: Int?, level: Int?) : BaseWidget {
        val id = UUID.randomUUID().toString()
        val widget = BaseWidget(id, LocalDateTime.now(), mapX ?: 0, mapY ?: 0, height ?: 10, width ?: 10, level ?: 0)
        WidgetDataSet[id] = widget
        return widget
    }

    fun getAll(lim: Int? = null) : List<BaseWidget> {
        val result = arrayListOf<BaseWidget>()
        val limit = when {
            lim == null -> 10
            lim < 1 -> 1
            lim > 500 -> 500
            else -> lim
        }
        result.addAll(WidgetDataSet.getAll().map { it.value }.sortedBy { it.level }.take(limit))
        return result
    }

    fun getOne (id: String) : BaseWidget? {
        return WidgetDataSet[id]
    }

    fun delete (id: String) : Boolean {
        WidgetDataSet[id] ?: return false
        WidgetDataSet.remove(id)
        return true
    }

    fun update(id: String, mapX: Int?, mapY: Int?, height: Int?, width: Int?, level: Int?) : Boolean {
        val currentWidget = WidgetDataSet[id] ?: return false
        val widget = BaseWidget(
                id = id,
                date = LocalDateTime.now(),
                mapX = mapX ?: currentWidget.mapX,
                mapY = mapY ?: currentWidget.mapY,
                height = height ?: currentWidget.height,
                width = width ?: currentWidget.width,
                level = level ?: currentWidget.level
        )
        WidgetDataSet[id] = widget
        return true
    }
}