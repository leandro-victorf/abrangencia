package com.example.repository

import com.example.model.ShippingCompany

interface ShippingCompanyRepository {
    fun getByShippingCompany(shippingCompany: String): ShippingCompany?
}
