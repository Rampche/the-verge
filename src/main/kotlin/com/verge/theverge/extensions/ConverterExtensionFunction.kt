package com.verge.theverge.extensions

import com.verge.theverge.controller.requests.customer.PostCustomerRequest
import com.verge.theverge.controller.requests.customer.PutCustomerRequest
import com.verge.theverge.controller.requests.employee.PostEmployeeRequest
import com.verge.theverge.controller.requests.employee.PutEmployeeRequest
import com.verge.theverge.controller.requests.order.PostOrderRequest
import com.verge.theverge.controller.requests.order.PutOrderRequest
import com.verge.theverge.controller.requests.reservation.PostReservationRequest
import com.verge.theverge.controller.requests.reservation.PutReservationRequest
import com.verge.theverge.controller.requests.tables.PostTableRequest
import com.verge.theverge.controller.requests.tables.PutTableRequest
import com.verge.theverge.controller.responses.CustomerResponse
import com.verge.theverge.enums.CustomerStatus
import com.verge.theverge.enums.EmployeeStatus
import com.verge.theverge.enums.RoleType
import com.verge.theverge.enums.TableStatus
import com.verge.theverge.models.*

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
return CustomerModel(
    name = this.name,
    email=this.email,
    password = this.password,
    status = CustomerStatus.ACTIVE
)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel ):CustomerModel {
    return CustomerModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        email=this.email ?: previousValue.email,
        password = this.password ?: previousValue.password,
        status = previousValue.status
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
        id = this.id!!,
        name = this.name,
        email = this.email,
        status = this.status!!
    )
}

fun PostEmployeeRequest.toEmployeeModel():EmployeeModel{
    return EmployeeModel(
        name = this.name,
        email=this.email,
        password = this.password
    )
}

fun PutEmployeeRequest.toEmployeeModel(previousValue: EmployeeModel ):EmployeeModel {
    return EmployeeModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        email=this.email ?: previousValue.email,
        password = this.password ?: previousValue.password,
        role = previousValue.role,
        status = previousValue.status
    )
}

fun PostTableRequest.toTableModel(): TableModel{
    return TableModel(
        id = this.id,
        number = this.number,
        capacity = this.capacity,
        status = TableStatus.EMPTY
    )
}

fun PutTableRequest.toTableModel(previousValue: TableModel): TableModel{
    return TableModel(
        id = previousValue.id,
        number = this.number ?: previousValue.number,
        capacity = this.capacity ?: previousValue.capacity,
        status = previousValue.status
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

fun PutReservationRequest.toReservationModel(previousTable: TableModel, previousCustomer: CustomerModel, previousValue: ReservationModel):ReservationModel{
    return ReservationModel(
        id = previousValue.id,
        date = this.date ?: previousValue.date,
        time = this.time ?: previousValue.time,
        partySize = this.partySize ?: previousValue.partySize,
        table = previousTable,
        customer = previousCustomer
    )
}

fun PostOrderRequest.toOrderModel(table: TableModel):OrderModel{
    return OrderModel(
        paymentStatus = this.paymentStatus,
        table = table

    )
}

fun PutOrderRequest.toOrderModel(previousTable: TableModel, previousValue: OrderModel):OrderModel{
    return OrderModel(
        id = previousValue.id,
        paymentStatus = this.paymentStatus ?: previousValue.paymentStatus,
        table = previousTable
    )
}
