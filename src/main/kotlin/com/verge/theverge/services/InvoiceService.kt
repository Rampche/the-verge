package com.verge.theverge.services

import com.verge.theverge.enums.Errors
import com.verge.theverge.exception.NotFoundException
import com.verge.theverge.models.*
import com.verge.theverge.repository.InvoiceRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class InvoiceService(
    val invoiceRepository: InvoiceRepository
    ) {

    //Create a invoice
    fun createInvoice(invoice: InvoiceModel) {
        calcInvoiceTotal(invoice)
        invoiceRepository.save(invoice)
    }

    //List the invoices
    fun findInvoice(): List<InvoiceModel> {
        return invoiceRepository.findAll().toList()
    }

    //List invoices by id
    fun findInvoiceById(id: Int): InvoiceModel {
        return invoiceRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.VG0009.message.format(id), Errors.VG0009.code) }
    }

    //Update invoice
    fun updateInvoice(invoice: InvoiceModel) {
        if (!invoiceRepository.existsById(invoice.id!!)) {
            throw NotFoundException(Errors.VG0009.message.format(invoice.id), Errors.VG0009.code)
        }
        invoiceRepository.save(invoice)
    }

    //Delete the invoice
    fun deleteInvoice(id: Int) {
        if (!invoiceRepository.existsById(id)) {
            throw NotFoundException(Errors.VG0009.message.format(id), Errors.VG0009.code)
        }
        invoiceRepository.deleteById(id)
    }

    fun calcInvoiceTotal(invoice: InvoiceModel) {
        val orderItem = invoice.orderitem
        if (orderItem != null) {
            val itemPrice = orderItem.items?.price ?: BigDecimal.ZERO
            val quantity = orderItem.quantity
            val totalAmount = itemPrice.multiply(BigDecimal(quantity))
            invoice.total = totalAmount
        }
    }
}





