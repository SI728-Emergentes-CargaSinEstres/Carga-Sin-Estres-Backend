package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * This class represents the Chat entity for CSE. The table name is chats. And the columns are:
 * <ul>
 *     <li>id - The id of the chat. </li>
 *    <li>user - The author of the chat. </li>
 *    <li>message - The content of a message in chat. </li>
 *    <li>dateTime - The date and time of the message. </li>
 *    <li>order - The order of the message. </li>
 *    <li>bookingHistory - The booking history. </li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The id of the chat.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The content of a message in chat.
     */
    @OneToMany(mappedBy = "chatId", cascade = CascadeType.ALL)
    private List<Message> messages;

    /**
     * The booking history.
     */
    @Column(name = "reservation_id")
    private Long reservationId;

}
