package com.reactive.program.zip;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ZipWithFluxAndMono {

    public Flux<String> zipFlux(){
        Flux<String> oneFlux = Flux.just("A","B", "C");
        Flux<String> twoFlux = Flux.just("1","2", "3");
        Flux<String> threeFlux = Flux.just("X","Y", "Z");
        return Flux.zip(oneFlux, twoFlux, threeFlux)
                .map((res)-> res.getT1()+res.getT2()+res.getT3()).log(); // "A1X", "B2Y", "C3Z"
    }

    public  Mono<String> zipMono(){
        Mono<String> oneFlux = Mono.just("A");
        Mono<String> twoFlux = Mono.just("1");
        return Mono.zip(oneFlux, twoFlux, (one, two)-> one+two).log();
    }

}
