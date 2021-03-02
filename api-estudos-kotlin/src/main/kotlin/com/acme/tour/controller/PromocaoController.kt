package com.acme.tour.controller

import com.acme.tour.dto.ErrorMessageDTO
import com.acme.tour.dto.ResponseJson
import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(value=["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService


    @GetMapping(value=["/sayHello"])
    fun sayHello(): String{
        return "Hello World"
    }

    @GetMapping(value=["/{id}"])
    fun getById(@PathVariable id:Long): ResponseEntity<Any>{

        var promocao = this.promocaoService.getById(id)
        return if(promocao != null)
            return ResponseEntity(promocao, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessageDTO("Promoção não localizada ", "Promoção: ${id} não localizada" !! ), HttpStatus.NOT_FOUND)

    }

    @PostMapping()
    fun createPromocao(@RequestBody promocao: Promocao): ResponseEntity<ResponseJson>{
        this.promocaoService.create(promocao)
        val respostaJson = ResponseJson("OK", Date())
        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

    @DeleteMapping(value=["/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }

        return ResponseEntity(Unit, status)
    }

    @PutMapping(value=["/{id}"])
    fun update(@PathVariable id:Long, @RequestBody promocao: Promocao): ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(this.promocaoService.getById(id) != null){
            status = HttpStatus.ACCEPTED
            this.promocaoService.update(id, promocao)
        }

        return ResponseEntity(Unit, status)
    }

    @GetMapping()
    fun getByLocal(@RequestParam(required = false, defaultValue = "1") start: Int,
                   @RequestParam(required = false, defaultValue = "3") size: Int): ResponseEntity<List<Promocao>>{
        val listPromocoes = this.promocaoService.getAll(start, size)

        val status = if(listPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(listPromocoes, status)
    }

    @GetMapping("/count")
    fun count():ResponseEntity<Long>{
        return ResponseEntity.ok(this.promocaoService.count())

    }

    @GetMapping("/sort")
    fun sort(): ResponseEntity<List<Promocao>>{
        val listPromocoes = this.promocaoService.getAllSortedByLocal()

        val status = if(listPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(listPromocoes, status)

    }




}