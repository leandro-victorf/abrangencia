package com.abrangencia.controller

import com.example.service.DefaultPostalCodeService
import com.example.service.PostalCodeService
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

// Inicializa o micronaut, tbm inicializa o service
@MicronautTest
class PostalCodeTest {

    // cria um client http para ser usado no test
    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    // Cria o mock para depois ser injetado, por isso o @Inject e o lateinit
    @Inject
    lateinit var service: PostalCodeService

    //Simula a service
    @MockBean(DefaultPostalCodeService::class)
    fun mockService(): PostalCodeService{
        return mock()
    }

    @Test
    fun `test get postalCode`() {
        val request: HttpRequest<Any> = HttpRequest.GET("postalcode/09280650")
        val body = client.toBlocking().retrieve(request)
        assertNotNull(body)
        assertEquals("Hello World", body)
    }
}