package com.upc.cargasinestres.CargaSinEstres.ChatManagement.service;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.model.entity.Chat;

public interface IChatService {

    public abstract Long getChatByReservationId(Long reservationId);

    public abstract Chat createChat(Long companyId);

}
