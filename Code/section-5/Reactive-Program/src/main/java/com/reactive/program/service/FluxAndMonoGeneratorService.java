package com.reactive.program.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> getFluxNames(){
        Flux<String> names = Flux.fromIterable(List.of("Polas","Rasel", "Naime", "Jakariya")).log();
        return names;
    }

    public Mono<String> getMonoNames(){
        Mono<String> names = Mono.just("Polas").log();
        return names;
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();
        Flux<String> fluxNames = obj.getFluxNames();

        System.out.println("Flux");
        fluxNames.subscribe(name->{
            System.out.println("Name = " + name);
        });

        System.out.println("\n\n\nMono");
        Mono<String> monoNames = obj.getMonoNames();
        monoNames.subscribe(name->{
            System.out.println("Name = " + name);
        });

    }

}
