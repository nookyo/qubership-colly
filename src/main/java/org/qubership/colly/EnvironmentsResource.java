package org.qubership.colly;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.qubership.colly.data.Environment;

import java.util.List;

@Path("/environments")
public class EnvironmentsResource {
    @Inject
    EnvironmentStorage environmentStorage;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Environment> getEnvironments() {
        return environmentStorage.getEnvironments();
    }


    @Inject
    EnvironmentsLoader environmentsLoader;

    @GET
    @Path("/tick")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Environment> loadEnvironmentsManually() {
        return environmentsLoader.loadEnvironments();
    }
}

