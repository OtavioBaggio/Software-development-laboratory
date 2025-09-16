/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_escola_ii.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java_escola_ii.beans.Alunos;
import java_escola_ii.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class AlunosDAO {
    private Conexao conexao;
    private Connection conn;
    
    public AlunosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public Alunos getAlunos(int id){
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Alunos a = new Alunos();
            rs.first();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setIdade(rs.getInt("idade"));
            a.setCurso(rs.getString("curso"));
            return a;
        
        }catch(SQLException ex){
            System.out.println("Erro ao consultar pessoa: " + ex.getMessage());
            return null;
        }
        
    }
    
    public void inserir (Alunos a){
        String sql = "INSERT INTO alunos (nome, idade, curso) VALUES (?,?,?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getIdade());
            stmt.setString(3, a.getCurso());
            
            stmt.execute();
        }catch(Exception ex){
            System.out.println("Erro ao inserir pessoa: "+ex.getMessage());
        }        
    }
    
    public void editar(Alunos a){
        try{
            String sql = "UPDATE alunos set nome=?, idade=?, curso=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, String.valueOf(a.getIdade()));
            stmt.setString(3, a.getCurso());
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar aluno: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            // Primeiro, exclui todas as matrículas do aluno
            String sqlMat = "DELETE FROM matriculas WHERE id_aluno=?";
            PreparedStatement stmtMat = conn.prepareStatement(sqlMat);
            stmtMat.setInt(1, id);
            stmtMat.executeUpdate();

            // Depois, exclui o aluno
            String sql = "DELETE FROM alunos WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Aluno e matrículas excluídos com sucesso!");
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
}
