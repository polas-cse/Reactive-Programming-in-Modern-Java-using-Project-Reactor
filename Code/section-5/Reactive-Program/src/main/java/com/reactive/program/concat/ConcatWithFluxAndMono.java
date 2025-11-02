package com.reactive.program.concat;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConcatWithFluxAndMono {

    public Flux<String> getCharsWithConcat(){
        Mono<String> char1 = Mono.just("A");
        Mono<String> char2 = Mono.just("B");
        return char1.concatWith(char2).log();
    }

}
