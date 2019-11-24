/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 */
public class ItemVendaDAO {
    
    private Connection con;

    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Método que cadastra itens:
    
    public void cadastraItem(ItemVenda obj){
        try {

            String sql = "INSERT INTO tb_itensvendas (venda_id,produto_id,"
                    + " qtd, subtotal)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    } 
}
