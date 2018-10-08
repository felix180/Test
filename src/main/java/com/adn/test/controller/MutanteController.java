package com.adn.test.controller;

import com.adn.test.dto.ConsultaMutanteDTO;
import com.adn.test.dto.EstadisticaDTO;
import com.adn.test.service.MutanteService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@Api(name = "Mutante Service", description = "servicio para mutantes", group = "Test", visibility = ApiVisibility.PUBLIC, stage = ApiStage.RC)
@ApiVersion(since = "1.0")
@RestController
public class MutanteController {

    @Autowired
    private MutanteService mutanteService;

    @Value("${jsondoc.url}")
    private String url;

    @ApiMethod(description = "Redirecciona a la pagina de documentacion de la api")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView test() {
        return new ModelAndView( "redirect:/jsondoc-ui.html?url="+url+"#");
       // return new ModelAndView( "redirect:/jsondoc-ui.html?url=http://localhost:8080/jsondoc");
    }

    @ApiMethod(responsestatuscode ="200 - ok",description = "Se encarga de valudacion del adn NxN")
    @ApiErrors(apierrors={
            @ApiError(code="403", description="Forbidden")})
    @RequestMapping(value = "/mutant/", method = RequestMethod.POST)
    public  @ApiResponseObject ResponseEntity <Boolean> esMutante(@RequestBody @ApiBodyObject ConsultaMutanteDTO consultaMutanteDTO) {
        try{
            return new ResponseEntity<>(
                    mutanteService.isMutant(consultaMutanteDTO.getDna()) , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.FORBIDDEN);
        }

    }

    @ApiMethod(description = "Consulta estadisticas")
    @ApiErrors(apierrors={
            @ApiError(code="403", description="Forbidden")})
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public @ApiResponseObject
    ResponseEntity<EstadisticaDTO> estadistica() {
        try {
            return new ResponseEntity<EstadisticaDTO>(
                    mutanteService.getEstadisticas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
