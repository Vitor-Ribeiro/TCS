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
public class SensorGraficoLuminosidade {
    private double mediaLuminosidade;
    private int dia;

    public SensorGraficoLuminosidade() {
    }

    public SensorGraficoLuminosidade(float mediaLuminosidade, int dia) {
        this.mediaLuminosidade = mediaLuminosidade;
        this.dia = dia;
    }

    public double getMediaLuminosidade() {
        return mediaLuminosidade;
    }

    public void setMediaLuminosidade(double mediaLuminosidade) {
        this.mediaLuminosidade = mediaLuminosidade;
    }      

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    
    
}
