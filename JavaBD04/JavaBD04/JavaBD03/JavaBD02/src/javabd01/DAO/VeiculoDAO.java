/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabd01.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import beans.Veiculo;
import javabd01.conexao.Conexao;

/**
 *
 * @author laboratorio
 */
public class VeiculoDAO {
    private Conexao conexao;
    private Connection conn;
    
    public VeiculoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Veiculo veiculo){
        String sql = "INSERT INTO veiculo (modelo, placa, id_pessoa) VALUES (?,?,?)";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getPessoaid().getId());
            
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao inserir pessoa:" + ex.getMessage());
        }
    }
    
    public void editar(Veiculo veiculo){
        try{
            String sql = "UPDATE veiculo set modelo=?, placa=?, id_pessoa=? WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getPessoaid().getId());
            stmt.setInt(4, veiculo.getId());
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
    
    public void excluir(int id){
        try{
            String sql = "delete from veiculo WHERE id=?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar pessoa: " + ex.getMessage());
        }
    }
    
}
