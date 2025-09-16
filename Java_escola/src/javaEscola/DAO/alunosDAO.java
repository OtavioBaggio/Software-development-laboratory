/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaEscola.DAO;

import java.sql.*;
import javaEscola.beans.alunos;
import javaEscola.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class alunosDAO {
    private Conexao Conexao;
    private Connection conn;

    public alunosDAO() {
        this.Conexao = new Conexao();
        this.conn = this.Conexao.getConexao();
    }
    
    public void inserir(alunos Alunos){
        String sql = "INSERT INTO Alunos (nome, idade, curso) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, Alunos.getNome());
            stmt.setInt(2, Alunos.getIdade());
            stmt.setString(3, Alunos.getCurso());
            
            
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao inserir aluno:" + ex.getMessage());
        }
    }
    
    
}
