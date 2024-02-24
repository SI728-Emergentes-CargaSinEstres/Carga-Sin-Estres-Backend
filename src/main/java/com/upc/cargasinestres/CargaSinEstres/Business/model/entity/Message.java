package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "message_date", nullable = false)
    private LocalDateTime messageDate;

    @Column(name = "chat_id", nullable = false) //deberia ser many to one
    private Long chatId;

    //@ManyToOne
    //@JoinColumn(name = "chat_id", nullable = false)
    //private Chat chat;
}