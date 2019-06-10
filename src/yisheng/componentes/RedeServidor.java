/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidoryisheng.componentes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thomas
 */
public class RedeServidor 
{
    public static void EnviarDados(String st,String porta)
    {
            try 
            {
                ServerSocket server = new ServerSocket(Integer.parseInt(porta));
                Socket conexao = server.accept();
                ObjectOutputStream out = new ObjectOutputStream(conexao.getOutputStream());

                out.writeObject(st);
                
                conexao.close();
                server.close();
            }        
            catch(IOException ex)
            {}
    }     
}
