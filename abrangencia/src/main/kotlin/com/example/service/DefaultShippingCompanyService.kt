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

    override fun deleteCompanyById(company: ShippingCompany){
        shippingCompanyRepository.deleteShippingCompany(company)
    }

    fun otmizePostalCode(company: ShippingCompany): String {
       var satrtRange = company.ranges[0].start.toInt()
       var endRange = company.ranges[0].end.toInt()

        if(company.ranges[1].start.toInt() <= satrtRange){
            if (company.ranges[1].end.toInt() <= endRange && company.ranges[1].end.toInt() > satrtRange){
                satrtRange = company.ranges[1].start.toInt()
            }else {
                satrtRange = company.ranges[1].start.toInt()
                endRange = company.ranges[1].end.toInt()
            }
        } else if (company.ranges[1].start.toInt() > satrtRange && company.ranges[1].start.toInt() <= endRange){
            if (company.ranges[1].end.toInt() > endRange){
                endRange = company.ranges[1].end.toInt()
            }
        }
        return satrtRange.toString(); endRange.toString()
    }
}


