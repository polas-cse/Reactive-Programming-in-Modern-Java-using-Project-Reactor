package com.reactive.program.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class FluxAndMono {

    private Flux<String> getNames(){
        Function<Flux<String>, Flux<String>> nameTrans = (name)-> name.map(String::toUpperCase);

        return Flux.fromIterable(List.of("Polas", "Naime", "Naime"))
                .transform(nameTrans);
    }

    private Mono<String> defaultIfEmpty(){
        return Mono.<String>empty()
                .defaultIfEmpty("default");
    }

    private Mono<String> switchIfEmpty(){
        return Mono.<String>empty()
                .switchIfEmpty(Mono.just("switched"));
    }



    public static void main(String[] args) {
        FluxAndMono obj = new FluxAndMono();

        Mono<String> d = obj.defaultIfEmpty();
        d.subscribe(System.out::println);

        Mono<String> s = obj.switchIfEmpty();
        s.subscribe(System.out::println);

    }
}
