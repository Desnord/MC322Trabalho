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
import java.sql.*;
import java.sql.PreparedStatement;


import jsmaiorjava.implementations.*;
import jsmaiorjava.interfaces.*;

/**
 *
 * @author Thomas
 */
public class JPPrincipal extends JPanel implements ActionListener
{
    private IDoctor medico;
    private IPatient paciente;
    private IDataSet dataset;
    
    private JButton JBDiagnosticar,JBConectar,JBArquivo,JBSqlbd;
    private JTextArea JTAinfos; 
    
    //construtor de janela
    public JPPrincipal()
    {
        //setando opcoes de layout
        this.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.green),BorderFactory.createLineBorder(Color.black)));  
	this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
       
        
        //criando os botoes,campo de informacoes e seus layouts
        
        JBSqlbd = new JButton("BD Conectar");
        JBSqlbd.setBackground(Color.lightGray);
        JBSqlbd.setForeground(Color.black);
        JBSqlbd.setPreferredSize(new Dimension(100, 30));
        
        JBSqlbd.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                JBSqlbd.setBorder(BorderFactory.createLineBorder(Color.black));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                JBSqlbd.setBackground(Color.lightGray);
                JBSqlbd.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                JBSqlbd.setBackground(new Color(66, 244, 95));    
            }     
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                JBSqlbd.setBackground(Color.lightGray);
                JBSqlbd.setBorder(BorderFactory.createLineBorder(Color.lightGray));            
            }
        });
        
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
        JBSqlbd.addActionListener(new BDConectar());
        
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
        GridLayout gl = new GridLayout(1,4);
        jps.setBackground(Color.darkGray);
        jps.setLayout(gl);
        jps.add(JBDiagnosticar);
        jps.add(JBConectar);
        jps.add(JBArquivo);
        jps.add(JBSqlbd);
      
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
    
    
    //evento do botao bd conectar
    private class BDConectar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            dataset = new DataSetComponent();
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");  
                java.sql.Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10295754","sql10295754","ir8Qy9HGeF");  
                              
                PreparedStatement stmt;

                
                int randomtable = (int)(Math.random() * ((4 - 1) + 1)) + 1;
                ResultSet rs;
                String[] vetInfos;
                
                if(randomtable == 1)
                {
                    int randomIndex = (int)(Math.random() * ((499 - 0) + 0)) + 0;
                    stmt = con.prepareStatement("select * from dados1 where codigo = "+randomIndex);
                    rs = stmt.executeQuery(); 
                    
                    String row = "";                          
                    
                    if (rs.next())
                    {
                      row = rs.getString("codigo")+",";
                      row = row + rs.getString("paralysis")+",";
                      row = row + rs.getString("yellow_tongue")+",";
                      row = row + rs.getString("trembiling_finger")+",";
                      row = row + rs.getString("member_loss")+",";
                      row = row + rs.getString("chest_pain")+",";
                      row = row + rs.getString("severe_anger")+",";
                      row = row + rs.getString("diagnostic");
                    }
                    
                    vetInfos = row.split(",");
                    
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-cases500.csv");
                    
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);
                }
                else if(randomtable == 2)
                {
                    int randomIndex = (int)(Math.random() * ((19 - 0) + 0)) + 0;
                    stmt = con.prepareStatement("select * from dados2 where codigo = "+randomIndex);
                    rs = stmt.executeQuery(); 
                    
                    String row = "";   
                    
                    if (rs.next())
                    {
                      row = rs.getString("codigo")+",";
                      row = row + rs.getString("paralysis")+",";
                      row = row + rs.getString("yellow_tongue")+",";
                      row = row + rs.getString("member_loss")+",";
                      row = row + rs.getString("chest_pain")+",";
                      row = row + rs.getString("trembiling_finger")+",";
                      row = row + rs.getString("severe_anger")+",";
                      row = row + rs.getString("red_eye")+",";
                      row = row + rs.getString("blue_skin")+",";
                      row = row + rs.getString("diagnostic");   
                    }
                    
                    vetInfos = row.split(",");
                    
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases20.csv");
                    
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);
                }
                else if(randomtable == 3)
                {
                    int randomIndex = (int)(Math.random() * ((499 - 0) + 0)) + 0;
                    stmt = con.prepareStatement("select * from dados3 where codigo = "+randomIndex);
                    rs = stmt.executeQuery(); 
                    
                    String row = "";              
                    
                    if (rs.next())
                    {
                      row = rs.getString("codigo")+",";
                      row = row + rs.getString("Paralisia")+",";
                      row = row + rs.getString("LinguaAmarela")+",";
                      row = row + rs.getString("DedoTremendo")+",";
                      row = row + rs.getString("PerdadeMembro")+",";
                      row = row + rs.getString("DornoPeito")+",";
                      row = row + rs.getString("RaivaSevera")+",";
                      row = row + rs.getString("Olhovermelho")+",";
                      row = row + rs.getString("Peleazul")+",";
                      row = row + rs.getString("Diagnostico");
                    }
                    
                    vetInfos = row.split(",");
                    
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases500.csv");
                    
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);
                }
                else if(randomtable == 4)
                {
                    int randomIndex = (int)(Math.random() * ((18 - 0) + 0)) + 0;
                    stmt = con.prepareStatement("select * from dados4 where codigo = "+randomIndex);
                    rs = stmt.executeQuery(); 
                    
                    String row = "";              
                    
                    if (rs.next())
                    {
                      row = rs.getString("codigo")+",";
                      row = row + rs.getString("paralysis")+",";
                      row = row + rs.getString("yellow_tong")+",";
                      row = row + rs.getString("member_loss")+",";
                      row = row + rs.getString("chest_pain")+",";
                      row = row + rs.getString("trembling_finger")+",";
                      row = row + rs.getString("severe_anger")+",";
                      row = row + rs.getString("history_bacteria")+",";
                      row = row + rs.getString("diagnostic");
                    }
                    
                    vetInfos = row.split(",");
                    
                    dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-spreadsheet-ml-training.csv");
                    
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);
                }
                
                con.close();  
                
                //habilita botao de diagnostico
                JBDiagnosticar.setEnabled(true);
            }
            catch(ClassNotFoundException | SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Banco de dados não disponível.", "Erro: ", JOptionPane.INFORMATION_MESSAGE);
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
                    {
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-cases500.csv");
                        medico.setNomeArquivo("zombie-health-cases500.csv");
                    }
                    else if(vetInfos[0].equals("2"))
                    {
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases20.csv");
                        medico.setNomeArquivo("zombie-health-new-cases20.csv");
                    }
                    else if(vetInfos[0].equals("3"))
                    {
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-new-cases500.csv");
                        medico.setNomeArquivo("zombie-health-new-cases500.csv");
                    }
                    else if(vetInfos[0].equals("4"))
                    {
                        dataset.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+"zombie-health-spreadsheet-ml-training.csv");
                        medico.setNomeArquivo("zombie-health-spreadsheet-ml-training.csv");
                    }
                    //cria um paciente com os sintomas recebidos pela rede
                    paciente = new Patient();
                    paciente.connect(dataset);
                    paciente.setaSintomas(vetInfos);
                    medico.setaDadosPaciente(vetInfos,vetInfos.length);                    
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
            
            
            //Componentes importados de outro grupo.
            //Gera um atestado do diagnostico
            //Posta mensagem no twitter
            //link do twitter:
            //https://mobile.twitter.com/ZumbiDoutor/with_replies
            /*------------------------------------------------------------------------------------------------*/
            JFrame janelaNomeMedico = new JFrame("Nome Medico");
            JFrame janelaNomePaciente = new JFrame("Nome Paciente");
            
            String doutor = JOptionPane.showInputDialog(janelaNomeMedico, "Digite o nome do médico: ");
            String paciente = JOptionPane.showInputDialog(janelaNomePaciente, "Digite o nome do paciente: ");
            
            Tratamento tratamento = new Tratamento(doenca);
            
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
