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
    public int[] melhorPergunta(String att[], String ins[][]){
        int[][] cont = new int[att.length - 1][4]; 
        int aux_t = 0, aux_f = 0;
        int[] res = new int[att.length - 1], aux = new int[4];
        for(int j = 0; j < att.length - 1; j++){
            for (int i = 0; i < ins.length; i++){
                if(ins[i][j].equals("f"))
                    aux_f += 1;
                else if(ins[i][j].equals("t"))
                    aux_t += 1;
            }
            cont[j][0] = aux_t;
            cont[j][1] = aux_f;
            cont[j][2] = j;
            cont[j][3] = Math.abs(aux_t - aux_f);
            System.out.println("[ " + cont[j][0] + " " + cont[j][1] + " " + cont[j][2] +  " " +  cont[j][3] + " ]");
            aux_t = 0;
            aux_f = 0;
        }
        for(int a = cont.length; a>= 1; a--){
            for(int b = 1; b< a; b++){
                if(cont[b - 1][3] > cont[b][3]){
                    aux[0] = cont[b][0]; aux[1] = cont[b][1]; aux[2] = cont[b][2]; aux[3] = cont[b][3];
                    cont[b][0] = cont[b - 1][0]; cont[b][1] = cont[b - 1][1]; cont[b][2] = cont[b - 1][2]; cont[b][3] = cont[b - 1][3];
                    cont[b - 1][0] = aux[0];cont[b - 1][1] = aux[1];cont[b - 1][2] = aux[2];cont[b - 1][3] = aux[3];
                }
            }
        }
        for(int z = 0; z < res.length; z++){
            res[z] = cont[z][2];
            System.out.println(res[z]);
        }
        return res;
    }
  
}
