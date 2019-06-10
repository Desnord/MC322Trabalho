/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import yisheng.DataSet.DataSetComponent;
import yisheng.DataSet.IDataSet;
import yisheng.Doctor.Doctor;
import yisheng.Doctor.IDoctor;
import yisheng.Pacient.IPatient;
import yisheng.Pacient.Patient;
import yisheng.componentes.Arquivo;
import yisheng.componentes.RedeCliente;


import jsmaiorjava.interfaces.ITratamento;
import jsmaiorjava.implementations.Tratamento;
import jsmaiorjava.interfaces.IProntuario;
import jsmaiorjava.implementations.Prontuario;
import jsmaiorjava.implementations.ZumbiTwittero;
import jsmaiorjava.implementations.ImprimeAtestado;

/**
 *
 * @author Thomas
 */
public class JPPrincipal extends JPanel implements ActionListener
{
    private IDoctor medico;
    private IPatient paciente;
    private IDataSet dataset;
    
    private JButton JBDiagnosticar,JBConectar,JBArquivo;
    private JTextArea JTAinfos; 
    
    //construtor de janela
    public JPPrincipal()
    {
        //setando opcoes de layout
        this.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green),BorderFactory.createLineBorder(Color.black)));  
	this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
       
        
        //criando os botoes,campo de informacoes e seus layouts
        JBDiagnosticar = new JButton("Diagnosticar");
        JBDiagnosticar.setEnabled(false);
        JBDiagnosticar.setBackground(Color.lightGray);
        JBDiagnosticar.setForeground(Color.black);
        JBDiagnosticar.setPreferredSize(new Dimension(100, 30));
        
        JBDiagnosticar.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                if(JBDiagnosticar.isEnabled())
                    JBDiagnosticar.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                JBDiagnosticar.setBackground(Color.lightGray);
                JBDiagnosticar.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                if(JBDiagnosticar.isEnabled())
                    JBDiagnosticar.setBackground(new Color(66, 244, 95));    
            }     
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                JBDiagnosticar.setBackground(Color.lightGray);
                JBDiagnosticar.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            }
        });
        
  
        JBConectar = new JButton("Conectar");
        JBConectar.setBackground(Color.lightGray);
        JBConectar.setForeground(Color.black);
        JBConectar.setPreferredSize(new Dimension(100, 30));
        
        JBConectar.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                JBConectar.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                JBConectar.setBackground(Color.lightGray);
                JBConectar.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                JBConectar.setBackground(new Color(66, 244, 95));    
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {

                JBConectar.setBackground(Color.lightGray);
                JBConectar.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
        });
        
        JBArquivo = new JButton("Abrir Arquivo");
        JBArquivo.setBackground(Color.lightGray);
        JBArquivo.setForeground(Color.black);
        JBArquivo.setPreferredSize(new Dimension(100, 30));
        
        JBArquivo.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                JBArquivo.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                JBArquivo.setBackground(Color.lightGray);
                JBArquivo.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                JBArquivo.setBackground(new Color(66, 244, 95));    
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {

                JBArquivo.setBackground(Color.lightGray);
                JBArquivo.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
        });
        
        JTAinfos = new JTextArea(100,100);
        JTAinfos.setBackground(Color.gray);
        TitledBorder linha = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray),"Diagnóstico:");
        linha.setTitleColor(Color.black);
        JTAinfos.setBorder(linha);
        JTAinfos.setLineWrap(true);
        JTAinfos.setWrapStyleWord(true);
        JTAinfos.setEditable(false);
        
        //adiciona eventos dos botoes
        JBConectar.addActionListener(new RedeConectar());
        JBArquivo.addActionListener(new AbrirArquivo());
        JBDiagnosticar.addActionListener(new RealizaDiagnostico());
        
        //setando mais opcoes de layout
            
        URL iconURL = getClass().getResource("./images/zombie.png");
        ImageIcon icon = new ImageIcon(iconURL);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH); 
        icon = new ImageIcon(newimg);
        JLabel lblImg = new JLabel(icon, JLabel.CENTER);  

        add(BorderLayout.NORTH,lblImg);
        add(BorderLayout.CENTER,JTAinfos);
       
        JPanel jps = new JPanel();
        GridLayout gl = new GridLayout(1,3);
        jps.setBackground(Color.darkGray);
        jps.setLayout(gl);
        jps.add(JBDiagnosticar);
        jps.add(JBConectar);
        jps.add(JBArquivo);
      
        add(BorderLayout.SOUTH,jps);  
        
        //medico instanciado
        medico = new Doctor();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        throw new UnsupportedOperationException("Ainda não suportado.");
    }
    
    //evento do botao abrir arquivo
    private class AbrirArquivo implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
    	{  
            //acha o caminho do arquivo com uma janelinha e instancia o dataset
            String nome = Arquivo.AbrirArquivo("/src/yisheng/csv");
            
            if(!nome.equals(""))
            {
            dataset = new DataSetComponent();
            dataset.setDataSource(nome);
            
            //seleciona um novo paciente para diagnosticar
            paciente = new Patient();
            paciente.connect(dataset);
                
            //habilita botao de diagnostico
            JBDiagnosticar.setEnabled(true);
            }
    	}
    }
    
    //evento do botao conectar
    private class RedeConectar implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
    	{
                dataset = new DataSetComponent();
                
                String vetInfos[] = RedeCliente.ReceberDados("127.0.0.1","6660");
                
                if(vetInfos != null)
                {
                    //verifica em qual dos arquivos o paciente pertence
                    if(vetInfos[0].equals("1"))
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-cases500.csv");
                    else if(vetInfos[0].equals("2"))
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases20.csv");
                    else if(vetInfos[0].equals("3"))
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases500.csv");
                    else if(vetInfos[0].equals("4"))
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-spreadsheet-ml-training.csv");
           
                    //seleciona um novo paciente para diagnosticar
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);

                    //habilita botao de diagnostico
                    JBDiagnosticar.setEnabled(true);
                }
        }      
    }
    
    //evento do botao diagnosticar
    private class RealizaDiagnostico implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
    	{
            //desativa opcao de diagnostico
            JBDiagnosticar.setEnabled(false);
            
            //conecta o medico à tabela
            medico.connect(dataset);
            
            //conecta o medico ao paciente
            medico.connect(paciente);
            
            //realiza o diagnóstico da doença
            String doenca = medico.startInterview();
            
            //escreve o diagnóstico na tela
            JTAinfos.setText("O paciente pode ter: "+doenca);
            
            
            //Componente importado de outro grupo.
            //Gera um atestado do diagnostico
            //Posta mensagem no twitter
            //link do twitter:
            //https://mobile.twitter.com/ZumbiDoutor/with_replies
            /*------------------------------------------------------------------------------------------------*/
            JFrame janelaNomeMedico = new JFrame("Nome Medico");
            JFrame janelaNomePaciente = new JFrame("Nome Paciente");
            
            String doutor = JOptionPane.showInputDialog(janelaNomeMedico, "Digite o nome do médico: ");
            String paciente = JOptionPane.showInputDialog(janelaNomePaciente, "Digite o nome do paciente: ");
            
            ITratamento tratamento = new Tratamento(doenca);
            
            IProntuario prontuario = new Prontuario(tratamento,paciente,doutor);
            ZumbiTwittero zt = new ZumbiTwittero(prontuario,
            "@doutor está cuidando de @paciente, tristemente o paciente pode ter: @doenca.\n" +
            "Pra não perder a vida terá que @tratamento. \n até mais meus consagrados.");
            
            boolean twitou = zt.twittar();
            
            ImprimeAtestado impAts = new ImprimeAtestado(prontuario);
            impAts.imprime(System.getProperty("user.home")+"/Desktop");
            
           /*------------------------------------------------------------------------------------------------*/
        }      
    }
}
