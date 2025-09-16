/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_escola_ii.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java_escola_ii.beans.Professores;
import java_escola_ii.conexao.Conexao;

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
    
    public Professores getProfessores(int id){
        String sql = "SELECT * FROM professores WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Professores p = new Professores();
            rs.first();
            p.setId(id);
            p.setNome(rs.getString("nome"));
            p.setDisciplina(rs.getString("disciplina"));
            return p;
        
        }catch(SQLException ex){
            System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
            return null;
        }
    }
    
    public void inserir (Professores p){
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
    
    public void editar(Professores p){
        try{
            String sql = "UPDATE professores set nome=?, idade=?, disciplina=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, String.valueOf(p.getIdade()));
            stmt.setString(3, p.getDisciplina());
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar aluno: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "delete from professores WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar professor: " + ex.getMessage());
        }
    }
}
