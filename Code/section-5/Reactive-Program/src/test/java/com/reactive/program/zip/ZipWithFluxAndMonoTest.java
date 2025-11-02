package com.reactive.program.zip;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ZipWithFluxAndMonoTest {
    ZipWithFluxAndMono obj = new ZipWithFluxAndMono();

    @Test
    void zipFlux() {
        StepVerifier.create(obj.zipFlux()).expectNext("A1X", "B2Y", "C3Z").verifyComplete();
    }

    @Test
    void zipMono() {
        StepVerifier.create(obj.zipMono()).expectNext("A1").verifyComplete();
    }
}