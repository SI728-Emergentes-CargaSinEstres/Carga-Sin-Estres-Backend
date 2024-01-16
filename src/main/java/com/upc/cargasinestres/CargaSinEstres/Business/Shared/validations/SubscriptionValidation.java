package com.upc.cargasinestres.CargaSinEstres.Business.Shared.validations;

import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.request.SubscriptionRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.ValidationException;

/**
 * The SubscriptionValidation class provides methods for validating SubscriptionRequestDto objects.
 * It checks for the presence and validity of fields in a subscription request.
 */
public class SubscriptionValidation {
    public static void ValidateSubscription(SubscriptionRequestDto subscription){
       if (subscription.getPaymentMethod() == null || subscription.getPaymentMethod().isEmpty()){
           throw new ValidationException("El metodo de pago es obligatorio");
       }
       if(subscription.getPaymentMethod().length() != 16){
           throw new ValidationException("El metodo de pago no tiene 16 caracteres");
       }
    }
}
