package com.example.service

import com.example.model.Range
import com.example.model.ShippingCompany
import com.example.repository.ShippingCompanyRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton


@Singleton
class DefaultShippingCompanyService(@Inject private val shippingCompanyRepository: ShippingCompanyRepository): ShippingCompanyService {

//    private val companies = hashMapOf<String, ShippingCompany>()

    override fun getById(id: String): ShippingCompany? {
        return shippingCompanyRepository.getByShippingCompany(id)
    }

    override fun getListCompanies(): List<ShippingCompany> {
        return shippingCompanyRepository.getShippingCompany("")
    }
    override fun getByPostalCode(postalCode: String): List<ShippingCompany> {
        return getListCompanies().filter {
            it.ranges.any {
                range ->  range.start <= postalCode && range.end >= postalCode
            }
        }
    }

    override fun addCompany(company: ShippingCompany) {
       shippingCompanyRepository.addShippingCompany(company)
    }

    override fun updateById(company: ShippingCompany){
        shippingCompanyRepository.updateShippingCompany(company)
    }

    override fun deleteCompanyById(id: String){
        shippingCompanyRepository.deleteShippingCompany(id)
    }

    fun otmizePostalCode(company: ShippingCompany): String {

        val orderRangeLists = company.ranges.sortedBy { it.start }
//        val orderRangeLists = sortedSetOf(mutableListOf(company.ranges)).toList()
//        passar para int para fazer a comparação

        for (i in 1 until orderRangeLists.size){
//            como chegar no start
            if (orderRangeLists[i-1].start > orderRangeLists[i].start){

            }
        }
    }
}


