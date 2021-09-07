/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDateTime;
import java.time.Period;

/**
 *
 * @author User
 */
public class AlertaIntervalo {

    public static void main(String[] args) {
//    LocalDateTime inicio = LocalDateTime.of(2017, 1, 25, 10, 00, 00);
//    LocalDateTime termino = LocalDateTime.of(2017, 12, 26, 11, 20, 15);
        LocalDateTime termino = LocalDateTime.now();
        if (termino.getHour() >= 17) {
            if(termino.getMinute() >= 31){
                System.out.println("Ar-Condicionado Ligado + produto.getSala()");
            }
        }
        if(termino.getDayOfWeek().getValue() != 7){
            //depois de consultar no banco e dar o alerta, mudar o atributo leituraAlerta no banco para true
            
        }
            
    }
}
