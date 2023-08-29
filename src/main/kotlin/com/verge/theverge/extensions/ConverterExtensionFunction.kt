package com.verge.theverge.extensions

import com.verge.theverge.controller.requests.customer.PostCustomerRequest
import com.verge.theverge.controller.requests.customer.PutCustomerRequest
import com.verge.theverge.controller.requests.employee.PostEmployeeRequest
import com.verge.theverge.controller.requests.employee.PutEmployeeRequest
import com.verge.theverge.controller.requests.invoice.PostInvoiceRequest
import com.verge.theverge.controller.requests.invoice.PutInvoiceRequest
import com.verge.theverge.controller.requests.order.PostOrderRequest
import com.verge.theverge.controller.requests.order.PutOrderRequest
import com.verge.theverge.controller.requests.orderitem.PostOrderItemRequest
import com.verge.theverge.controller.requests.orderitem.PutOrderItemRequest
import com.verge.theverge.controller.requests.reservation.PostReservationRequest
import com.verge.theverge.controller.requests.reservation.PutReservationRequest
import com.verge.theverge.controller.requests.tables.PostTableRequest
import com.verge.theverge.controller.requests.tables.PutTableRequest
import com.verge.theverge.models.*

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
return CustomerModel(
    name = this.name,
    email=this.email,
    password = this.password
)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel ):CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        email=this.email ?: previousValue.email,
        password = this.password ?: previousValue.password
    )
}

//fun CustomerModel.toResponse() : CustomerResponse {
//    return CustomerResponse(
//        id = this.id!!,
//        name = this.name,
//        email = this.email,
//    )
//}

fun PostEmployeeRequest.toEmployeeModel():EmployeeModel{
    return EmployeeModel(
        name = this.name,
        email=this.email,
        password = this.password,
        role = this.role
    )
}

fun PutEmployeeRequest.toEmployeeModel(previousValue: EmployeeModel ):EmployeeModel {
    return EmployeeModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        email=this.email ?: previousValue.email,
        password = this.password ?: previousValue.password,
        role = this.role ?: previousValue.role
    )
}

fun PostTableRequest.toTableModel(): TableModel{
    return TableModel(
        id = this.id,
        number = this.number,
        capacity = this.capacity,
        status = this.status
    )
}

fun PutTableRequest.toTableModel(previousValue: TableModel): TableModel{
    return TableModel(
        id = previousValue.id,
        number = this.number ?: previousValue.number,
        capacity = this.capacity ?: previousValue.capacity,
        status = this.status ?: previousValue.status
    )
}

fun PostReservationRequest.toReservationModel(customer: CustomerModel, table: TableModel):ReservationModel{
    return ReservationModel(
        date = this.date,
        time = this.time,
        partySize = this.partySize,
        customer = customer,
        table = table
    )
}

fun PutReservationRequest.toReservationModel(previousValue: ReservationModel, previousTable: TableModel):ReservationModel{
    return ReservationModel(
        id = previousValue.id,
        date = this.date ?: previousValue.date,
        time = this.time ?: previousValue.time,
        partySize = this.partySize ?: previousValue.partySize,
        table = previousTable,
        customer = previousValue.customer
    )
}

fun PostOrderRequest.toOrderModel(employee: EmployeeModel, reservation: ReservationModel):OrderModel{
    return OrderModel(
        schedule = this.schedule,
        employee = employee,
        reservation = reservation,

    )
}

fun PutOrderRequest.toOrderModel(previousValue: OrderModel):OrderModel{
    return OrderModel(
        id = previousValue.id,
        schedule = this.schedule ?: previousValue.schedule,

    )
}

fun PostInvoiceRequest.toInvoiceModel(order: OrderModel, orderitem: OrderItemModel):InvoiceModel{
    return InvoiceModel(
        order = order,
        orderitem = orderitem,
        total = this.total
    )
}

fun PutInvoiceRequest.toInvoiceModel(previousValue: InvoiceModel, previousOrder: OrderModel, previousOrderItem: OrderItemModel):InvoiceModel{
    return InvoiceModel(
        id = previousValue.id,
        order = previousOrder,
        orderitem = previousOrderItem,
        total = this.total ?: previousValue.total
    )
}

fun PostOrderItemRequest.toOrderItemModel(items: ItemsModel?):OrderItemModel {
    return OrderItemModel(
        items = items,
        quantity = this.quantity
    )
}

fun PutOrderItemRequest.toOrderItemModel(previousValue: OrderItemModel, previousItem: ItemsModel?):OrderItemModel {
    return OrderItemModel(
        items = previousItem,
        quantity = this.quantity ?: previousValue.quantity


    )
}