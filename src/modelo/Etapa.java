/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Etapa {

    public String nombre;

    // tiempo por maceta o total
    public double tiempo;

    // cuantos operarios forman 1 recurso físico (2 en casi todo)
    public int operariosPorRecurso;

    // recursos disponibles (moldes, bowls, balanzas, trituradoras)
    public int recursosDisponibles;

    // true = usa recursos, false = secado
    public boolean usaRecursos;
    
    public boolean tiempoEsTotal;

    public Etapa(String nombre,double tiempo,int opr,int recursos,boolean usa,boolean total){
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.operariosPorRecurso = opr;
        this.recursosDisponibles = recursos;
        this.usaRecursos = usa;
        this.tiempoEsTotal=total;
    }
}
