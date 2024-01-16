package com.upc.cargasinestres.CargaSinEstres.Business.controller;


import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.request.SubscriptionRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Business.model.dto.Subscription.response.SubscriptionResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Business.service.ISubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST Subscription controller
 * @author Grupo1
 * @version 1.0
 */
@Tag(name="Subscription Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;

    /**
     * Class constructor
     *
     * @param subscriptionService The subscription service implementation.
     */
    public SubscriptionController(ISubscriptionService subscriptionService){
        this.subscriptionService=subscriptionService;
    }

    /**
     * Handles the creation of a new subscription.
     *
     idCompany     * @param subscriptionRequestDto The request data for creating the subscription.
     * @return ResponseEntity with the created subscription response or an error status.
     */
    @PostMapping("/subscriptions/{idCompany}")
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@PathVariable Long idCompany, @RequestBody SubscriptionRequestDto subscriptionRequestDto){
        var res = subscriptionService.createSubscription(idCompany, subscriptionRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    /**
     * Handles the retrieval of subscription(s) by company ID.
     *
     * @param companyId The ID of the company for which subscriptions are retrieved.
     * @return ResponseEntity with the subscription response(s) or an error status.
     */
    @GetMapping("/subscriptions/{companyId}")
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable(name="companyId") Long companyId){
        var res = subscriptionService.getSubscriptionByCompanyId(companyId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
