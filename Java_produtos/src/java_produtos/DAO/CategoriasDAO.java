/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_produtos.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java_produtos.beans.Categorias;
import java_produtos.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class CategoriasDAO {
    private Conexao conexao;
    private Connection conn;

    
    public CategoriasDAO(){
        this.conexao =  new Conexao();
        this.conn = this.conexao.getConexao();
    }
   
    public void inserir(Categorias c){
        try {
            String sql = "INSERT INTO categorias(nome) VALUES (?);";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO AO INSERIR CATEGORIA : "+ex.getMessage());
        }
    }
   
    public void editar(Categorias c){
        try {
            String sql = "UPDATE categorias SET nome=? WHERE id=?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("ERRO AO EDITAR CATEGORIA : "+ex.getMessage());
        }
    }
    
   public List<Categorias> getCategorias(){
        try {
            String sql = "SELECT * FROM categorias";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Categorias>listaCategorias = new ArrayList();
            while (rs.next()){
                Categorias c = new Categorias();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                listaCategorias.add(c);
            }
            return listaCategorias;
            
        } catch (SQLException ex) {
            System.out.println("ERRO AO CONSULTAS TODAS AS CATEGORIA : "+ex.getMessage());
            return null;
        }
   }
   
    public Categorias getCategorias(int id){
        try {
            String sql = "SELECT * FROM categorias WHERE id =?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Categorias c = new Categorias();
            rs.first();
            
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            
            return c;            
        } catch (SQLException ex) {
            System.out.println("ERRO AO CONSULTAS CATEGORIA : "+ex.getMessage());
            return null;        }
    }
}
