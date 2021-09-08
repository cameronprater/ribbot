package io.ribbot.genshin.impact.rest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

import org.jboss.resteasy.reactive.RestPath;
import org.jdbi.v3.core.Jdbi;

import io.ribbot.genshin.impact.entity.Character;
import io.ribbot.genshin.impact.jdbi.CharacterDao;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.converters.uni.UniReactorConverters;
import reactor.core.publisher.Mono;

@Path("character/{name}")
public class CharacterEndpoint {
    private final Jdbi jdbi;

    CharacterEndpoint(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    public Uni<Character> getByName(@RestPath String name) {
        return Uni.createFrom().converter(UniReactorConverters.fromMono(),
                Mono.fromCallable(() -> jdbi.withExtension(CharacterDao.class, dao -> dao.findByName(name.toLowerCase())))
                        .flatMap(Mono::justOrEmpty)
                        .switchIfEmpty(Mono.error(NotFoundException::new)));
    }
}
