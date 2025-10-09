/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javabd01.socket;

import Thread.ThreadServer;
import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//import javabd01.DAO.PessoaDAO;
//import beans.Pessoa;

/**
 *
 * @author laboratorio
 */
public class Main {
    public static void main(String[] args) {
        
        int porta = 12345;  // Use uma constante para a porta
        
        try(ServerSocket servidorSocket = new ServerSocket(porta)){
            System.out.println("Servidor aguardando conexões na porta " + porta);
            
            while(true){
                try{
                    Socket clienteSocket = servidorSocket.accept();
                    System.out.println("Conexão aceita de " + clienteSocket.getInetAddress());
                    
                    //ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                    //ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream());
                    
                    Thread threadCliente = new ThreadServer(clienteSocket);
                    threadCliente.start();
                    /*
                    int id = in.readInt();
                    System.out.println("ID recebido: " + id);
                    
                    PessoaDAO pDAO = new PessoaDAO();
                    Pessoa p = pDAO.getPessoaPorId(id);
                    System.out.println("nome: "+p.getNome());
                    
                    out.writeObject(p); */
                          
                }catch(IOException ex){
                    System.out.println("Erro ao aceitar conexão do cliente");
                }
            }
        }catch(IOException ex){
            System.out.println("Erro ao criar o ServerSocket");
        }
    }
    
}
