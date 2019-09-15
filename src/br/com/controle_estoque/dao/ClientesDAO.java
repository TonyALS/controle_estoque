package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 * Esta classe contém os métodos que farão interação com a classe Clientes.
 */
public class ClientesDAO {
    
    private Connection con;
    
    //Para não precisar abrir a conexão em todos os métodos já inicializamos 
    //dentro do Construtor dentro da variável con:
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
            JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            
        }
    }
    
    //Método alterarCliente:
    public void alterarCliente(){
        
    }
    
    //Método excluirCliente:
    public void excluirCliente(){
        
    }
    
}
