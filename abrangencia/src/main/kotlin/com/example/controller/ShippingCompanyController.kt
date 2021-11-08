package com.example.controller

import com.example.model.ShippingCompany
import com.example.service.ShippingCompanyService
import io.micronaut.http.annotation.*
import jakarta.inject.Inject

@Controller("/shippingcompany")
class ShippingCompanyController(@Inject private val shippingCompanyService: ShippingCompanyService) {

    @Get("/{id}")
    fun getById(id: String): ShippingCompany? {
        return shippingCompanyService.getById(id)
    }

    @Get
    fun getListCompanies(@QueryValue("postal-code", defaultValue = "") postalCode: String = ""): List<ShippingCompany> {
        if (postalCode.isEmpty()) {
            return shippingCompanyService.getListCompanies()
        }
        return shippingCompanyService.getByPostalCode(postalCode)

    }

    @Post
    fun addCompany(@Body company: ShippingCompany) {
        shippingCompanyService.addCompany(company)
    }

    @Put("/{id}")
    fun updateById(@Body company: ShippingCompany) {
        shippingCompanyService.updateById(company)
    }

    @Delete("/{id}")
    fun deleteCompanyById(company: ShippingCompany) {
        shippingCompanyService.deleteCompanyById(company)
    }

}