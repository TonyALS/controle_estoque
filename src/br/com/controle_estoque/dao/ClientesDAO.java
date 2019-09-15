package br.com.controle_estoque.dao;

import br.com.controle_estoque.jdbc.ConnectionFactory;
import br.com.controle_estoque.model.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
            String sql = "INSERT INTO tb_clientes (nome,rg,cpf,email,telefone,celular"
                    + "cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            //2º Passo: Conectar com o banco de dados e organizar a query sql:
            
            //Classe PreparedStatement é responsável por tratar os comandos sql
            //e executá-los:
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(0, obj.getNome());
            
            
        } catch (Exception e) {
        }
        
    }
    
    //Método alterarCliente:
    public void alterarCliente(){
        
    }
    
    //Método excluirCliente:
    public void excluirCliente(){
        
    }
    
}
