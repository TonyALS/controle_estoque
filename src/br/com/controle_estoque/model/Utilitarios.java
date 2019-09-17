/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tony
 */
public class Utilitarios {
    
    //Método para limpar os campos do formulário assim que alterarmos ou inserirmos
    //um novo cliente:
    
    public void limpaTela(JPanel container){
        Component componentes[] = container.getComponents();
        
        for(Component component : componentes){
            if(component instanceof JTextField){
                ((JTextField) component).setText(null);
            }
        } 
    }  
}
