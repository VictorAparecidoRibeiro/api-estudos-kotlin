package com.acme.tour.controller

import com.acme.tour.dto.ClienteDTO
import com.acme.tour.dto.SimpleObject
import com.acme.tour.dto.Telefone
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JsonController {

    @GetMapping(value = ["/json"])
    fun getJson() = SimpleObject()

    @GetMapping(value = ["/cliente"])
    fun getCliente(): ClienteDTO{
        var telefone = Telefone("14", "999999999", "Celular")
        var cliente = ClienteDTO(1, "Victor", Date(), telefone)

        return cliente



    }
}