package com.alter.minimiro.frontend.handler

import com.alter.minimiro.backend.DaoManager
import org.slf4j.LoggerFactory
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
    private val logger = LoggerFactory.getLogger("com.alter.minimiro.frontend.handler.Handler")

    @POST
    @Path("create")
    fun createWidget(req: CreateRequest) : Response {
        logger.info("widget/create :: request :: $req")
        val widget = try {
             DaoManager.create(req.mapX, req.mapY, req.height, req.width, req.level)
        }
        catch (ex: Exception) {
            logger.error("", ex)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
        logger.info("widget/create :: response :: $widget")
        return Response.ok(widget).build()
    }

    @POST
    @Path("get-all")
    fun getAllWidget() : Response {
        logger.info("widget/get-all :: request")
        val widgetList = try {
            DaoManager.getAll()
        }
        catch (ex: Exception) {
            logger.error("", ex)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
        logger.info("widget/get-all :: response :: $widgetList")
        return Response.ok(widgetList).build()
    }

    @POST
    @Path("get-one")
    fun getWidgetById(req: GetOneRequest) : Response {
        logger.info("widget/get-one :: request :: $req")
        val widget = try {
            DaoManager.getOne(req.id)
        }
        catch (ex: Exception) {
            logger.error("", ex)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
        logger.info("widget/get-one :: response :: ${widget ?: "Виджет с таким id не найден"}")
        return Response.ok(widget).build()
    }

    @POST
    @Path("delete")
    fun deleteWidget(req: DeleteRequest) : Response {
        logger.info("widget/delete :: request :: $req")
        try {
            val result = DaoManager.delete(req.id)
            if (!result) {
                logger.info("widget/delete :: response :: Виджет с таким id не найден")
                return Response.ok("Виджет с таким id не найден").build()
            }
        }
        catch (ex: Exception) {
            logger.error("", ex)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
        logger.info("widget/delete :: response :: ok")
        return Response.ok().build()
    }

    @POST
    @Path("update")
    fun updateWidget(req: UpdateRequest) : Response {
        logger.info("widget/update :: request :: $req")
        try {
            val result = DaoManager.update(req.id, req.mapX, req.mapY, req.height, req.width, req.level)
            if (!result) {
                logger.info("widget/update :: response :: Виджет с таким id не найден")
                return Response.ok("Виджет с таким id не найден").build()
            }
        }
        catch (ex: Exception) {
            logger.error("", ex)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()
        }
        logger.info("widget/update :: response :: ok")
        return Response.ok().build()
    }

}