/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.Doctor;

import yisheng.DataSet.ITableProducer;
import yisheng.Reference.IResponder;

/**
 *
 * @author Thomas
 */
public class Doctor implements IDoctor
{
    private int patientN = 0;

    private ITableProducer producer;
    private IResponder responder;
    
    public String startInterview()
    {
        String attributes[] = producer.requestAttributes();
        String instances[][] = producer.requestInstances();
        melhorPergunta(attributes, instances);
        return null;
    }
    public void connect(IResponder responder)
    {
        this.responder = responder;
    }
    public void connect(ITableProducer producer) 
    {
        this.producer = producer;
    }
    public void melhorPergunta(String att[], String ins[][]){
        int[][] cont = new int[att.length][2]; 
        int aux_v = 0, aux_f = 0;
        for(int j = 0; j < att.length; j++){
            for (int i = 0; i < ins.length; i++){
                if(ins[i][j] == "f")
                    aux_f++;
                else
                    aux_v++;
            }
            cont[j][0] = aux_v;
            cont[j][1] = aux_f;
            
            System.out.println("[ " + aux_v + " " + aux_f + " ]");
            aux_v = 0;
            aux_f = 0;
        }
      
    }
    public String VetorDeMelhoresPerguntas(String attributes[], String instances[][]){
        melhorPergunta(attributes, instances);
        return null;
    }
}
