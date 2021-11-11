package com.example.repository

import com.example.model.ShippingCompany
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertOneResult
import com.mongodb.client.result.UpdateResult

interface ShippingCompanyRepository {
    fun getByShippingCompany(id: String): ShippingCompany?

    fun getShippingCompany(shippingCompany: String): List<ShippingCompany>

    fun getByPostalCodeShippingCompany(postalCode: String): List<ShippingCompany>

    fun addShippingCompany(company: ShippingCompany): InsertOneResult

    fun updateShippingCompany(company: ShippingCompany): UpdateResult

    fun deleteShippingCompany(id: String): DeleteResult
}
