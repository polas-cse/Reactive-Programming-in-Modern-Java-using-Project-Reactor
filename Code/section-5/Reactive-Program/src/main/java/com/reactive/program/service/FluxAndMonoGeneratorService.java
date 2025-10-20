package com.reactive.program.service;

import reactor.core.publisher.Flux;

import java.util.List;

public class FluxAndMonoGeneratorService {

    private Flux<String> getNames(){
        Flux<String> names = Flux.fromIterable(List.of("Polas","Rasel", "Naime", "Jakariya"));
        return names;
    }

    public static void main(String[] args) {

        FluxAndMonoGeneratorService obj = new FluxAndMonoGeneratorService();
        Flux<String> names = obj.getNames();

        System.out.println("Subscribe");
        names.filter(name-> name.length() <= 5)
                .subscribe(System.out::println);

        System.out.println("\nSubscribe");
        names.subscribe(name->{
            System.out.println("Name = " + name);
        });

    }

}
