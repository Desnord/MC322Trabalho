/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng;

import yisheng.DataSet.*;
import yisheng.Doctor.*;
import yisheng.Pacient.*;

/**
 *
 * @author Thomas
 */
public class Yisheng 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFPrincipal jfp = new JFPrincipal();
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("src/yisheng/csv/zombie-health-spreadsheet-ml-training.csv");
        IPatient paciente = new Patient();
        paciente.connect(dataset);
        
        IDoctor medico = new Doctor();
        medico.connect(dataset);
        medico.connect(paciente);
        medico.startInterview();
    }
    
}
