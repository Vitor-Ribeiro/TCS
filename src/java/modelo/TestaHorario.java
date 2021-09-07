/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author User
 */
public class TestaHorario {
    public static void main(String[] args) {
        LocalTime horario = LocalTime.now();
        LocalDate data = LocalDate.now();
        System.out.println("Dia " + data.getDayOfMonth());       
        System.out.println("Dia " + data.getDayOfWeek());       
        System.out.println("Dia " + data.getDayOfYear());       
    }
}
