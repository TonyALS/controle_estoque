/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Fornecedores;
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
public class ProdutosDAO {

    private Connection con;

    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarProduto(Produtos obj) {
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
    
    public List<Produtos> listarProdutos(){
        try {
            
            //1º Passo: Criar a lista:
            List<Produtos> lista = new ArrayList<>();
            
            //2º Passo: Criar o SQL que fará a seleção no BD:
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque,"
                    + " f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores"
                    + " AS f ON(p.for_id = f.id)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                //Salvamos um objeto do tipo Fornecedores dentro de um objeto do tipo
                //Produtos:
                obj.setFornecedor(f);
                
                lista.add(obj);
            }
            
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
    
    public void alterarProduto(Produtos obj) {
        try {

            String sql = "UPDATE tb_produtos SET descricao=?, preco=?,"
                    + " qtd_estoque=?, for_id=? WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtdEstoque());

            //O quarto item é a chave estrangeira da tabela de fornecedores.
            stmt.setInt(4, obj.getFornecedor().getId());
            
            //Onde recebemos o id da cláusula WHERE:
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
    
    public void excluirProduto(Produtos obj){
        try {
            
            String sql = "DELETE FROM tb_produtos WHERE id = ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    } 
    
    //Método listar produtos por nome:
    public List<Produtos> buscaProdutoPorNome(String nome){
        try {
            
            //1º Passo: Criar a lista:
            List<Produtos> lista = new ArrayList<>();
            
            //2º Passo: Criar o SQL que fará a seleção no BD:
            String sql = "SELECT p.id, p.descricao, p.preco, p.qtd_estoque,"
                    + " f.nome FROM tb_produtos AS p INNER JOIN tb_fornecedores"
                    + " AS f ON(p.for_id = f.id) WHERE p.descricao LIKE ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtdEstoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
                lista.add(obj);
            }
            
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }
}
