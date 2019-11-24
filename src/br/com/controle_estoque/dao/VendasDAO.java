/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 */
public class VendasDAO {
    
    private Connection con;

    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Cadastrar venda:
    public void cadastrarVenda(Vendas obj){
        try {

            String sql = "INSERT INTO tb_vendas (cliente_id,data_venda,"
                    + " total_venda, observacoes)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
      
    //Retorna a última venda:
    public int retornaUltimaVenda(){
        try {
            int idVenda = 0;
            String sql = "SELECT MAX(id) id FROM tb_vendas";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Vendas p = new Vendas();
                p.setId(rs.getInt("id"));
                
                idVenda = p.getId();
            }
            return idVenda;
    
        } catch (Exception e) {
            throw new RuntimeException(e); 
        } 
    }  
}
