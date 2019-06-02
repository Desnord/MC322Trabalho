/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class JPPrincipal extends JPanel
{
    IDoctor medico;
    IPatient paciente;
    IDataSet dataset;
    
    private JButton JBDiagnosticar,JBConectar,JBArquivo;
    private JTextArea JTAinfos; 
    
    public JPPrincipal()
    {
        this.setBorder(BorderFactory.createEtchedBorder(Color.WHITE,Color.RED));  
	this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
       
        JBDiagnosticar = new JButton("Diagnosticar");
        JBDiagnosticar.setEnabled(false);
        
        JBConectar = new JButton("Conectar");
        JBArquivo = new JButton("Abrir Arquivo");
        
        JTAinfos = new JTextArea(200,200);
        JTAinfos.setBackground(Color.LIGHT_GRAY);
        JTAinfos.setEditable(false);
        
        
        add(BorderLayout.CENTER,JTAinfos);
       
        JPanel jfs = new JPanel();
        GridLayout gl = new GridLayout(1,3);
        jfs.setLayout(gl);
        jfs.add(JBDiagnosticar);
        jfs.add(JBConectar);
        jfs.add(JBArquivo);
        
        add(BorderLayout.SOUTH,jfs);
        
        dataset = new DataSetComponent();
        dataset.setDataSource("src/yisheng/csv/zombie-health-spreadsheet-ml-training.csv");
        paciente = new Patient();
        paciente.connect(dataset);
        medico = new Doctor();
    }
}
