package com.example.controller

import com.example.model.PostalCode
import com.example.service.PostalCodeService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller("/postalcode")
class PostalCodeController(@Inject private val postalCodeService: PostalCodeService) {

    @Get("/{postalCode}")
    fun getPostalCode(postalCode: String): PostalCode? {

        return postalCodeService.getPostalCode(postalCode)
    }
}
