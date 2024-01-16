package com.upc.cargasinestres.CargaSinEstres.Business.service;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.request.SubscriptionRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.response.SubscriptionResponseDto;

/**
 * Service interface for managing subscriptions.
 * This interface declares methods for creating and retrieving subscription information.
 * @author Grupo1*/
public interface ISubscriptionService {

    /**
     * Registers a new subscription for the specified company.
     * @param companyId     The unique identifier of the company for which the subscription is registered.
     * @param subscription  The data for creating the subscription.
     * @return A SubscriptionResponseDto containing information about the registered subscription.
     */
    public abstract SubscriptionResponseDto createSubscription(Long companyId, SubscriptionRequestDto subscription);

    /**
     * Retrieves subscription information for the specified company.
     * @param companyId The unique identifier of the company for which to retrieve the subscription.
     * @return A SubscriptionResponseDto containing information about the subscription, or null if not found.
     */
    public abstract SubscriptionResponseDto getSubscriptionByCompanyId(Long companyId);

}
