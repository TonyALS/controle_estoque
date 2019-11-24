package br.com.controle_estoque.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Tony
 */
public class ConnectionFactory {
    
    private String caminho_nomeBd = "jdbc:mysql://127.0.0.1/bdvendas";
    private String usuario = "root";
    private String senha = "12345";
    
    
    //Importamos a classe Connection da biblioteca java.sql.Connection
    public Connection getConnection(){
        try {
            //Retorna uma conexão com as credenciais repassadas:
            return DriverManager.getConnection(caminho_nomeBd, usuario, senha);
        
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
    }
}
