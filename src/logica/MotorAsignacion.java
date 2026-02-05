/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.*;

public class MotorAsignacion {

    int macetas;

    public MotorAsignacion(int macetas){
        this.macetas = macetas;
    }

    public ResultadoEtapa resolver(
            Etapa etapa,
            int Smin,
            int Smax,
            ResultadoEtapa anterior){

        int filas = Smax-Smin+1;

        // dk = recursos * operarios
        int dMin = etapa.usaRecursos ? etapa.operariosPorRecurso : 0;
        int dMax = etapa.usaRecursos ?
                etapa.recursosDisponibles * etapa.operariosPorRecurso : 0;

        int columnas = etapa.usaRecursos ?
                etapa.recursosDisponibles : 1;

        double[][] F = new double[filas][columnas];
        int[][] D = new int[filas][columnas];
        int[] S = new int[filas];

        for(int i=0;i<filas;i++){

            S[i]=Smin+i;

            for(int j=0;j<columnas;j++){

                int recursos = etapa.usaRecursos ? j+1 : 0;
                int d = etapa.usaRecursos ?
                        recursos*etapa.operariosPorRecurso : 0;

                D[i][j]=d;

                if(etapa.usaRecursos && d>S[i]){
                    F[i][j]=Double.POSITIVE_INFINITY;
                    continue;
                }

                double tiempo;

                if(!etapa.usaRecursos){
                    tiempo = etapa.tiempo + (macetas-1);
                }else{
                    if(etapa.tiempoEsTotal){
                        tiempo = etapa.tiempo / recursos;
                    }else{
                        tiempo = (macetas*etapa.tiempo) / recursos;
                    }
                }

                if(anterior==null){
                    F[i][j]=tiempo;
                }else{
                    int prev = buscar(anterior.S,S[i]-d);
                    if(prev<0){
                        F[i][j]=Double.POSITIVE_INFINITY;
                    }else{
                        F[i][j]=tiempo + minimo(anterior.F[prev]);
                    }
                }
            }
        }

        return new ResultadoEtapa(F,D,S);
    }

    int buscar(int[] a,int v){
        for(int i=0;i<a.length;i++)
            if(a[i]==v) return i;
        return -1;
    }

    double minimo(double[] f){
        double m=Double.POSITIVE_INFINITY;
        for(double x:f) m=Math.min(m,x);
        return m;
    }
}