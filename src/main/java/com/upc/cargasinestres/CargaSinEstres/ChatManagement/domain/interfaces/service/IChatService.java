package com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.interfaces.service;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.entity.Chat;

public interface IChatService {

    public abstract Long getChatByReservationId(Long reservationId);

    public abstract Chat createChat(Long companyId);

}
