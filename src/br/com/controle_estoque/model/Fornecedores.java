/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.model;

/**
 *
 * @author Tony
 */
public class Fornecedores extends Clientes{
    
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    //Sobrescrevemos o método toString para retornar somente o nome do fornecedor
    //assim no combobox de selecionar fornecedor dos produtos teremos somente o nome
    //e não a representação literal do objeto.
    @Override
    public String toString(){
        return this.getNome();
    }
}
