package com.alter.minimiro.backend

import com.alter.minimiro.backend.entity.BaseWidget
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.locks.ReentrantLock

object DaoManager {
    fun create(mapX: Int?, mapY: Int?, height: Int?, width: Int?, level: Int?) : BaseWidget {
        val id = UUID.randomUUID().toString()
        val widget = BaseWidget(id, LocalDateTime.now(), mapX ?: 0, mapY ?: 0, height ?: 10, width ?: 10, level ?: 0)
        WidgetDataSet[id] = widget
        return widget
    }

    fun getAll() : List<BaseWidget> {
        val result = arrayListOf<BaseWidget>()
        result.addAll(WidgetDataSet.getAll().map { it.value }.sortedBy { it.level })
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