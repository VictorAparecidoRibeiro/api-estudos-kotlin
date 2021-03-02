package com.acme.tour.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ClienteDTO (@JsonProperty("matricula")val id: Long, val nome : String, val dataNascimento : Date, var telefone: Telefone)

data class Telefone (val ddd: String = "", val numero: String = "", val tipo: String= "")
