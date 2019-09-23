/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 */
public class ProdutosDAO {
    
    private Connection con;
    
    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public ProdutosDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarProduto(Produtos obj){
        try {
            
            String sql = "INSERT INTO tb_produtos (descricao,preco,qtd_estoque,for_id)"
                    + " VALUES (?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtdEstoque());
            
            //O quarto item é a chave estrangeira da tabela de fornecedores.
            //Como a coluna for_id recebe um int, vamos passar o id do fornecedor
            //para ser gravado junto com o produto:
            stmt.setInt(4, obj.getFornecedor().getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro: " + e); 
        }   
    }    
}
