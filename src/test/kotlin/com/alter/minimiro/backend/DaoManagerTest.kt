package com.alter.minimiro.backend

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*

internal class DaoManagerTest {
    private var firstId = ""

    @BeforeEach
    fun beforeEach() {
        DaoManager.create(5, 7 ,5, 15, 0).width
        firstId = DaoManager.getAll().firstOrNull()!!.id
    }

    @AfterEach
    fun afterEach() {
        DaoManager.getAll().map { it.id }.forEach { DaoManager.delete(it) }
        firstId = ""
    }

    @Test
    fun create() {
        val widget = DaoManager.create(5, 8 ,7, 10, 3)
        assertArrayEquals(
                arrayOf(5, 8, 7, 10, 3),
                arrayOf(widget.mapX, widget.mapY, widget.height, widget.width, widget.level)
        )
    }

    @Test
    fun getAll() {
        assertEquals(
                1,
                DaoManager.getAll().size
        )
    }

    @Test
    fun getOne() {
        assertEquals(
                5,
                DaoManager.getOne(firstId)!!.height
        )
    }

    @Test
    fun getOneException() {
        assertThrows<KotlinNullPointerException> {
            DaoManager.getOne("${firstId}0")!!
        }
    }

    @Test
    fun update() {
        assertEquals(
                true,
                DaoManager.update(firstId, 7, null, 7, null, 5)
        )
        val widget = DaoManager.getOne(firstId)!!
        assertArrayEquals(
                arrayOf(7, 7, 7, 15, 5),
                arrayOf(widget.mapX, widget.mapY, widget.height, widget.width, widget.level)
        )
    }

    @Test
    fun delete() {
        assertEquals(
                true,
                DaoManager.delete(firstId)
        )
        assertEquals(
                0,
                DaoManager.getAll().size
        )
    }

    @Test
    fun checkShift() {
        val widget2 = DaoManager.create(2, 2 ,5, 5, 0)
        val widget3 = DaoManager.create(5, 5 ,3, 3, 0)
        assertEquals(
                3,
                DaoManager.getAll().size
        )
        assertEquals(
                2,
                DaoManager.getOne(firstId)!!.level
        )
        assertEquals(
                1,
                DaoManager.getOne(widget2.id)!!.level
        )
        assertEquals(
                0,
                DaoManager.getOne(widget3.id)!!.level
        )
    }

}