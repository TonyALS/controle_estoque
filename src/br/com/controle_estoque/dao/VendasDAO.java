/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Clientes;
import br.com.controle_estoque.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    //Método filtra vendas por data:
    public List<Vendas> listarVendasPorPeriodo(String dataInicio, String dataFim) {
        try {

            //1º Passo: Criar a lista:
            List<Vendas> lista = new ArrayList<>();

            //2º Passo: Criar o SQL que fará a seleção no BD:
            String sql = "SELECT v.id, v.data_venda, c.nome, v.total_venda,"
                    + " v.observacoes FROM tb_vendas AS v INNER JOIN tb_clientes"
                    + " AS c ON(v.cliente_id = c.id) WHERE v.data_venda"
                    + " BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataInicio);
            stmt.setString(2, dataFim);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("v.data_venda"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
        
                obj.setCliente(c);
                
                lista.add(obj);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
}
