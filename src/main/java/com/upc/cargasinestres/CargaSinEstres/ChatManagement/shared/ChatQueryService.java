package com.upc.cargasinestres.CargaSinEstres.ChatManagement.shared;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.entity.Chat;

public interface ChatQueryService {

    public abstract Chat createChat(Long reservationId);

}

