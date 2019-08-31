package com.alter.minimiro.backend

import com.alter.minimiro.backend.entity.BaseWidget

object WidgetDataSet {
    private val storage = hashMapOf<String, BaseWidget>()

    fun getAll() = storage

    fun remove(id: String) = storage.remove(id)

    operator fun get(id: String) = storage[id]

    operator fun set(key: String, value: BaseWidget) {
        shiftLevel(value.level)
        storage[key] = value
    }

    fun shiftLevel(level: Int) {
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