package com.reactive.program.concat;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ConcatWithFluxAndMonoTest {

    @Test
    void getCharsWithConcat(){
        ConcatWithFluxAndMono obj = new ConcatWithFluxAndMono();
        StepVerifier.create(obj.getCharsWithConcat())
                .expectNext("A","B").verifyComplete();
    }

}