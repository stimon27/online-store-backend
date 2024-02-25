package org.rimini;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public final class DefaultResource {

    /**
     * @return Welcome text
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World!";
    }
}
