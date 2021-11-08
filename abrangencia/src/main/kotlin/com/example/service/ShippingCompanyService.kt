package com.example.service

import com.example.model.ShippingCompany

interface ShippingCompanyService {

    fun getById(id: String): ShippingCompany?

    fun getListCompanies(): List<ShippingCompany>

    fun getByPostalCode(postalCode: String): List<ShippingCompany>

    fun addCompany(company: ShippingCompany)

    fun updateById(company: ShippingCompany)
    
    fun deleteCompanyById(company: ShippingCompany)
}