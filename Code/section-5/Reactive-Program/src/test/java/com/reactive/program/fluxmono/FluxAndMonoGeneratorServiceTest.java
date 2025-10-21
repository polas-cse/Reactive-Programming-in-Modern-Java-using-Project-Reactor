package com.reactive.program.fluxmono;

import com.reactive.program.service.FluxAndMonoGeneratorService;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();


    @Test
    void fluxNames() {

        Flux<String> fluxNames = obj.getFluxNames();

        StepVerifier.create(fluxNames)
//                .expectNext("Polas","Rasel", "Naime", "Jakariya")
                .expectNext("Polas")
                .expectNextCount(3)
                .verifyComplete();

    }


    @Test
    void mapNames() {

        Flux<String> names = obj.getNamesFluxMap();
        StepVerifier.create(names).expectNext("POLAS", "RASEL").verifyComplete();

    }


    FluxAndMonoGeneratorService object = new FluxAndMonoGeneratorService();
    @Test
    void namesMono_map_filter() {
        Mono<String> name = object.namesMono_map_filter(4);
        StepVerifier.create(name).expectNext("ALEX").verifyComplete();
    }
}
