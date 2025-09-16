/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframe;

import Prova1.beans.Veiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author laboratorio
 */
public class Arquivo {
    private FileWriter arqw;
    private BufferedWriter escritor;
    private FileReader arqr;
    private BufferedReader leitor;
    private ArrayList<Veiculo> listVeiculo;
    public String nomeArquivo;
    
    public Arquivo(String nomeArquivo) {
	this.nomeArquivo = nomeArquivo;
	listVeiculo = new ArrayList<>();
    }
    
    public void gravaArquivo(Veiculo v) {
	try {
            arqw = new FileWriter (nomeArquivo+".txt",true);
            escritor = new BufferedWriter(arqw);
            escritor.write(v.getId()+";"+v.getMarca()+";"+v.getModelo()+";"+v.getAno()+";"+v.getPlaca()+";"+v.getCor());
            escritor.newLine();
            escritor.close();
            arqw.close();
            System.out.println("Arquivos salvos com sucesso no .txt");
					
        }catch(IOException e) {
            e.printStackTrace();
	}
    }
    
    public List<Veiculo> leArquivo() {
        List<Veiculo> lista = new ArrayList<>();
        File f = new File(nomeArquivo + ".txt");

        // Se o arquivo não existe, retorna lista vazia sem erro
        if (!f.exists()) {
            return lista;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campo = linha.split(";");
                if (campo.length == 6) {
                    Veiculo v = new Veiculo(
                        Integer.parseInt(campo[0]), // id
                        campo[1],                   // marca
                        campo[2],                   // modelo
                        Integer.parseInt(campo[3]), // ano
                        campo[4],                   // placa
                        campo[5]                    // cor
                    );
                    lista.add(v);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler arquivo: " + e.getMessage());
    }

    return lista;
    }
    
    
}
