package com.verge.theverge.controller

import com.verge.theverge.services.EmployeeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    val employeeService: EmployeeService
) {

    @Operation(summary = "Admins route", method = "GET")
    @GetMapping("/report")
    fun report(): String {
        return "This is a report, only admins can see it"
    }

}