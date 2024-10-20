package com.whistlewithme.alarm.resources

import javax.ws.rs.container.ContainerResponseFilter
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext

@Provider
class CORSFilter : ContainerResponseFilter {

    @Context
    private lateinit var requestContext: ContainerRequestContext

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        responseContext.headers.add("Access-Control-Allow-Origin", "*")
        responseContext.headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
        responseContext.headers.add("Access-Control-Allow-Credentials", "true")
        responseContext.headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
    }
}
