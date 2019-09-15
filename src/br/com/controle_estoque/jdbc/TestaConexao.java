package br.com.controle_estoque.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        try {
            
            //Instancia uma nova ConnectionFactory para testar a conexão:
            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "Conectado com sucesso.");
            
        } catch (Exception e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Erro ao conectar "
                   + "com o banco de dados: " + e); 
        }
    }
}
