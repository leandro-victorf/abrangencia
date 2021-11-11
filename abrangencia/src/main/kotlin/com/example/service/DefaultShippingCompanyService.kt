package com.example.service

import com.example.model.Range
import com.example.model.ShippingCompany
import com.example.repository.ShippingCompanyRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlin.math.max


@Singleton
class DefaultShippingCompanyService(@Inject private val shippingCompanyRepository: ShippingCompanyRepository) :
    ShippingCompanyService {

    override fun getById(id: String): ShippingCompany? {
        return shippingCompanyRepository.getByShippingCompany(id)
    }

    override fun getListCompanies(): List<ShippingCompany> {
        return shippingCompanyRepository.getShippingCompany("")
    }

    override fun getByPostalCode(postalCode: String): List<ShippingCompany> {
        return shippingCompanyRepository.getByPostalCodeShippingCompany(postalCode)
    }

    override fun addCompany(company: ShippingCompany) {
        company.ranges = optmizePostalCode(company)
        shippingCompanyRepository.addShippingCompany(company)
    }

    override fun updateById(company: ShippingCompany) {
        company.ranges = optmizePostalCode(company)
        shippingCompanyRepository.updateShippingCompany(company)
    }

    override fun deleteCompanyById(id: String) {
        shippingCompanyRepository.deleteShippingCompany(id)
    }

    private fun optmizePostalCode(company: ShippingCompany): List<Range> {
        if (company.ranges.isEmpty()){
            return  company.ranges
        }

        val orderRangeLists = company.ranges.sortedBy { it.start }
        val optmizeRanges = mutableListOf(orderRangeLists[0])

        for (i in 1 until orderRangeLists.size) {
            if (orderRangeLists[i].start > optmizeRanges.last().end) {
                optmizeRanges.add(orderRangeLists[i])
            } else {
                optmizeRanges.last().end = max(orderRangeLists[i].end.toInt(), optmizeRanges.last().end.toInt()).toString()
            }
        }
        return optmizeRanges
    }
}
