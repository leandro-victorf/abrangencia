package com.abrangencia.controller

import com.example.service.DefaultShippingCompanyService
import com.example.service.ShippingCompanyService
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

@MicronautTest
class ShippingCompanyControllerTest {

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Inject
    lateinit var service: ShippingCompanyService

    @MockBean(DefaultShippingCompanyService::class)
    fun mockService(): ShippingCompanyService {
        return mock()
    }

    @Test
    fun `test getById ShippingCompany`() {
        val response = client.toBlocking().exchange<String>("http://localhost:8080/shippingcompany/6189b2d1e3474e73a44c6d8a")
        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

    @Test
    fun `test getByPostalCode ShippingCompany` (){
        val response = client.toBlocking().exchange<String>("http://localhost:8080/shippingcompany/postal-code=09280650")
        Assertions.assertEquals(HttpStatus.OK, response.status)
    }
}