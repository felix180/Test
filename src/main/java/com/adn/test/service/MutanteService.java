package com.adn.test.service;

import com.adn.test.dao.ExamenRepository;
import com.adn.test.dto.EstadisticaDTO;
import com.adn.test.entity.Examen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutanteService {

    @Autowired
    ExamenRepository examenRepository;

    /**
     * Posibilidades  dentro de la matrix
     */
    public final IJ[] cordenadasDeB =
            {
                    new IJ( 0, 1 ), new IJ( 0, -1 ), new IJ( 1, 0 ), new IJ( -1, 0 ),
                    new IJ( 1, 1 ), new IJ( 1, -1 ), new IJ( -1, 1 ), new IJ( -1, -1 )
            };

    /**
     * Metodo que valida si es humano o mutante
     * @param dna
     * @return
     */
    public  Boolean isMutant(String[] dna){

        String dna2d [][] = new String[dna.length][dna.length];
        for (int i = 0;i<dna.length ;i++) {
            for (int j = 0; j < dna.length; j++) {
                dna2d[i][j] = dna[i].substring(j,j+1);
            }

        }
        for (int i = 0;i<dna.length ;i++) {
            for (int j = 0; j < dna.length; j++) {
                for (IJ cordenada:cordenadasDeB) {
                    int count = 1;
                    if( busquedaPorCordenadas(i,j,cordenada,dna2d,count)){
                        examenRepository.save(new Examen(Arrays.toString(dna), true));
                        return  true;
                    }
                }

            }

        }
        examenRepository.save(new Examen(Arrays.toString(dna), Boolean.FALSE));
        return false ;
    }

    /**
     * Buscador y a que direccion se tiene que dirigir  desde un punto dado
     * @param i  posicion
     * @param j posicion
     * @param cordenada direccion de busqueda
     * @param dna2d matrix
     * @param count contador
     * @return
     */
    private  boolean  busquedaPorCordenadas(int i, int j, IJ cordenada, String[][] dna2d, int count) {
        int nuevaI =cordenada.i+i;
        int nuevaJ =cordenada.j+j;

        String letra = dna2d[i][j];
        if(nuevaI <0  || nuevaJ <0 || nuevaI > dna2d.length -1|| nuevaJ >dna2d.length-1){
            count = 0;
            return  false;
        }
        if(dna2d[nuevaI][nuevaJ].equals(letra)){
            count++;
            if (count > 3)
                return true;
        } else {
            return false;
        }

        return busquedaPorCordenadas(nuevaI,nuevaJ,cordenada,dna2d,count);
    }

    /**
     * Metodo que busca las estadisticas con sus count  y comparacion
     * @return
     */
    public EstadisticaDTO getEstadisticas() {
        EstadisticaDTO estadisticaDTO = new EstadisticaDTO();

        Examen busqueda = new Examen();
        busqueda.setMutante(Boolean.TRUE);
        Long mutantes = examenRepository.count(Example.of(busqueda));
        busqueda.setMutante(Boolean.FALSE);
        Long humanos = examenRepository.count(Example.of(busqueda));
        estadisticaDTO.setCount_human_dna(humanos.intValue());
        estadisticaDTO.setCount_mutant_dna(mutantes.intValue());
        return estadisticaDTO;
    }

    /**
     * Clase para las cordenadas
     */
    private  class IJ
    {
        int i, j;

        private IJ( int i, int j )
        {
            this.i = i;
            this.j = j;
        }
    }
}
