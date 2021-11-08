package com.example.repository

import com.example.model.ShippingCompany
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult

interface ShippingCompanyRepository {
    fun getByShippingCompany(shippingCompany: String): ShippingCompany?

    fun getShippingCompany(shippingCompany: String): List<ShippingCompany>

    fun addShippingCompany(company: ShippingCompany): InsertOneResult

    fun updateShippingCompany(company: ShippingCompany): UpdateResult

    fun deleteShippingCompany(company: ShippingCompany): DeleteResult
}
