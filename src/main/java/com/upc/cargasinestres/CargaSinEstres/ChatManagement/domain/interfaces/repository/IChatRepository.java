package com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.interfaces.repository;

import com.upc.cargasinestres.CargaSinEstres.ChatManagement.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The IChatRepository interface extends JpaRepository for CRUD operations on Chat entities.
 * @author Grupo1
 * @version 1.0
 * */
@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {

    Chat findByReservationId(Long id);

}
