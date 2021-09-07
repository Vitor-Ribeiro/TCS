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
public class SensorGraficoGasToxico {
    private double mediaGasToxico;
    private int dia;

    public SensorGraficoGasToxico() {
    }

    public SensorGraficoGasToxico(float mediaGasToxico, int dia) {
        this.mediaGasToxico = mediaGasToxico;
        this.dia = dia;
    }    

    public double getMediaGasToxico() {
        return mediaGasToxico;
    }

    public void setMediaGasToxico(double mediaGasToxico) {
        this.mediaGasToxico = mediaGasToxico;
    }             

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    
    
}
