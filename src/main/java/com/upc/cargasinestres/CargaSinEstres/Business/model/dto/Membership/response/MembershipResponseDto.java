package com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * The SubscriptionResponseDto class represents the data transfer object of the Subscription class.
 * It contains fields related to the details of a subscription entity.
 * This class is used for subscription information when retrieving a subscription.
 * @author Grupo1
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponseDto {
    private Long id;
    private Company company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private String description;
}
