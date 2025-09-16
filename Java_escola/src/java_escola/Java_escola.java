/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java_escola;

import javaEscola.DAO.alunosDAO;
import javaEscola.beans.alunos;

/**
 *
 * @author Otávio Baggio
 */
public class Java_escola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        alunos a = new alunos();
        a.setNome("João Kleber");
        a.setIdade(20);
        a.setCurso("Ingles");
        
        alunosDAO ad = new alunosDAO();
        ad.inserir(a);
    }
    
}
