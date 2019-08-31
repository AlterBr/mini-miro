package com.alter.minimiro.backend

import com.alter.minimiro.backend.entity.BaseWidget
import java.util.*

object WidgetDataSet {
    private val storage = Collections.synchronizedMap(hashMapOf<String, BaseWidget>())

    fun getAll(): MutableMap<String, BaseWidget> = storage

    fun remove(id: String) = storage.remove(id)

    operator fun get(id: String) = storage[id]

    operator fun set(key: String, value: BaseWidget) {
        shiftLevel(value.level)
        storage[key] = value
    }

    private fun shiftLevel(level: Int) {
        val flat = storage.map { it.value }
        val ids = arrayListOf<String>()
        var currentLevel = level
        var currentObj = flat.firstOrNull { it.level == currentLevel }
        while (currentObj != null) {
            ids.add(currentObj.id)
            currentLevel = currentObj.level + 1
            currentObj = flat.firstOrNull { it.level == currentLevel }
        }
        ids.forEach { storage[it]!!.level++ }
    }

}