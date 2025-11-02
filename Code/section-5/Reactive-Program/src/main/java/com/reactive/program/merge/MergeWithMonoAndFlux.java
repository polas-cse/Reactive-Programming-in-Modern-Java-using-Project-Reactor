package com.reactive.program.merge;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class MergeWithMonoAndFlux {

    public Flux<String> mergeWithFlux(){
        Flux<String> one = Flux.just("Polas","Naime")
                .delayElements(Duration.ofMillis(500));
        Flux<String> two = Flux.just("Ayesha","Jakiya")
                .delayElements(Duration.ofMillis(800));
        return Flux.merge(one, two).log();
    }

    public Flux<String> mergeWithMono(){
        Mono<String> one = Mono.just("Polas");
        Mono<String> two = Mono.just("Ayesha");
        return one.mergeWith(two).log();
    }

}
