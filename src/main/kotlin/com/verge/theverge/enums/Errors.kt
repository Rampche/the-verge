package com.verge.theverge.enums

enum class Errors(val code: String, val message: String) {
    VG0001("VG-0001", "The table [%s] is already reserved"),
    VG0002("VG-0002", "Customer nº [%s] not found"),
    VG0003("VG-0003", "Table nº [%s] not found"),
    VG0004("VG-0004", "Employee nº [%s] not found"),
    VG0005("VG-0005", "Reservation nº [%s] not found"),
    VG0006("VG-0006", "Order nº [%s] not found"),
    VG0007("VG-0007", "[%s] employees not found"),
    VG0008("VG-0008", "[%s] customers not found"),
    VG0009("VG-0009", "Invoice nº [%s] not found"),
    VG0010("VG-0010", "OrderItem nº [%s] not found"),
    VG0011("VG-0011", "Item nº [%s] not found")

}