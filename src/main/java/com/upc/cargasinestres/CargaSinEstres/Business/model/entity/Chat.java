package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
@Table(name = "chat")
public class Chat {
    /**
     * The id of the chat.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The author of the chat.
     */
    @Column(name = "user", nullable = false)
    private String user;

    /**
     * The content of a message in chat.
     */
    @Column(name = "message", nullable = false)
    private String message;

    /**
     * The date and time of the message.
     */
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    /**
     * The order of the message.
     */
    @Column(name = "message_order", nullable = false)
    private int messageOrder;

    /**
     * The booking history.
     */
    @ManyToOne
    @JoinColumn(name = "bookingHistory_id")
    private BookingHistory bookingHistory;

}
