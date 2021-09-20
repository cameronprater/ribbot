package io.ribbot.genshin.impact.rest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

import io.ribbot.genshin.impact.dao.CharacterDao;
import org.jdbi.v3.core.Jdbi;

import io.ribbot.genshin.impact.entity.Character;
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
    public Uni<Character> getByName(String name) {
        // TODO capitalize each word in name, handle character aliases
        return Uni.createFrom().converter(UniReactorConverters.fromMono(),
                Mono.fromCallable(() -> jdbi.withExtension(CharacterDao.class, dao -> dao.findByName(name.toLowerCase())))
                        .flatMap(Mono::justOrEmpty)
                        .switchIfEmpty(Mono.error(NotFoundException::new)));
    }
}
