/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_produtos.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java_produtos.beans.Categorias;
import java_produtos.beans.Produtos;
import java_produtos.conexao.Conexao;

/**
 *
 * @author Otávio Baggio
 */
public class ProdutosDAO {
    private Conexao conexao;
    private Connection conn;
    
    
    public ProdutosDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
   
    
    public void inserir (Produtos p){
        try {
            String sql = "INSERT INTO produtos (nome, preco, quantidade, categoria_id) VALUES (?,?,?,?);";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getCategoria_id().getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir PRODUTO: "+ex.getMessage());
        }
    }
    
    
    public void editar(Produtos p){
        try {
            String sql = "UPDATE produtos set nome=?, preco=?, quantidade=?, categoria_id=? WHERE id=?;";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setFloat(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getCategoria_id().getId());
            stmt.setInt(5, p.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao editar PRODUTO:"+ex.getMessage());
        }
        
    }
    
    
    public void excluir(int id){
        try {
            String sql = "DELETE FROM produtos WHERE id=?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir o PRODUTO:"+ex.getMessage());
        }
    }
    
    
    public List<Produtos> getProdutos(){
        try {
            String sql = "SELECT * FROM produtos";
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            List<Produtos> listaProdutos = new ArrayList();
            while(rs.next()){
                Produtos p = new Produtos();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getFloat("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                int categoria_id = rs.getInt("categoria_id");
                CategoriasDAO cDAO = new CategoriasDAO();
                Categorias c = cDAO.getCategorias(categoria_id);
                p.setCategoria_id(c);
                listaProdutos.add(p);
                
            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao consutar todos os PRODUTOS"+ex.getMessage());
            return null;
        }
    }
    
   
    public Produtos getProdutos(int id){
        try {
            String sql = "SELECT * FROM produtos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produtos p = new Produtos();
            rs.first();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPreco(rs.getFloat("preco"));
            p.setQuantidade(rs.getInt("quantidade"));
            
            int categoria_id = rs.getInt("categoria_id");
            CategoriasDAO cDAO = new CategoriasDAO();
            Categorias c = cDAO.getCategorias(rs.getInt("categoria_id"));
            p.setCategoria_id(c);
                   
            return p;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar PRODUTO");
            return null;
        }
    }
}
