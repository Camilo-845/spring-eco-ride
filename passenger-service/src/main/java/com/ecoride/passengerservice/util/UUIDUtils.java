package com.ecoride.passengerservice.util;

import java.util.UUID;

public class UUIDUtils {
    public static UUID toUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format: " + id);
        }
    }
}
