package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
public class MembershipRequestDto {
    private String name;
    private Date startDate;
    private String cardNumber;
}
