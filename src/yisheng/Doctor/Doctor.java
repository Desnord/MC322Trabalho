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
        //chamar o método de encontrar a melhor pergunta
        
        return "";
    }
    public void connect(IResponder responder)
    {
        this.responder = responder;
    }
    public void connect(ITableProducer producer) 
    {
        this.producer = producer;
    }
    public void melhorPergunta(String attributes[], String instance[][]){
        
    }
}
