/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.ItemVenda;
import br.com.controle_estoque.model.Produtos;
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

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    } 
    
    //Listar itens vendidos por cada id de venda:
    public List<ItemVenda> listaItensPorVenda(int vendaId) {
        try {
            //1º Passo: Criar a lista:
            List<ItemVenda> lista = new ArrayList<>();

            //2º Passo: Criar o SQL que fará a seleção no BD:
            String sql = "SELECT i.id, p.descricao, i.qtd, p.preco, i.subtotal"
                    + " FROM tb_itensvendas AS i INNER JOIN tb_produtos AS p"
                    + " ON(i.produto_id = p.id) WHERE i.venda_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendaId);
   
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
                
                item.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);
               
                lista.add(item);
            }
            return lista;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
}
