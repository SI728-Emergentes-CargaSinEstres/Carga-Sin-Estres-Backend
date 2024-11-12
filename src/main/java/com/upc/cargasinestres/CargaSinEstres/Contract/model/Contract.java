package com.upc.cargasinestres.CargaSinEstres.Contract.model;

public class Contract {
    private Long userId;
    private Long amount;

    // Constructor
    public Contract(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}