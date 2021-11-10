package com.example.service

import com.example.model.Range
import com.example.model.ShippingCompany
import com.example.repository.ShippingCompanyRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton


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

        val orderRangeLists = company.ranges.sortedBy { it.start }
        val optmizeRanges = mutableListOf<Range>(orderRangeLists[0])

        for (i in 1 until orderRangeLists.size) {
            if (orderRangeLists[i].end >= optmizeRanges.last().start && orderRangeLists[i].end <= optmizeRanges.last().end) {
                continue
            } else if (orderRangeLists[i].end > optmizeRanges.last().end) {
                orderRangeLists[i].end.also { optmizeRanges.last().end = it }
            } else {
                optmizeRanges.add(orderRangeLists[i])
            }
        }
        return optmizeRanges
    }
}
