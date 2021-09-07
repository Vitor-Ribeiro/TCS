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


/**
 *
 * @author Silvio
 */
public class EmpresaRest {

    public List<Empresa> listar() {
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/empresa");        
        return webResource.path("/todas").get(new GenericType<List<Empresa>>() {});
    }
    
    public List<Empresa> listarPorNome(String nome) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/empresa");
        return webResource.path("/empresaPorNome").path(nome).get(new GenericType<List<Empresa>>() {           
        });
    }
    
    public Empresa listarPorId(Long id) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/empresa");
        return webResource.path("/pesquisarPorId").path(id.toString()).get(new GenericType<Empresa>() {           
        });
    }
    
    public Empresa salvar(Empresa empresa){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/empresa");
        //webResource.post(produto);
        empresa = webResource.post(new GenericType<Empresa>() {           
        },empresa);
        if(empresa.getId()!= null){
            System.out.println("Salvou!");
        }
        return empresa;
    }
    
    
    
    public void atualizar(Empresa empresa){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/empresa");
        //webResource.post(produto);
        ClientResponse response = webResource.path(empresa.getId().toString()).put(ClientResponse.class, empresa);
        System.out.println(response.getStatus());
        if(response.getStatus() == 200){
            System.out.println("Alterou");
        }
         
    }
    
    public void excluir(Empresa empresa){
        Client client = Client.create();           
        WebResource webResource = client.resource("http://localhost:8090/empresa");
        //webResource.post(produto);
        webResource.path(empresa.getId().toString()).delete();
                
    }
    
    public static void main(String[] args) {
        EmpresaRest notaRest = new EmpresaRest();
        List<Empresa> empresas = notaRest.listarPorNome("se");
        System.out.println("empresa: " + empresas.get(0).getNome());
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
