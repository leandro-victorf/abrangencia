package com.example.service

import com.example.model.ShippingCompany
import jakarta.inject.Singleton
import java.util.*
import kotlin.collections.HashMap


@Singleton
class DefaultShippingCompany: ShippingCompanyService {

    private val companies = hashMapOf<String, ShippingCompany>()

    override fun getById(id: String): ShippingCompany {
        return ShippingCompany(id)
    }

    override fun getListCompanies(): List<ShippingCompany> {
        return companies
    }
    override fun getByPostalCode(postalCode: String): ShippingCompany {
        return ShippingCompany(postalCode)
    }

    override fun addCompany(company: ShippingCompany): ShippingCompany? {
        return companies.put(company.id, company)
    }

    override fun updateById(id: String) {
      val company =  getById(id)

    }
}


