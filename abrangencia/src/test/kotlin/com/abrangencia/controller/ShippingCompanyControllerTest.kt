package com.abrangencia.controller

import com.example.model.Range
import com.example.model.ShippingCompany
import com.example.service.DefaultShippingCompanyService
import com.example.service.ShippingCompanyService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*

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

    // body pré existente, simulando o que será devolvido pelo mock do service
    private val existentShippingCompany = ShippingCompany(
        id = ObjectId("6189b2d1e3474e73a44c6d8a"),
        name = "Shipping Company Test",
        slo = 1,
        ranges = listOf(Range("09280650", "09300500"))
        )
    // usada para fazer o veriify in post and put
    private val newShippingCompany = ShippingCompany(
        name = "Shipping Company Test",
        slo = 2,
        ranges = listOf(Range("09160000", "09880000"))
    )
//      pq deu errado(mas depois de corrigir o restamte)
//    private fun httpRequestShippingCompany() = Stream.of(
//       Arguments.of(HttpRequest.POST("http://localhost:8080/shippingcompany", "{}")),
//        Arguments.of(HttpRequest.POST("http://localhost:8080/shippingcompany", "{}"))
//    )

    @Test
    fun `test list of shippingCompany`(){
        //given
        whenever(service.getListCompanies()) doReturn listOf(existentShippingCompany)

        // when
        val response =
            client.toBlocking().exchange<ShippingCompany>("shippingcompany/")

        //then
        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

    @Test
    fun `test getById ShippingCompany`() {
        //given
        // dar um retorno esperado ao service mockado
        whenever(service.getById(anyString())) doReturn existentShippingCompany

        //when
        val response =
            client.toBlocking().exchange<ShippingCompany>("shippingcompany/6189b2d1e3474e73a44c6d8a")
            // exchange possibilita vários retornos, como status  body, já o retrive somente o status

        //then
        Assertions.assertEquals(HttpStatus.OK, response.status)

        // neste caso confere o corpo recebido com o esperado
//        Assertions.assertEquals(existentShippingCompany.toString(), response)
        // verify ajuda na verificação se a requisição esta sendo feita da maneita que deveria
        // no getById pode ser usado any, para qualquer valor tbn
        verify(service, times(1)).getById("6189b2d1e3474e73a44c6d8a")
    }



    @Test
    fun `test getByPostalCode ShippingCompany` (){
        // given
        whenever(service.getByPostalCode(anyString())) doReturn listOf(existentShippingCompany)

        //when
        val response = client.toBlocking().exchange<String>("/shippingcompany/?postal-code=09280650")

        //then
        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

    @Test
    fun `test to create new shipppingCompany`() {
        //given
        // usa-se o doNothing pq ao adicionar uma nova shipping company não ha nenhum return
        doNothing().`when`(service).addCompany(any())

        //when
        val response = client.toBlocking().exchange<String, String>(HttpRequest.POST(
            "/shippingcompany",
            """{"name": "Shipping Company Test",
                "slo": 2,
                "ranges": [
                    {
                        "start": "09160000",
                        "end": "09880000"
                    }
                ]
            }
            """.trimIndent()


        ))

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.status)
        verify(service, times(1)).addCompany(newShippingCompany)
    }

    @Test
    fun `test to update of shippingCompany`(){
        //given
        doNothing().`when`(service).updateById(existentShippingCompany)

        //when
        val response = client.toBlocking().exchange<String, String>(
            HttpRequest.PUT(
                "shippingcompany/6189b2d1e3474e73a44c6d8a",
                """{"name": "Shipping Company Test",
                "slo": 2,
                "ranges": [
                    {
                        "start": "09160000",
                        "end": "09880000"
                    }
                ]
            }
            """.trimIndent()
            )
        )

        //then
        Assertions.assertEquals(HttpStatus.OK, response.status)
        verify(service, times(1)).updateById(newShippingCompany)
    }
}
