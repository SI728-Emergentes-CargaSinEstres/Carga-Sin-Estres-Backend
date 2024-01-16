package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * The SubscriptionRequestDto class represents the data transfer object of the Subscription class.
 * It contains fields related to the details of a subscription entity.
 * This class is used for subscription information when creating a subscription.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequestDto {
    private String firma;
    private String subscriptionType;
    private String paymentMethod;
}
