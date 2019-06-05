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
    protected int sintomas[] = new int[7]; //vetor q salva os sintomas
    protected int quantSintomas = 0;
    protected String attributes[];
    protected String instances[][];
    
    private DataSetComponent ds = new DataSetComponent();
    
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
        public void Diagnostico(String att[], String ins[][], int melhor[]) {
        boolean resposta;
        
        // Perguntar pro paciente
        // Criar uma matriz igual a ins[][]
        // Nessa matriz colocar 0 nas linhas que nao correspondem
        // Passar um laço verificando se a ultima coluna, das doencas, só tem um tipo de doença
        // Se tiver uma só doença retorna ela. Caso não, retorna a matriz para a melhor pergunta
            String[][] aux_ins = new String[ins.length][ins[1].length];
            
            for(int i = 0; i< ins.length; i++)
                for(int j = 0; j< ins[i].length; j++)
                    ins[i][j] = aux_ins[i][j];
        
            if(resposta) {
                
            }
            else {
            }
        }
  
    public String VetorDeMelhoresPerguntas(String attributes[], String instances[][]){
        melhorPergunta(attributes, instances);
        return null;
    }
    public void matrizArquivo() { //de 0 a 19
        ds.setDataSource("./src/yisheng/csv/zombie-health-cases500.csv");
        this.attributes = ds.requestAttributes();
        this.instances = ds.requestInstances();
        
    }
    //essa parte cria o vetor com os sintomas do paciente
    public void adicionaSintoma(boolean resp, int pos){
      if (resp == true) {
        this.sintomas[pos] = 't';
        this.quantSintomas += 1;
      }
      else{
        this.sintomas[pos] = 'f';
      }
    }
    public void achaDoenca(){
        int c = 0;
        int d = 0;
        for (int a = 0; a < this.instances.length; a++) {
          for (int b = 0; b < this.attributes.length-1; b++){
            if (this.sintomas[b] == 't' && (this.instances[a][b]).equals("t")) {
              c += 1;
              if (c == this.quantSintomas) { //mesmo numero de sintomas iguais
                System.out.println(this.instances[a][7]); //printar todas as possoveis doencas
              }
            }
          }
          if (c > d) {
            d = c; //variavel que salva o q mais tem em comum
            doenca = this.instances[a][this.attributes.length]; //salva a doenca que mais tem sintomas em comum
          }
          c = 0;
        }
    }
    public void adicionaDoenca(){
        FileWriter arquivo;
        PrintWriter formatado;
        try {
          // o segundo parametro indica se fara append ou nao
          arquivo = new FileWriter("zombie-health-cases500.csv", true); //colocar o nome certo do arquivo
          formatado = new PrintWriter(arquivo);
          for (int i = 0; i <= this.instances.length; i++) {
            if (i == this.instances.length) {
              for (int j = 0; j < this.attributes.length; j++) {
                if (j == this.attributes.length-1) {
                  formatado.println(this.doenca); //coloca o nome da doenca
                }
                else {
                  formatado.println(this.sintomas[j]+ ","); //nao sei se isso funciona, mas preciso adicionar a virgula
                  //coloca t ou f
                }
              }
            }
          }
          formatado.close();
          System.out.println("Gravacao realizada com sucesso!");
        }
        catch (IOException erro) {
          System.out.println("Nao consegui criar o arquivo =(");
        }
    }
}
