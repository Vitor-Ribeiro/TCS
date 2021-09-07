/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.junior.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import modelo.Sensor;

/**
 *
 * @author Silvio
 */
public class SensoresRest {

    public List<Sensor> listar() {
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/sensores");        
        return webResource.path("/todos").get(new GenericType<List<Sensor>>() {});
    }       
    
    public static void main(String[] args) {
        
        
       }
    }

