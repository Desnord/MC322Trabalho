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
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    
    private Socket socket;
    
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
        
        //medico instanciado
        medico = new Doctor();
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

                    //habilita botao de diagnostico
                    JBDiagnosticar.setEnabled(true);
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
            try
            {
                socket = new Socket("127.0.0.1",Integer.parseInt("6660"));
                ObjectInputStream ois = new ObjectInputStream (socket.getInputStream());   
                String pacInfos = (String)ois.readObject();
                JTAinfos.setText(pacInfos);
                
                String vetInfos[] = pacInfos.split(",");
                dataset = new DataSetComponent();
                
                
                switch(vetInfos[0])
                {
                case "1":
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-cases500.csv");
                case "2":
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases20.csv");
                case "3":
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases500.csv");
                case "4":
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-spreadsheet-ml-training.csv");
                }
           
                //seleciona um novo paciente para diagnosticar
                paciente = new Patient();
                paciente.connect(dataset);
                
                //habilita botao de diagnostico
                JBDiagnosticar.setEnabled(true);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Servidor indisponível ou dados recebidos inválidos.", "Erro: ", JOptionPane.INFORMATION_MESSAGE);
            }
        }      
    }
    
    private class RealizaDiagnostico implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
    	{
            JBDiagnosticar.setEnabled(false);
            //aqui o medico tem de realizar o diagnostico do paciente selecionado
            //chamar o metodo de diagnosticar com o paciente atual em parametro
        }      
    }
}
