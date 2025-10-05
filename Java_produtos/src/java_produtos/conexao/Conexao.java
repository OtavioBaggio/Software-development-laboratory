/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_produtos.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Otávio Baggio
 */
public class Conexao {
    public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/produtos?useTimezone=true$serverTimezone=UTC","root","12345678");
            System.out.println("Conexao realizada com sucesso!!");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco de dados"+ex.getMessage());
            return null;
        }   
    }
    
}
