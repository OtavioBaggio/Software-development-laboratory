/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_escola_iii.DAO;

import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import java_escola_iii.beans.Professores;
import java_escola_iii.conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Otávio Baggio
 */
public class ProfessoresDAO {
    private Conexao conexao;
    private Connection conn;

    public ProfessoresDAO(Conexao conexao, Connection conn) {
        this.conexao = new Conexao();
        this.conn = (Connection) this.conexao.getConexao();
    }

    public ProfessoresDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Professores getProfessorPorId(int idProfessor){
        String sql = "SELECT * FROM professores WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                Professores p = new Professores();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setDisciplina(rs.getString("disciplina"));
                return p;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("Erro ao buscar professor por ID: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Professores> getProfessores(){
        String sql = "SELECT * FROM professores";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Professores> listaProfessores = new ArrayList<>();
            
            while(rs.next()){
                Professores p = new Professores();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setDisciplina(rs.getString("disciplina"));
                listaProfessores.add(p);
            }
            return listaProfessores;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar todos os professores: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Professores> getProfessoresPorNome(String nome){
        String sql = "SELECT * FROM professores WHERE nome LIKE ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Professores> listaProfessores = new ArrayList<>();
            
            while(rs.next()){
                Professores p = new Professores();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setDisciplina(rs.getString("disciplina"));
                listaProfessores.add(p);
            }
            return listaProfessores;
        }catch(SQLException ex){
            System.out.println("Erro ao consultar professores por nome: " + ex.getMessage());
            return null;
        }
    }
    
    public void inserir(Professores p){
        String sql = "INSERT INTO professores (nome, idade, disciplina) VALUES (?,?,?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getDisciplina());
            
            stmt.execute();
        }catch(Exception ex){
            System.out.println("Erro ao inserir professor: " + ex.getMessage());
        }        
    }
    
    public void editar(Professores p){
        try{
            String sql = "UPDATE professores SET nome=?, idade=?, disciplina=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getIdade());
            stmt.setString(3, p.getDisciplina());
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar professor: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "DELETE FROM professores WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Professor excluído com sucesso!");
        }catch(SQLException ex){
            System.out.println("Erro ao excluir professor: " + ex.getMessage());
        }
    }
}
    

