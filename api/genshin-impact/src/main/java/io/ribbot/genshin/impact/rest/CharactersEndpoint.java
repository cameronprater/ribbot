package io.ribbot.genshin.impact.rest;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

import io.ribbot.genshin.impact.entity.Character;
import io.ribbot.genshin.impact.entity.Character.Sex;
import io.ribbot.genshin.impact.jdbi.CharacterDao;
import org.jboss.resteasy.reactive.RestQuery;
import org.jdbi.v3.core.Jdbi;

import io.ribbot.genshin.impact.entity.Element;
import io.ribbot.genshin.impact.entity.Nation;
import io.ribbot.genshin.impact.entity.Rarity;
import io.ribbot.genshin.impact.entity.WeaponType;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.converters.multi.MultiReactorConverters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Path("characters")
public class CharactersEndpoint {
    private final Jdbi jdbi;

    public CharactersEndpoint(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    public Multi<Character> getWhere(@RestQuery Rarity rarity, @RestQuery Element element, @RestQuery WeaponType weaponType,
                                     @RestQuery Sex sex, @RestQuery Nation nation) {
        return Multi.createFrom().converter(MultiReactorConverters.fromFlux(),
                Flux.defer(() -> Flux.fromIterable(jdbi.withExtension(CharacterDao.class,
                        dao -> dao.find(rarity, element, weaponType, sex, nation))))
                        .switchIfEmpty(Mono.error(NotFoundException::new)));
    }
}
