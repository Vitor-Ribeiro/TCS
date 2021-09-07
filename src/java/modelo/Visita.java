/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author User
 */
public class Visita {   
    private int mes;
    private int manha;
    private int tarde;
    
    public Visita(){
    }

    public Visita(int mes, int manha, int tarde) {
        this.mes = mes;
        this.manha = manha;
        this.tarde = tarde;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getManha() {
        return manha;
    }

    public void setManha(int manha) {
        this.manha = manha;
    }

    public int getTarde() {
        return tarde;
    }

    public void setTarde(int tarde) {
        this.tarde = tarde;
    }

    
    
    
}
