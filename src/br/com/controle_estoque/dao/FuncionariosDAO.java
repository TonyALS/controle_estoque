/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Clientes;
import br.com.controle_estoque.model.Funcionarios;
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
public class FuncionariosDAO {
    
    private Connection con;
    
    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public FuncionariosDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Método cadastrar funcionário:
     public void cadastrarFuncionario(Funcionarios obj){
        
        try {
            
            //1º Passo: Criar a query SQL:
            String sql = "INSERT INTO tb_funcionarios (nome,rg,cpf,email,senha,"
                    + "cargo,nivel_acesso,telefone,celular,cep,endereco,numero,"
                    + "complemento,bairro,cidade,estado)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2º Passo: Conectar com o banco de dados e organizar a query sql:
            
            //Classe PreparedStatement é responsável por tratar os comandos sql:
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getEnderecoNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            
            //3º Executar o comando SQL:
            stmt.execute();
            //Fecha a conexão com o banco de dados:
            stmt.close();
            
            //Exibe um aviso de cadastrado com sucesso:
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            
        }
    }
     
     //Método listarTodosOsFuncionários:
    public List<Funcionarios> listarFuncionarios(){
        try {
            //1º Criar a lista:
            List<Funcionarios> lista = new ArrayList<>();
            
            //2º Criar query sql e executar:
            String sql = "SELECT * FROM tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            //Todo comando select salva o resultado da consulta no BD numa classe
            //chamada ResultSet, ela que utilizaremos para adicionar os itens à lista:
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setEnderecoNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
                
                lista.add(obj);
            }
            
            return lista;
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }  
}
