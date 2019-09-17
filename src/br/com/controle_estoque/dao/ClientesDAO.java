package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Clientes;
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
 * Esta classe contém os métodos que farão interação com a classe Clientes.
 */
public class ClientesDAO {
    
    private Connection con;
    
    //Para não precisar abrir a conexão em todos os métodos, já inicializamos 
    //uma conexão dentro do próprio Construtor:
    public ClientesDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Método cadastrarCliente. Este método recebe um objeto do tipo Clientes 
    //para ser persistido no banco de dados:
    public void cadastrarCliente(Clientes obj){
        
        try {
            
            //1º Passo: Criar a query SQL:
            String sql = "INSERT INTO tb_clientes (nome,rg,cpf,email,telefone,celular,"
                    + "cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2º Passo: Conectar com o banco de dados e organizar a query sql:
            
            //Classe PreparedStatement é responsável por tratar os comandos sql:
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getEnderecoNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            
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
    
    //Método alterarCliente:
    public void alterarCliente(Clientes obj){
        
         try {
            
            //1º Passo: Criar a query SQL:
            String sql = "UPDATE tb_clientes SET nome=?, rg=?, cpf=?, email=?, telefone=?,"
                    + " celular=?, cep=?, endereco=?, numero=?, complemento=?,"
                    + " bairro=?, cidade=?, estado=? WHERE id=?";
            
            //2º Passo: Organizar a query sql:
            
            //Classe PreparedStatement é responsável por tratar os comandos sql:
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getEnderecoNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getUf());
            stmt.setInt(14, obj.getId());
            
            //3º Executar o comando SQL:
            stmt.execute();
            //Fecha a conexão com o banco de dados:
            stmt.close();
            
            //Exibe um aviso de alterado com sucesso:
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar: " + e);
            
        }    
    }
    
    //Método excluirCliente:
    public void excluirCliente(Clientes obj){
        
         try {
            
            //1º Passo: Criar a query SQL:
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            
            //2º Passo: Conectar com o banco de dados e organizar a query sql:
            
            //Classe PreparedStatement é responsável por tratar os comandos sql:
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            
            //3º Executar o comando SQL:
            stmt.execute();
            //Fecha a conexão com o banco de dados:
            stmt.close();
            
            //Exibe um aviso de excluído com sucesso:
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
            
        }  
    }
    
    //Método listarTodosOsClientes:
    public List<Clientes> listarClientes(){
        try {
            //1º Criar a lista:
            List<Clientes> lista = new ArrayList<>();
            
            //2º Criar query sql e executar:
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            //Todo comando select salva o resultado da consulta no BD numa classe
            //chamada ResultSet, ela que utilizaremos para adicionar os itens à lista:
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
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
    
    //Método buscar cliente por nome:
    public List<Clientes> buscaClientePorNome(String nome){
        try {
            //1º Criar a lista:
            List<Clientes> lista = new ArrayList<>();
            
            //2º Criar query sql com a busca:
            String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            //Todo comando select salva o resultado da consulta no BD numa classe
            //chamada ResultSet, ela que utilizaremos para adicionar os itens à lista:
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
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
