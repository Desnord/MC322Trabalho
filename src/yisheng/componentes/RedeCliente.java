/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.componentes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;


/**
 *
 * @author Thomas
 */
public class RedeCliente 
{    
    public static String[] ReceberDados(String ipServidor,String porta)
    {
            try
            {
                //conecta-se ao paciente
                Socket socket = new Socket(ipServidor,Integer.parseInt(porta));
                //recebe dados do paciente e transforma em string
                ObjectInputStream ois = new ObjectInputStream (socket.getInputStream());   
                String pacInfos = (String)ois.readObject();
                
                //fecha conexao
                socket.close();
                
                //transforma dados do paciente em um vetor
                String vetInfos[] = pacInfos.split(",");
                return vetInfos;
            }
            catch(IOException | ClassNotFoundException | NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Servidor indisponível ou dados recebidos inválidos.", "Erro: ", JOptionPane.INFORMATION_MESSAGE);
            }        
           
            return null;
    }
}
