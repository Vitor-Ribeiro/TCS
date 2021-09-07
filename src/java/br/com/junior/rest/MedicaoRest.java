/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.junior.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import modelo.Empresa;
import modelo.Medicao;


/**
 *
 * @author Silvio
 */
public class MedicaoRest {

    public List<Medicao> listar() {
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/medicao");        
        return webResource.path("/todas").get(new GenericType<List<Medicao>>() {});
    }
    
    public Medicao listarPorId(Long id) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/medicao");
        return webResource.path("/pesquisarPorId").path(id.toString()).get(new GenericType<Medicao>() {           
        });
    }
    
    public Medicao salvar(Medicao medicao){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/medicao");
        
        medicao = webResource.post(new GenericType<Medicao>() {           
        },medicao);
        if(medicao.getId()!= null){
            System.out.println("Salvou!");
        }
        return medicao;
    }
    
    
    
    public void atualizar(Medicao medicao){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/medicao");
        
        ClientResponse response = webResource.path(medicao.getId().toString()).put(ClientResponse.class, medicao);
        System.out.println(response.getStatus());
        if(response.getStatus() == 200){
            System.out.println("Alterou");
        }
         
    }
    
    public void excluir(Medicao medicao){
        Client client = Client.create();           
        WebResource webResource = client.resource("http://localhost:8090/medicao");
        
        webResource.path(medicao.getId().toString()).delete();
                
    }
    
    public static void main(String[] args) {
        MedicaoRest notaRest = new MedicaoRest();
        List<Medicao> medicoes = (List<Medicao>) notaRest.listar();
        System.out.println("medicao: " + medicoes.get(0));
        for (Medicao medicoe : medicoes) {
            System.out.println("id " + medicoe.getId());
            System.out.println("Unidade " + medicoe.getUnidade());
            System.out.println("Valor " + medicoe.getValor());
            System.out.println("");
        }
//        try {
//            Empresa empresa = new Empresa();
//            empresa.setNome("Senac7");
//            Endereco endereco = new Endereco();
//            endereco.setNumero("555");
//            endereco.setLogradouro("rua acb");
//            empresa.setEndereco(endereco);
//            //endereco.setEmpresa(empresa);
//            notaRest.salvar(empresa);
//        } catch (Exception e) {
//            System.out.println("XXXX" + e.getMessage());
//            
//        }
        
        
        
        
        //List<modelo.Produto> lista = notaRest.listar();
        //for (Produto nota : lista) {
            //System.out.println("ID: " + nota.getId());
           // System.out.println("Nome: " + nota.getNome());                     
           // System.out.println();
       // }
    }
}
