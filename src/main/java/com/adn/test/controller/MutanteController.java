package com.adn.test.controller;

import com.adn.test.dto.ConsultaMutanteDTO;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(name = "Mutante Service", description = "servicio para mutantes", group = "Test", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0")
@RestController
public class MutanteController {

    @ApiMethod
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "hola";
    }

    @ApiMethod(responsestatuscode ="203 - FORBIDDEN")
    @RequestMapping(value = "/mutant/", method = RequestMethod.POST)
    public
    @ApiResponseObject
    ResponseEntity <Boolean> esMutante(@RequestBody @ApiBodyObject ConsultaMutanteDTO consultaMutanteDTO) {
        try{
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.FORBIDDEN);
        }

    }
}
