package com.example.service

import com.example.model.ShippingCompany
import com.example.repository.ShippingCompanyRepository
import com.mongodb.client.FindIterable
import jakarta.inject.Inject
import jakarta.inject.Singleton


@Singleton
class DefaultShippingCompanyService(@Inject private val shippingCompanyRepository: ShippingCompanyRepository) :
    ShippingCompanyService {

//    private val companies = hashMapOf<String, ShippingCompany>()

    override fun getById(id: String): ShippingCompany? {
        return shippingCompanyRepository.getByShippingCompany(id)
    }

    override fun getListCompanies(): List<ShippingCompany> {
        return shippingCompanyRepository.getShippingCompany("")
    }

    override fun getByPostalCode(postalCode: String): List<ShippingCompany> {
        return shippingCompanyRepository.getByPostalCodeShippingCompany(postalCode)
    //        return getListCompanies().filter {
//            it.ranges.any { range ->
//                range.start <= postalCode && range.end >= postalCode
//            }
//        }
    }

    override fun addCompany(company: ShippingCompany) {
        shippingCompanyRepository.addShippingCompany(company)
    }

    override fun updateById(company: ShippingCompany) {
        shippingCompanyRepository.updateShippingCompany(company)
    }

    override fun deleteCompanyById(id: String) {
        shippingCompanyRepository.deleteShippingCompany(id)
    }

//    fun optmizePostalCode(company: ShippingCompany): String {
//
//        val orderRangeLists = company.ranges.sortedBy { it.start }
//        var optmizeRanges = mutableListOf<String>()
//        var counter = 0
//
//        for (i in 1 until orderRangeLists.size) {
//            if (orderRangeLists[i - 1].end > orderRangeLists[i].start) {
//                otmizeRanges.add(counter, orderRangeLists[i - 1].start)
//                var range = orderRangeLists[i - 1].end
//
//                fun comparatorEnd(range: CharRange): Int {
//                    for (j in i until orderRangeLists.size) {
//                        if (orderRangeLists[i - 1].end >= orderRangeLists[j].end) {
//                            comparatorStart(range)
//                        } else {
//                            otmizeRanges.add(counter, orderRangeLists[j].end)
//                            return j.also { i = it }
//                        }
//                    }
//                }
//
//                fun comparatorStart(range: CharRange) {
//                    for (j in i until orderRangeLists.size) {
//                        if (orderRangeLists[i - 1].end >= orderRangeLists[j].end) {
//                            comparatorEnd(range)
//                        } else {
//                            otmizeRanges.add(counter, orderRangeLists[j].end)
//                        }
//                    }
//                }
//                counter++
//            }
//        }
//        return
//    }
}


