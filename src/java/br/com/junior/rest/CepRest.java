/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.junior.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import modelo.Endereco;

/**
 *
 * @author User
 */
public class CepRest {

    private Client client;
    private WebResource webResource;

    public Endereco pesquisarPorCep(String cep) {
        client = Client.create();
        webResource = client.resource("https://viacep.com.br/ws/");
        return webResource.path(cep).path("/json").get(new GenericType<Endereco>() {           
        });
    }       

    
    public static void main(String... args) {
        CepRest notaREST = new CepRest();

        Endereco endereco = notaREST.pesquisarPorCep("88110400");

        System.out.println("");
        System.out.println("Cep: " + endereco.getCep());
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Bairro: " + endereco.getBairro());
    }
}
