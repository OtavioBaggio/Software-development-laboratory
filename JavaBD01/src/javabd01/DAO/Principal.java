/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabd01.DAO;

import javabd01.beans.Pessoa;

/**
 *
 * @author laboratorio
 */
public class Principal {
    public static void main(String[] args) {
        
        Pessoa p = new Pessoa();
        p.setNome("Ricardo");
        p.setSexo("M");
        p.setIdioma("PORTUGUES");
        
        PessoaDAO pDAO = new PessoaDAO();
        pDAO.inserir(p);
    }
    
}
