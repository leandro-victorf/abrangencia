package com.example.service

import com.example.model.ShippingCompany

interface ShippingCompanyService {

    fun getById(id: String): ShippingCompany

    fun getListCompanies(): List<ShippingCompany>

    fun getByPostalCode(postalCode: String): ShippingCompany

    fun addCompany(company: ShippingCompany): ShippingCompany?

    fun updateById(id: String): ShippingCompany
}