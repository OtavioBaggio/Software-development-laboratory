/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Thread;

import beans.Pessoa;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javabd01.DAO.PessoaDAO;

/**
 *
 * @author laboratorio
 */
public class ThreadServer extends Thread{
    private Socket clienteSocket;
        
        public ThreadServer(Socket clientesSocket){
            this.clienteSocket = clienteSocket;
        }
        
        @Override
        public void run(){
            try(ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())){
                
                int id = in.readInt();
                System.out.println("ID recebido: " + id);
                
                // simule a obtenção de ume Pessoa a partir do ID:
                PessoaDAO pDAO = new PessoaDAO();
                Pessoa p = pDAO.getPessoaPorId(id);
                
                out.writeObject(p);
            
            }catch(IOException ex){
                System.out.println("Erro ao lidar com o cliente");
            }
                    
        }
    
}
