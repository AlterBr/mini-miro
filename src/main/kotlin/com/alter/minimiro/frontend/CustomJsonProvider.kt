package com.alter.minimiro.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CustomJsonProvider : JacksonJsonProvider() {

    companion object {
        private val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        private val mapper: ObjectMapper = ObjectMapper()
                .registerModule(ParameterNamesModule())
                .registerModule(JavaTimeModule()
                        .addDeserializer(LocalDate::class.java, LocalDateDeserializer(dateFormat))
                        .addSerializer(LocalDate::class.java, LocalDateSerializer(dateFormat))
                )
    }

    init {
        setMapper(mapper)
    }
}