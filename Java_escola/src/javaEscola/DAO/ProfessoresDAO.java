/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaEscola.DAO;

import java.sql.*;
import javaEscola.beans.professores;
import javaEscola.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class ProfessoresDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProfessoresDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    
    public void inserir (professores p){
        String sql = "INSERT INTO professores (nome, idade, disciplina) VALUES (?,?,?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getDisciplina());
            
            stmt.execute();
        }catch(Exception ex){
            System.out.println("Erro ao inserir pessoa: "+ex.getMessage());
        }        
    }
    
}
