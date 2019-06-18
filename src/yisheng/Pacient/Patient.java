/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.Pacient;

import yisheng.DataSet.ITableProducer;

/**
 *
 * @author Thomas
 */
public class Patient implements IPatient {
    private int patientN = 0;
    
    private ITableProducer producer;
    
    private String attributes[];
    private String patientInstance[];
    
    public void setaSintomas(String infos[])
    {        
        for(int i=0; i<attributes.length - 1; i++)
        {
            patientInstance[i] = infos[i+1];
        }
        
        String instances[][] = producer.requestInstances();
        
        //doenca desconhecida do paciente (por enquanto)
        patientInstance[attributes.length-1] = "";
        
        boolean b = true;
        
        for(int i=0;i<instances.length;i++)
        {
            b = true;
            
            for(int j=0;j<instances[i].length-1;j++)
            {
                if(!patientInstance[j].equals(instances[i][j]))
                {
                    b = false;
                }
            }
            
            //se o paciente existe na tabela, entao ele tem a doenca descrita
            //seta a doenca do paciente igual a que está na tabela
            if(b)
            {
                patientInstance[attributes.length-1] = instances[i][attributes.length-1];
                break;
            }
        }        
    }
    
    public String[] getDados()
    {
        return this.patientInstance;
    }
    
    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        patientN = (int)(Math.random() * instances.length);
        patientInstance = instances[patientN];
        
        //System.out.println("Patient selected: " + patientN);
        //System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }
    
    public String ask(String question) {
        String result = "unknown";
        
        for (int a = 0; a < attributes.length - 1; a++)
            if (question.equalsIgnoreCase(attributes[a]))
                result = (patientInstance[a].equals("t")) ? "yes" : "no";
        
        return result;
    }

    public boolean finalAnswer(String answer) {
        boolean result = false;
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            result = true;
        return result;
    }
}
