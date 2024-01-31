package com.medicines.distribution.model;

import java.util.UUID;

public class TokenValidator {

    private boolean isFree;
    private UUID token;

    public TokenValidator(boolean isFree, UUID token) {
        this.isFree = isFree;
        this.token = token;
    }

    public boolean isFree() {
        return isFree;
    }

    public UUID getToken() {
        return token;
    }
}
