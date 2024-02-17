package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.request.MembershipRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Membership.response.MembershipResponseDto;

/**
 * Service interface for managing subscriptions.
 * This interface declares methods for creating and retrieving subscription information.
 * @author Grupo1*/
public interface IMembershipService {

    /**
     * Registers a new subscription for the specified company.
     * @param companyId The unique identifier of the company for which the subscription is registered.
     * @param membership The data for creating the subscription.
     * @return A SubscriptionResponseDto containing information about the registered subscription.
     */
    public abstract MembershipResponseDto createMembership(Long companyId, MembershipRequestDto membership);

    /**
     * Retrieves membership information for the specified company.
     * @param companyId The unique identifier of the company for which to retrieve the subscription.
     * @return A SubscriptionResponseDto containing information about the subscription, or null if not found.
     */
    public abstract MembershipResponseDto getMembershipByCompanyId(Long companyId);

    /*delete membership once it's over*/
}
