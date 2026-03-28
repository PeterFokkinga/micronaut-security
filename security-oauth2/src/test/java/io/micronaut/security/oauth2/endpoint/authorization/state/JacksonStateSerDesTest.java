package io.micronaut.security.oauth2.endpoint.authorization.state;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest(startApplication = false)
public class JacksonStateSerDesTest {
    @Inject
    private StateSerDes stateSerDes;

    @Test
    void roundtripTest() {
        var state = new DefaultState();
        state.setNonce("b8d45798-36d8-450e-8e5d-d5f56fe221df");
        state.setRedirectUri(URI.create("/ebrevet?pwa=standalone"));

        var serialized = stateSerDes.serialize(state);
        var check = stateSerDes.deserialize(serialized);

        assertNotNull(check);
        assertEquals(state.getNonce(), check.getNonce());
        assertEquals(state.getRedirectUri(), check.getRedirectUri());
    }
}
