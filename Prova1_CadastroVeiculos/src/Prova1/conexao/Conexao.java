/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prova1.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author laboratorio
 */
public class Conexao {
    public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/bdveiculo?useTimezone=true&serverTimezone=UTC",
                    "root", "12345678");
            System.out.println("Conexao realizada com sucesso");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD "+e.getMessage());
            return null;
        }
    }
    
}
