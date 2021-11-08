package com.example.service

import com.example.model.ShippingCompany
import jakarta.inject.Singleton


@Singleton
class DefaultShippingCompanyService: ShippingCompanyService {

    private val companies = hashMapOf<String, ShippingCompany>()

    override fun getById(id: String): ShippingCompany? {
        return companies.get(id)
    }

    override fun getListCompanies(): List<ShippingCompany> {
        return companies.values.toList()
    }
    override fun getByPostalCode(postalCode: String): List<ShippingCompany> {
        return getListCompanies().filter {
            it.ranges.any {
                range ->  range.start <= postalCode && range.end >= postalCode
            }
        }
    }

    override fun addCompany(company: ShippingCompany) {
       if (!companies.containsKey(company.id)){
           company.id?.let { companies.put(it, company) }
       }
    }
//    pensei em usar o any para conferir todos

    override fun updateById(company: ShippingCompany){
        if (companies.containsKey(company.id)){
            company.id?.let { companies.put(it, company) }
        }
//        else retornar erro
    }

    override fun deleteCompanyById(company: ShippingCompany){
        if (companies.containsKey(company.id)){
            companies.remove(company.id, company)!!
        }
    }

    fun otmizePostalCode(company: ShippingCompany){
       var satrtRange = company.ranges[0].start.toInt()
       var endRange = company.ranges[0].end.toInt()
       var aux = 0


    }
}


