package com.example.businessLogic.entities.enums;

public enum BookStatus {
    DISPONIBILE("Disponibile"),
    INPRESTITO("In prestito");

    private final String status;

    BookStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
