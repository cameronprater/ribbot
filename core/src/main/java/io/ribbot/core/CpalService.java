package io.ribbot.core;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@RegisterRestClient
public interface CpalService {

    @POST
    @Produces("audio/opus")
    Multi<?> streamOpus();
}
