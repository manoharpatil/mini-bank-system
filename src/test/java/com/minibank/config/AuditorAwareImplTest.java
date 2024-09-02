package com.minibank.config;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuditorAwareImplTest {

    @Test
    void testGetCurrentAuditor() {
        AuditorAwareImpl auditorAware = new AuditorAwareImpl();
        Optional<String> auditor = auditorAware.getCurrentAuditor();

        assertTrue(auditor.isPresent());
        assertEquals("system", auditor.get());
    }
}
