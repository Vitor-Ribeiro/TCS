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
import modelo.Produto;

/**
 *
 * @author Silvio
 */
public class ProdutoRest {

    public List<Produto> listar() {
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/produto");        
        return webResource.path("/todos").get(new GenericType<List<Produto>>() {});
    }
    
    public List<Produto> listarPorNome(String nome) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/produto");
        return webResource.path("/produtoPorNome").path(nome).get(new GenericType<List<Produto>>() {           
        });
    }
    
    public Produto listarPorId(Long id) {
        Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8090/produto");
        return webResource.path("/pesquisarPorId").path(id.toString()).get(new GenericType<Produto>() {           
        });
    }
    
    public Produto salvar(Produto produto){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/produto");
        //webResource.post(produto);
        produto = webResource.post(new GenericType<Produto>() {           
        },produto);
        if(produto.getId()!= null){
            System.out.println("Salvou!");
        }
        return produto;
    }
    
    public void atualizar(Produto produto){
        Client client = Client.create();
        
        WebResource webResource = client.resource("http://localhost:8090/produto");
        //webResource.post(produto);
        ClientResponse response = webResource.path(produto.getId().toString()).put(ClientResponse.class, produto);
        System.out.println(response.getStatus());
        if(response.getStatus() == 200){
            System.out.println("Alterou");
        }
         
    }
    
    public void excluir(Produto produto){
        Client client = Client.create();           
        WebResource webResource = client.resource("http://localhost:8090/produto");
        //webResource.post(produto);
        webResource.path(produto.getId().toString()).delete();
                
    }
    
    public static void main(String[] args) {
        ProdutoRest notaRest = new ProdutoRest();
        //Produto produto = notaRest.listarPorId(1L);
        //System.out.println("produto: " + produto.getNome());
        try {
            Produto produto = new Produto();
            produto.setNome("soni");
            produto.setSerial("855");
            produto.setVersao(2.4);
            produto.setFw_versao(23);
            produto.setId(4L);
            notaRest.salvar(produto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        
        
        
        
        //List<modelo.Produto> lista = notaRest.listar();
        //for (Produto nota : lista) {
            //System.out.println("ID: " + nota.getId());
           // System.out.println("Nome: " + nota.getNome());                     
           // System.out.println();
       // }
    }
}
