package com.adn.test.service;

import org.springframework.stereotype.Service;

@Service
public class MutanteService {
    public final IJ[] cordenadasDeB =
            {
                    new IJ( 0, 1 ), new IJ( 0, -1 ), new IJ( 1, 0 ), new IJ( -1, 0 ),
                    new IJ( 1, 1 ), new IJ( 1, -1 ), new IJ( -1, 1 ), new IJ( -1, -1 )
            };

    public  Boolean isMutant(String[] dna){




        String dna2d [][] = new String[dna.length][dna.length];
        for (int i = 0;i<dna.length ;i++) {
            for (int j = 0; j < dna.length; j++) {
                dna2d[i][j] = dna[i].substring(j,j+1);
                System.out.println(dna[i]);
            }

        }

        for (int i = 0;i<dna.length ;i++) {
            for (int j = 0; j < dna.length; j++) {
                for (IJ cordenada:cordenadasDeB) {
                    int count = 1;
                    if( busquedaPorCordenadas(i,j,cordenada,dna2d,count)){
                        return  true;
                    }
                }

            }

        }

        return false ;
    }

    private  boolean  busquedaPorCordenadas(int i, int j, IJ cordenada, String[][] dna2d, int count) {
        int nuevaI =cordenada.i+i;
        int nuevaJ =cordenada.j+j;

        String letra = dna2d[i][j];
        if(nuevaI <0  || nuevaJ <0 || nuevaI > dna2d.length -1|| nuevaJ >dna2d.length-1){
            return  false;
        }
        if(dna2d[nuevaI][nuevaJ].equals(letra)){
            count++;

        }
        if (count >3)
            return true;
        System.out.println(count);
        return busquedaPorCordenadas(nuevaI,nuevaJ,cordenada,dna2d,count);
    }

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