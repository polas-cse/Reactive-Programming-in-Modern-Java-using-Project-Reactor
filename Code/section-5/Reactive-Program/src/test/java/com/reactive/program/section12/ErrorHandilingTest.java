package com.reactive.program.section12;

import com.reactive.program.exception.ReactorException;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class ErrorHandilingTest {

    ErrorHandiling eh = new ErrorHandiling();

    @Test
    void onErrorReturn() {
        StepVerifier.create(eh.onErrorReturn())
                .expectNext("A", "B", "C")
                .verifyComplete();
    }

    @Test
    void onErrorResume() {
        StepVerifier.create(eh.onErrorResume())
                .expectNext("A", "B", "C", "D")
                .verifyComplete();
    }

    @Test
    void onErrorContinue() {
        StepVerifier.create(eh.onErrorContinue())
                .expectNext("A", "C")
                .verifyComplete();
    }

    @Test
    void onErrorMap() {
        StepVerifier.create(eh.onErrorMap())
                .expectNext("A")
                .expectError(ReactorException.class)
                .verify();
    }
}