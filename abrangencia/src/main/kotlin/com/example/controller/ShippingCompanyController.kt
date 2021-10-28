package com.example.controller

import com.example.model.ShippingCompany
import com.example.service.ShippingCompanyService
import io.micronaut.http.annotation.*
import java.util.*

@Controller("/shippingcompany")
class ShippingCompanyController(private val shippingCompanyService: ShippingCompanyService) {

    @Get("/{id}")
    fun getById(id: String): ShippingCompany {
        return shippingCompanyService.getById(id)
    }

    @Get("/companies")
    fun getListCompanies(): MutableList<ShippingCompanyService> {
        return Arrays.asList(shippingCompanyService)
    }

    @Get("/{postalCode}")
    fun getByPostalCode(postalCode: String): ShippingCompany {
        return shippingCompanyService.getByPostalCode(postalCode)
    }

    @Post
    fun addCompany(company: ShippingCompany) {
        shippingCompanyService.addCompany(company)
    }

    @Put("/{id}")
    fun updateById(id: String) {
        shippingCompanyService.updateById(id)
    }

    @Delete("/")

}