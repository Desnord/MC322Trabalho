/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.componentes;

import java.awt.HeadlessException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas
 */
public class Arquivo 
{
    public static String AbrirArquivo()
    {
        String nome = "";//nome do arquivo
        try
        {
            JFileChooser x = new JFileChooser(System.getProperty("user.dir"));
            x.setDialogTitle("Abrir");
            int retorno = x.showSaveDialog(null);
           
            if(retorno == JFileChooser.APPROVE_OPTION)
            {
                nome = x.getSelectedFile().getAbsolutePath();
            }
        }
        catch (HeadlessException ex)
        {
            JOptionPane.showMessageDialog(null, "Arquivo inválido.", "Erro: ", JOptionPane.INFORMATION_MESSAGE);
        }
        return nome;
    }
    
    public static String AbrirArquivo(String pasta)
    {
        String nome = "";//nome do arquivo
        try
        {
            JFileChooser x = new JFileChooser(System.getProperty("user.dir")+pasta);
            x.setDialogTitle("Abrir");
            int retorno = x.showSaveDialog(null);
           
            if(retorno == JFileChooser.APPROVE_OPTION)
            {
                nome = x.getSelectedFile().getAbsolutePath();
            }
        }
        catch (HeadlessException ex)
        {
            JOptionPane.showMessageDialog(null, "Arquivo inválido.", "Erro: ", JOptionPane.INFORMATION_MESSAGE);
        }
        return nome;
    }
}
