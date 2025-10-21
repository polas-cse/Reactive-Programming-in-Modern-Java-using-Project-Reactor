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

    public Flux<String> getNamesFluxMap(){
        Flux<String> names = Flux.fromIterable(List.of("Polas", "Rasel"))
                .map(String::toUpperCase)
                .log();
        return names;
    }

    public Flux<String> getNamesFluxMapImmutable(){
        Flux<String> names = Flux.just("Polas", "Rasel").log();
        names.map(String::toUpperCase);
        return names;
    }

    public Flux<String> getFluxMapFilterName(int size){
        Flux<String> names = Flux.just("Polas", "Mim", "Naime", "Jakariya", "Jakir")
                .filter(name-> name.length() >= size)
                .map(name-> name +" -> " +name.length());
        return names;
    }

    public Mono<String> namesMono_map_filter(int stringLength){
        Mono<String> name = Mono.just("alex")
                .filter(n-> n.length() >= stringLength)
                .map(String::toUpperCase);
        return name;
    }

    public Flux<String> namesMono_flat_map_filter(int stringLength){
       Flux<String> names = Flux.just("Polas")
               .filter(n -> n.length() >= stringLength)
               .map(String::toUpperCase)
               .flatMap(this::getSplitWord);

        return names;
    }

    private Flux<String> getSplitWord(String name){
        String[] splitName = name.split("");
        return Flux.fromArray(splitName);
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();
        Flux<String> fluxNames = obj.getFluxNames();

//        System.out.println("Flux");
//        fluxNames.subscribe(name->{
//            System.out.println("Name = " + name);
//        });
//
//        System.out.println("\n\n\nMono");
//        Mono<String> monoNames = obj.getMonoNames();
//        monoNames.subscribe(name->{
//            System.out.println("Name = " + name);
//        });
//
//        System.out.println("\n\n\nFlux Map");
//        Flux<String> mapNames = obj.getNamesFluxMap();
//        mapNames.subscribe(name->{
//            System.out.println("Name = " + name);
//        });
//
//        System.out.println("\n\n\nFlux Immutable");
//        Flux<String> mapImmutableNames = obj.getNamesFluxMapImmutable();
//        mapImmutableNames.subscribe(name->{
//            System.out.println("Name = " + name);
//        });

//        System.out.println("\nFlux Filter");
//        Flux<String> filterName = obj.getFluxMapFilterName(5);
//        filterName.subscribe(name->{
//            System.out.println("Name = " + name);
//        });

        System.out.println("\nFlux Flat Map");
        Flux<String> filterFlatMap = obj.namesMono_flat_map_filter(5);
        filterFlatMap.subscribe(c->{
            System.out.println("Char = " + c);
        });

    }

}
