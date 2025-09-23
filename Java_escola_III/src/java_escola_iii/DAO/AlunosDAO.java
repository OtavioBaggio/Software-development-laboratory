/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_escola_iii.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java_escola_iii.beans.Alunos;
import java_escola_iii.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class AlunosDAO {
    private Conexao conexao;
    private Connection conn;

    public AlunosDAO(Conexao conexao, Connection conn) {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public AlunosDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Alunos getAlunosPorId(int idAluno){
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Alunos a = new Alunos();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setCurso(rs.getString("curso"));
                return a;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("Erro ao buscar aluno por ID: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Alunos> getAlunos(){
        String sql = "SELECT * FROM Alunos";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Alunos> listaAlunos = new ArrayList();
            
            while(rs.next()){
                Alunos a = new Alunos();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setCurso(rs.getString("curso"));
                listaAlunos.add(a);
            }
        }catch(SQLException ex){
            System.out.println("Erro ao consultar todos os alunos: " + ex.getMessage());
            return null;
        }
        return null;
    }
    
    public List<Alunos> getAlunosNome(String nome){
        String sql = "SELECT * FROM alunos WHERE nome LIKE ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery(); //Obtenho o retorno da consulta e armazeno no ResultSet
            List<Alunos> listaAlunos = new ArrayList();
            
            while(rs.next()){
                Alunos a = new Alunos();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setCurso(rs.getString("curso"));
                listaAlunos.add(a);
            }
            return listaAlunos;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar todos os alunos: " + ex.getMessage());
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
