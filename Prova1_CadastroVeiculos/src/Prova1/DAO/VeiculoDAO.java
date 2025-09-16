/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prova1.DAO;

import Prova1.beans.Veiculo;
import Prova1.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    /*
    public Veiculo getVeiculos(int id){
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Veiculo v = new Veiculo();
            rs.first();
            v.setId(rs.getInt("id"));
            v.setMarca(rs.getString("marca"));
            v.setModelo(rs.getString("modelo"));
            v.setAno(rs.getInt("ano"));
            v.setPlaca(rs.getString("placa"));
            v.setCor(rs.getString("cor"));
            return v;
        
        }catch(SQLException ex){
            System.out.println("Erro ao consultar veiculo: " + ex.getMessage());
            return null;
        }
        
    } */
    
    public void inserir (Veiculo v){
        String sql = "INSERT INTO veiculos (marca, modelo, ano, placa, cor) VALUES (?,?,?,?,?);";
        
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, v.getMarca());
            stmt.setString(2, v.getModelo());
            stmt.setInt(3, v.getAno());
            stmt.setString(4, v.getPlaca());
            stmt.setString(5, v.getCor());
            
            stmt.execute();
        }catch(Exception ex){
            System.out.println("Erro ao inserir veiculo: "+ex.getMessage());
        }        
    }
    
}
