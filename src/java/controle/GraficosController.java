/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import modelo.Visita;

/**
 *
 * @author User
 */
@ManagedBean(name="mbGraficos")
public class GraficosController implements Serializable{
     private List<Visita> dados;
     
     public GraficosController(){
         dados = new ArrayList<Visita>();
         dados.add(new Visita(8, 10, 20));
         dados.add(new Visita(4, 19, 56));
         dados.add(new Visita(78, 150, 300));
         dados.add(new Visita(240, 100, 40));
         dados.add(new Visita(745, 444, 85));
         
     }

    public List<Visita> getDados() {
        return dados;
    }
     
     
}
