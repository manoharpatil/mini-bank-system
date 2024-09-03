package com.minibank.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuditorAwareImplTest {

    @Test
    void testGetCurrentAuditor() {
        // Create mocks for SecurityContext and Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        // Set up mock behavior
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("testUser");

        // Set the mock SecurityContext in SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Create the AuditorAwareImpl instance
        AuditorAwareImpl auditorAware = new AuditorAwareImpl();

        // Execute the method under test
        Optional<String> auditor = auditorAware.getCurrentAuditor();

        // Verify the results
        assertTrue(auditor.isPresent());
        assertEquals("testUser", auditor.get());

        // Clean up the SecurityContextHolder
        SecurityContextHolder.clearContext();
    }
}
