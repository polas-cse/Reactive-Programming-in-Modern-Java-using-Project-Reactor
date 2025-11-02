package com.reactive.program.merge;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MergeWithMonoAndFluxTest {

    @Test
    void mergeWithFlux() {
        MergeWithMonoAndFlux obj = new MergeWithMonoAndFlux();
        StepVerifier.create(obj.mergeWithFlux())
                .expectNext("Polas","Ayesha","Naime", "Jakiya").verifyComplete();
    }

    @Test
    void mergeWithMono() {
        MergeWithMonoAndFlux obj = new MergeWithMonoAndFlux();
        StepVerifier.create(obj.mergeWithMono())
                .expectNext("Polas","Ayesha").verifyComplete();
    }
}