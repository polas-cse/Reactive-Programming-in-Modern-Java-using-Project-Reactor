package com.reactive.program.section12;

import com.reactive.program.exception.ReactorException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
public class ErrorHandiling {

    public Flux<String> onErrorReturn(){
       return Flux.fromIterable(List.of("A", "B"))
                .concatWith(Flux.error(new IllegalArgumentException("Exception...")))
                .onErrorReturn("C").log();
    }

    public Flux<String> onErrorResume(){
        return Flux.fromIterable(List.of("A", "B"))
                .concatWith(Flux.error(new RuntimeException()))
                .onErrorResume(error->{
                    log.error("Exception is ", error);
                    return Flux.just("C", "D");
               }).log();
    }

    public Flux<String> onErrorContinue(){
        return Flux.fromIterable(List.of("A", "B"))
                .map(c ->{
                    if(c.equals("B"))
                        throw new RuntimeException("Runtime Exception form map");
                    return c;
                })
                .concatWith(Flux.just("C"))
                .onErrorContinue((error, name)->{
                    log.error("Exception is ", error);
                    log.error("Name is {}", name);
                }).log();
    }

    public Flux<String> onErrorMap(){
        return Flux.fromIterable(List.of("A", "B"))
                .map(c ->{
                    if(c.equals("B"))
                        throw new RuntimeException("Runtime Exception form map");
                    return c;
                })
                .concatWith(Flux.just("C"))
                .onErrorMap((error)->{
                    log.error("Exception is ", error);
                    return new ReactorException(error, error.getMessage());
                }).log();
    }
}
