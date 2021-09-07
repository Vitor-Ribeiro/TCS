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
public class SensorGraficoMonoxidoCarbono {
    private double mediaMonoxidoCarbono;
    private int dia;

    public SensorGraficoMonoxidoCarbono() {
    }

    public SensorGraficoMonoxidoCarbono(float mediaMonoxidoCarbono, int dia) {
        this.mediaMonoxidoCarbono = mediaMonoxidoCarbono;
        this.dia = dia;
    }

    public double getMediaMonoxidoCarbono() {
        return mediaMonoxidoCarbono;
    }

    public void setMediaMonoxidoCarbono(double mediaMonoxidoCarbono) {
        this.mediaMonoxidoCarbono = mediaMonoxidoCarbono;
    }   

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    
    
}
