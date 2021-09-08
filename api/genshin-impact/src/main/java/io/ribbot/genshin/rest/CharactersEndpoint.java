package io.ribbot.genshin.rest;

import io.ribbot.genshin.entity.Character;
import io.ribbot.genshin.entity.Character.Sex;
import io.ribbot.genshin.entity.Element;
import io.ribbot.genshin.entity.Nation;
import io.ribbot.genshin.entity.Rarity;
import io.ribbot.genshin.entity.WeaponType;
import io.ribbot.genshin.jdbi.CharacterDao;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.converters.multi.MultiReactorConverters;
import org.jboss.resteasy.reactive.RestQuery;
import org.jdbi.v3.core.Jdbi;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;

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
