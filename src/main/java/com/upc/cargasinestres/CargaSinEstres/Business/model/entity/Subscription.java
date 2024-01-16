package com.upc.cargasinestres.CargaSinEstres.Business.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This class represents the Subscription entity for CSE. The table name is subscriptions. And the columns are:
 * <ul>
 *     <li>id - The id of the subscription</li>
 *     <li>firma - The signature of the subscription</li>
 *     <li>idCompany - The id of the company that is subscribed</li>
 *     <li>subscriptionDate - The date of the subscription</li>
 *     <li>subscriptionType - The type of the subscription</li>
 *     <li>paymentMethod - The payment method of the subscription</li>
 * </ul>
 * @author Grupo1
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    /**
     * The id of the subscription.
     * This is a primary key.
     * This id is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The signature of the subscription.
     */
    @Column(name="firma", nullable = false)
    private String firma;

    /**
     * The company that is subscribed.
     * This is a foreign key.
     * This is a one to one relationship.
     */
    @OneToOne
    @JoinColumn(name="idCompany", nullable = false, foreignKey = @ForeignKey(name="FK_subscription_company"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Company company;

    /**
     * The date of the subscription.
     */
    @Column(name="subscription_date", nullable = false)
    private LocalDate subscriptionDate;

    /**
     * The type of the subscription.
     */
    @Column(name="subscription_type", nullable = false)
    private String subscriptionType;

    /**
     * The payment method of the subscription.
     */
    @Column(name="payment_method", nullable = false)
    private String paymentMethod;

    /*
    * id: any;
    firma: any;
    idCompany: any;
    subscriptionDate: any;
    subscriptionType: any;
    payment: {
      paymentMethod: any;
    };
    hiredCompany: {
      name: any;
      logo: any;
    };
    * */


}
