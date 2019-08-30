package com.alter.minimiro.frontend.handler

import com.alter.minimiro.backend.DataBase
import com.alter.minimiro.backend.entity.BaseWidget
import java.time.LocalDate
import java.util.*
import javax.annotation.ManagedBean
import javax.inject.Singleton
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@ManagedBean
@Singleton
@Path("widget")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
class Handler {

    @POST
    @Path("create")
    fun createWidget(req: CreateRequest) : Response {
        val id = UUID.randomUUID().toString()
        val widget = BaseWidget(id, req.date, req.mapX, req.mapY, req.height, req.width, req.level)
        val db = DataBase.widgetList
        db[id] = widget
        return Response.ok(widget).build()
    }

    @POST
    @Path("get-all")
    fun getAllWidget() : Response {
        val result = arrayListOf<BaseWidget>()
        result.addAll(DataBase.widgetList.map { it.value }.sortedBy { it.level })
        return Response.ok(result).build()
    }

    @POST
    @Path("get-one")
    fun getWidgetById(req: ActionByIdRequest) : Response {
        val widget = DataBase.widgetList[req.id]
        return Response.ok(widget).build()
    }

    @POST
    @Path("delete")
    fun deleteWidget(req: ActionByIdRequest) : Response {
        DataBase.widgetList.remove(req.id)
        return Response.ok().build()
    }

    @POST
    @Path("update")
    fun updateWidget(req: UpdateRequest) : Response {
        val currentWidget = DataBase.widgetList[req.id] ?: return Response.ok("Такой виджет не найден").build()
        currentWidget.date = LocalDate.now()
        req.mapX?.let { currentWidget.mapX = it }
        req.mapY?.let { currentWidget.mapY = it }
        req.height?.let { currentWidget.height = it }
        req.width?.let { currentWidget.width = it }
        req.level?.let { currentWidget.level = it }
        return Response.ok().build()
    }

}