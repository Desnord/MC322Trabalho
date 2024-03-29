/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yisheng.Doctor;

import yisheng.DataSet.ITableProducer;
import yisheng.Reference.IResponder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import yisheng.DataSet.DataSetComponent;


/**
 *
 * @author Thomas
 */
public class Doctor implements IDoctor
{
    private int patientN = 0;
    private String doenca;
    private String nome;
    protected int[] sintomas; //vetor q salva os sintomas
    protected int quantSintomas = 0;
    protected String attributes[];
    protected String instances[][];
    protected String dadosPaciente[];
    
    private DataSetComponent ds = new DataSetComponent();
    
    private ITableProducer producer;
    private IResponder responder;

    public String startInterview ()
    {
        attributes = producer.requestAttributes();
        instances = producer.requestInstances();
        return Diagnostico(attributes, instances);
    }
    public void connect(IResponder responder)
    {
        this.responder = responder;
    }
    public void connect(ITableProducer producer) 
    {
        this.producer = producer;
    }
    
    @Override
    public void setNomeArquivo(String n){
        this.nome = n;
    }
    
    public int[] melhorPergunta(String att[], String ins[][]){
        int[][] cont = new int[att.length - 1][4]; 
        int aux_t = 0, aux_f = 0;
        int[] aux = new int[4];
        int[] resp = new int [att.length - 1];
        this.sintomas = new int[att.length - 1];
        
        for(int j = 0; j < att.length - 1; j++){
            for (int i = 0; i < ins.length; i++){
                if(ins[i][j].equals("f")){
                    aux_f += 1;
                    this.sintomas[j] = 'f'; 
                }
                else if(ins[i][j].equals("t")){
                    if(j < this.sintomas.length)
                        this.sintomas[j] = 't';
                    this.quantSintomas += 1;
                    aux_t += 1;
                }
            }
            cont[j][0] = aux_t;
            cont[j][1] = aux_f;
            cont[j][2] = j;
            cont[j][3] = Math.abs(aux_t - aux_f);
            aux_t = 0;
            aux_f = 0;
        }
        for(int a = 0; a < cont.length - 1; a++){
            for(int b = 0; b < cont.length - a - 1; b++){
                if(cont[b][3] > cont[b + 1][3]){
                    aux[0] = cont[b][0]; aux[1] = cont[b][1]; aux[2] = cont[b][2]; aux[3] = cont[b][3];
                    cont[b][0] = cont[b + 1][0]; cont[b][1] = cont[b + 1][1]; cont[b][2] = cont[b + 1][2]; cont[b][3] = cont[b + 1][3];
                    cont[b + 1][0] = aux[0];cont[b + 1][1] = aux[1];cont[b + 1][2] = aux[2];cont[b + 1][3] = aux[3];
                }
            }
        }
        for(int i = 0; i < cont.length; i++)
            resp[i] = cont[i][2];

        return resp;
    }
    
    
    public int achaDoenca()
    {
        int c = 0;
        int d = 0;
        for (int a = 0; a < this.instances.length; a++) 
        {
          for (int b = 0; b < this.attributes.length-1; b++)
          {
            if (this.sintomas[b] == 't' && (this.instances[a][b]).equals("t"))
            {
              c += 1;
              if (c == this.quantSintomas) 
              { //mesmo numero de sintomas iguais
                //ja tem uma doenca equivalente
                this.doenca = this.instances[a][this.attributes.length-1];
                return 1;
              }
            }
           if (c > d) 
           {
             d = c; //variavel que salva o q mais tem em comum
             this.doenca = this.instances[a][this.attributes.length-1]; //salva a doenca que mais tem sintomas em comum
           }
          c = 0;
        }
        return 0;
        }
        return 0;
    }
    
    /*public void achaDoenca(){
        int c = 0;
        int d = 0;
        
        for (int a = 0; a < this.instances.length; a++) 
        {
          for (int b = 0; b < this.attributes.length-1; b++){
            if (this.sintomas[b] == 't' && (this.instances[a][b]).equals("t")) {
              c += 1;
            }
          }
          if (c > d) {
            d = c; //variavel que salva o q mais tem em comum
            this.doenca = this.instances[a][this.attributes.length-1]; //salva a doenca que mais tem sintomas em comum
          }
          c = 0;
        }
    }*/
    
   public String Diagnostico(String att[], String ins[][]) 
   {

        //Copia a matriz de instancias
        int altura = ins.length;
        int largura = ins[1].length;
        String[][] aux_ins = new String[altura][largura];
        int[] vet;
        vet = melhorPergunta(att, ins);
        int coluna = vet[0];

        for(int i = 0; i < altura; i++) 
        {
            for (int j = 0; j < largura; j++)
            {
                aux_ins[i][j] = ins[i][j];
            }
        }
        int ce = 0;
        
        while(ce < att.length)
        {
            String resposta = responder.ask(att[coluna]); // Coluna referente a pergunta

            //Substituicao por "" em linhas que nao se adequam
            if(resposta.equals("yes")) 
            {
                for(int i = 0; i < altura; i++) 
                {
                    if (aux_ins[i][coluna].equals("f")) 
                    { // Deve-se trocar a linha que tem f por ""
                        for (int k = 0; k < largura; k++) 
                        {
                            aux_ins[i][k] = "";
                        }
                    }
                }
            }
            else 
            {
                for(int i = 0; i < altura; i++) 
                {
                    if (aux_ins[i][coluna].equals("t")) 
                    { // Deve-se trocar a linha que tem t por ""
                        for(int k = 0; k < largura; k++) 
                        {
                            aux_ins[i][k] = "";
                        }
                    }
                }
            }

            // Primeira doenca valida(se existir)
            String doenca = "";
            for(int i = 0; i < altura; i ++) 
            {
                if (!aux_ins[i][largura - 1].equals("")) 
                {
                    doenca = aux_ins[i][largura - 1];
                    break;
                }
            }

            int ver = 0;
            for(int i = 0; i < altura; i ++) 
            {
                if (!aux_ins[i][largura - 1].equals("") && !aux_ins[i][largura - 1].equals(doenca))
                { // Mais de uma doenca, retorna para o inicio
                    ver = 1;
                    ce++;

                    if(ce < vet.length)
                        coluna = vet[ce];

                    break;
                }
            }

            if(ver == 0)
            { // So resta uma doenca ou nao encontrou nenhuma doenca
      
                for(int i = 0; i < altura; i ++)
                {
                    if(!aux_ins[i][largura - 1].equals("")) 
                    {
                        this.doenca = "" + aux_ins[i][largura - 1]; // Encontrou uma unica doenca
                    }

                }
                //this.achaDoenca();
                return this.adicionaDoenca();
                
                // Doenca desconhecida
            }
        }

        ArrayList<String> conj = new ArrayList<>(1);

        for(int i = 0; i < altura; i ++)
        {
            if (!aux_ins[i][largura - 1].equals("")) 
            {
                if (!conj.contains(aux_ins[i][largura - 1]))
                {
                    conj.add(aux_ins[i][largura - 1]);
                }
            }
        }
        String doencas = "" + conj.get(0);

        for(int k = 1; k < conj.size(); k++)
            doencas += ", " + conj.get(k);

        return doencas;
    }
  
    public void matrizArquivo() {
        ds.setDataSource(System.getProperty("user.dir")+"/src/yisheng/csv/"+this.nome);
        this.attributes = ds.requestAttributes();
        this.instances = ds.requestInstances();
    }
    
    public void setaDadosPaciente(String s[],int t)
    {
        this.dadosPaciente = new String[t];
        
        for(int i=1;i<t;i++)
            this.dadosPaciente[i-1] = s[i];
    }
    
    public String adicionaDoenca()
    {
        FileWriter arquivo;
        if(dadosPaciente != null)
        {
        try {
            
          // o segundo parametro indica se fara append ou nao
          
          File f = new File("src/yisheng/csv/"+this.nome);
          
          arquivo = new FileWriter(f,true);
          for (int i = 0; i <= this.instances.length; i++) {
            if (i == this.instances.length) {
              for (int j = 0; j < this.attributes.length; j++) {
                if (j == this.attributes.length-1) {
                    arquivo.append(this.doenca+"\n");
                    //coloca o nome da doenca
                }
                else {
                  arquivo.append(dadosPaciente[j]+","); //nao sei se isso funciona, mas preciso adicionar a virgula
                  //coloca t ou f
                }
              }
            }
          }
          arquivo.flush();  
          arquivo.close();
        }
        catch (IOException erro) {
          System.out.println("Nao consegui criar o arquivo =(");
        }
        }
        
        return this.doenca;
    }
}