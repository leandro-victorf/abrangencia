package com.example.service

import com.example.model.ShippingCompany
import com.mongodb.client.FindIterable

interface ShippingCompanyService {

    fun getById(id: String): ShippingCompany?

    fun getListCompanies(): List<ShippingCompany>

    fun getByPostalCode(postalCode: String): List<ShippingCompany>

    fun addCompany(company: ShippingCompany)

    fun updateById(company: ShippingCompany)
    
    fun deleteCompanyById(id: String)
}