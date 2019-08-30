package com.alter.minimiro.frontend.handler

import com.alter.minimiro.backend.DataBase
import com.alter.minimiro.backend.entity.BaseWidget
import org.springframework.web.bind.annotation.RequestBody
import javax.annotation.ManagedBean
import javax.inject.Singleton
import javax.ws.rs.Consumes
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
    fun createWidget(req: CreateRequestData) : Response {
        val widget = BaseWidget(1, req.date, req.mapX, req.mapY, req.height, req.width, req.level)
        val db = DataBase.widgetList
        db.add(widget)
        return Response.ok(widget).build()
    }

    @POST
    @Path("get-all")
    fun getAllWidget() : Response {
        val result = arrayListOf<BaseWidget>()

        return Response.ok(result).build()
    }

    @POST
    @Path("get-one")
    fun getWidgetById() : Response {

        return Response.ok().build()
    }

    @POST
    @Path("delete")
    fun deleteWidget() : Response {

        return Response.ok().build()
    }

    @POST
    @Path("update")
    fun updateWidget() : Response {

        return Response.ok().build()
    }

}