package com.verge.theverge.services

import com.verge.theverge.models.PurchaseModel
import com.verge.theverge.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun createPurchase(purchaseModel: PurchaseModel){
    purchaseRepository.save(purchaseModel)
    }

    fun updatePurchase(purchaseModel: PurchaseModel) {
    purchaseRepository.save(purchaseModel)
    }
}
