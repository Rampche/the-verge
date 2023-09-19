package com.verge.theverge.controller

import com.verge.theverge.controller.mapper.PurchaseMapper
import com.verge.theverge.controller.requests.purchase.PostPurchaseRequest
import com.verge.theverge.services.PurchaseService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/purchases")
@RestController
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper

) {
    @Operation(summary = "Create a purchase", method = "GET")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest){
        purchaseService.createPurchase(purchaseMapper.toModel(request))
    }

}