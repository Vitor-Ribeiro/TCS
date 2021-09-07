/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.com.junior.rest.SensoresRest;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Sensor;

/**
 *
 * @author Silvio
 */
@ManagedBean(name = "sensoresC")
@ViewScoped
public class SensoresControle {

    private Sensor sensores;  
    private DataModel<Sensor> modelSensores;

    public DataModel<Sensor> getModelSensores() {
        return modelSensores;
    }

    public void setModelSensores(DataModel<Sensor> modelSensores) {
        this.modelSensores = modelSensores;
    }
    private SensoresRest sensoresRest;    
    
    
    

    public String listarTodosSensores() {
        sensoresRest = new SensoresRest();
        modelSensores = new ListDataModel(sensoresRest.listar());
        return "";
    }

    

    

//    getters e setters 
    
    
    
    

    public Sensor getSensores() {
        return sensores;
    }

    public void setSensores(Sensor sensores) {
        this.sensores = sensores;
    }

    public SensoresRest getSensoresRest() {
        return sensoresRest;
    }

    public void setSensoresRest(SensoresRest sensoresRest) {
        this.sensoresRest = sensoresRest;
    }
    

    

    
    
    

}
