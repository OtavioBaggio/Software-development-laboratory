/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaEscola.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Otávio Baggio
 */
public class Conexao {
    public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/escola?useTimezone=true&serverTimezone=UTC",
                    "root", "12345678");
            System.out.println("Conexao realizada com sucesso");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD "+e.getMessage());
            return null;
        }
    }
    
}
