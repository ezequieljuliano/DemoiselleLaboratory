package org.demoiselle.jsfjpa.domain.enums;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN("Administrador"),
    GUEST("Convidado");

    private final String description;

    private Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
