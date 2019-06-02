/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import yisheng.DataSet.DataSetComponent;
import yisheng.DataSet.IDataSet;
import yisheng.Doctor.Doctor;
import yisheng.Doctor.IDoctor;
import yisheng.Pacient.IPatient;
import yisheng.Pacient.Patient;

/**
 *
 * @author Thomas
 */
public class JPPrincipal extends JPanel implements ActionListener
{
    IDoctor medico;
    IPatient paciente;
    IDataSet dataset;
    
    private JButton JBDiagnosticar,JBConectar,JBArquivo;
    private JTextArea JTAinfos; 
    
    public JPPrincipal()
    {
        //setando opcoes de layout
        this.setBorder(BorderFactory.createEtchedBorder(Color.WHITE,Color.RED));  
	this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
       
        
        //criando os botoes e campo de informacoes
        JBDiagnosticar = new JButton("Diagnosticar");
        JBDiagnosticar.setEnabled(false);
        
        JBConectar = new JButton("Conectar");
        JBArquivo = new JButton("Abrir Arquivo");
       
        JTAinfos = new JTextArea(200,200);
        JTAinfos.setBackground(Color.LIGHT_GRAY);
        JTAinfos.setEditable(false);
        
        //adiciona eventos dos botoes
        JBConectar.addActionListener(new RedeConectar());
        JBArquivo.addActionListener(new AbrirArquivo());
        JBDiagnosticar.addActionListener(new RealizaDiagnostico());
        
        //setando mais opcoes de layout
        add(BorderLayout.CENTER,JTAinfos);
       
        JPanel jfs = new JPanel();
        GridLayout gl = new GridLayout(1,3);
        jfs.setLayout(gl);
        jfs.add(JBDiagnosticar);
        jfs.add(JBConectar);
        jfs.add(JBArquivo);
      
        add(BorderLayout.SOUTH,jfs);        
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Ainda não suportado.");
    }
    
    private class AbrirArquivo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{  
            try
            {
            
            //abre um arquivo com configuracao de paciente
            JFileChooser x = new JFileChooser(System.getProperty("user.dir")+"/src/yisheng/csv");
	    x.setDialogTitle("Abrir");
            //x.setCurrentDirectory();
            
            int retorno = x.showSaveDialog(null);
            
            String nome = "";//nome do arquivo
            
                if(retorno == JFileChooser.APPROVE_OPTION)
                {
                    nome = x.getSelectedFile().getAbsolutePath();


                    dataset = new DataSetComponent();
                    dataset.setDataSource(nome);

                    //seleciona um novo paciente para diagnosticar
                    paciente = new Patient();
                    paciente.connect(dataset);
                    medico = new Doctor();

                    //habilita botao de diagnostico
                    JBDiagnosticar.setEnabled(true);

                    //desabilita opcao por rede
                    JBConectar.setEnabled(false);
                }
            }
            catch (Exception ex)
            {}
    	}
    }
    
    private class RedeConectar implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{
            
        }      
    }
    
    private class RealizaDiagnostico implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{
            
        }      
    }
}
