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
public class SensorGraficoHumidade {
    private double mediaHumidade;
    private int dia;

    public SensorGraficoHumidade() {
    }

    public SensorGraficoHumidade(float mediaHumidade, int dia) {
        this.mediaHumidade = mediaHumidade;
        this.dia = dia;
    }
    
    public double getMediaHumidade() {
        return mediaHumidade;
    }

    public void setMediaHumidade(double mediaHumidade) {
        this.mediaHumidade = mediaHumidade;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    
    
}
