package io.ribbot.genshin.rest;

import io.ribbot.genshin.entity.Character;
import io.ribbot.genshin.jdbi.CharacterDao;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.uni.UniReactorConverters;
import org.jboss.resteasy.reactive.RestPath;
import org.jdbi.v3.core.Jdbi;
import reactor.core.publisher.Mono;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

@Path("character")
public class CharacterEndpoint {
    private final Jdbi jdbi;

    CharacterEndpoint(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Path("{name")
    @GET
    public Uni<Character> getByName(@RestPath String name) {
        return Uni.createFrom().converter(UniReactorConverters.fromMono(),
                Mono.fromCallable(() -> jdbi.withExtension(CharacterDao.class, dao -> dao.findByName(name)))
                        .flatMap(Mono::justOrEmpty)
                        .switchIfEmpty(Mono.error(NotFoundException::new)));
    }
}
