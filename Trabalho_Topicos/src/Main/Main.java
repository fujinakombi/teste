package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import javaapplication1.Aluno;
import javaapplication1.Curso;
import javaapplication1.Departamento;
import javaapplication1.Disciplina;
import javaapplication1.Historico;
/**
 *
 * @author Gabriel Tolentino e João Pedro Leocárdio
 */
public class Main {
    
    static Scanner palavras = new Scanner(System.in);
    static Scanner numeros = new Scanner(System.in);
    static ArrayList<Disciplina> disc = new ArrayList<Disciplina>();
    static ArrayList<Aluno> students = new ArrayList<Aluno>();
    static ArrayList<Historico> history = new ArrayList<Historico>();
    static ArrayList<Curso> cursinho = new ArrayList<Curso>();
    
    public static void Cadastrar_Disciplina(Curso curso){
        
        System.out.println("Digite o nome do professor:");
        String Nome_professor = palavras.nextLine();
        
        System.out.println("Digite o nome da disciplina:");
        String Nome_disciplina = palavras.nextLine();
        
        System.out.println("Digite o numero da disciplina:");
        int Numero = numeros.nextInt();
        
        int Ano, Semestre;
        LocalDate date = LocalDate.now();
        Ano = date.getYear();
        if(date.getMonthValue() <= 6){
            Semestre = 1;
        }
        else{
            Semestre = 2;
        }
        
        disc.add(new Disciplina(Nome_disciplina , Nome_professor, Semestre, Ano, Numero, curso));
        System.out.println("Cadastro CONCLUÍDO com sucesso!");
        
    }
    
    public static void Matricular_Aluno_na_disciplina(){
        System.out.println("Digite o nome do aluno que deseja matricular:");
        String Alune = palavras.nextLine();
        System.out.println("Digite a disciplina que deseja matricular o aluno:");
        String Disci1 = palavras.nextLine();
        
        int cont=0, posicao=0;
        Aluno alunes = null;
        Disciplina discipli = null;
        
        do{ //faz a busca pelo nome de um aluno para cadastrá-lo
            for (Aluno a : students){
                if(a.getNome().equals(Alune)){
                    posicao = cont;
                    alunes = a;
                }
                cont++;
            }
        }while(students == null);
        
        do{ //faz a busca da disciplina em que o aluno será matriculado
            for(Disciplina d : disc){
                if(d.getNome_disciplina().equals(Disci1)){
                    discipli = d;
                    
                }
            }
        }while(discipli == null);
        
        history.get(posicao).adicionarConceito(null);
       
        history.get(posicao).adicionarDisciplina(discipli);
       
        history.get(posicao).adicionarNotaNumerica(0);
        
        System.out.println("Matricula CONCLUÍDA com sucesso!");
    }
    
    public static void Adicionar_Nota(){
        System.out.println("Digite a disciplina que deseja definir a nota do aluno:");
        String disciplina = palavras.nextLine();
        
        System.out.println("Digite o aluno que deseja atribuir a nota:");
        String aluno = palavras.nextLine();
        
        System.out.println("Digite a nota do aluno:");
        int nota = numeros.nextInt();
        
        String Conceito = null;
        if(nota>=0 & nota<11){
            Conceito="F";
        }
        if(nota>=11 & nota<=20){
            Conceito="E";
        }
        if(nota>=21 & nota<=40){
            Conceito="D";
        }
        if(nota>=41 & nota<=60){
            Conceito="C";
        }
        if(nota>=61 & nota<=80){
            Conceito="B";
        }
        if(nota>=81 & nota<=100){
            Conceito="A";
        }
        if(nota>100){
            System.out.println("Invalido");
        }
        
        
        System.out.println("Nota adicionada ao boletim do aluno!");
        
        
        for(Historico h : history){
            if(h.getAlunos().getNome().equals(aluno)){ //faz a busca do aluno que terá a nota inserida no boletim
                int posicao=0;
                for(int cont=0; cont<h.getDisciplinas().size();cont++){
                    if(h.getDisciplinas().get(cont).getNome().equals(disciplina)){ //faz a busca da disciplina em que será lançada a nota
                        posicao=cont;
                    }
                }

                h.setNotas_Numericas(posicao, nota);
                h.setConceito(posicao, Conceito);
                
               
            }
        }
    }
    
    public static void Listar() {
        System.out.println("Digite a disciplina que deseja ver a lista de aprovados e reprovados: ");
        String disciplina = palavras.nextLine();

        history.forEach((Historico h) -> {
            for (int cont = 0; cont < h.getDisciplinas().size(); cont++) { //faz a busca da disciplina que o usuário deseja ver a lista de aprovados e reprovados
                if (h.getDisciplinas().get(cont).getNome().equals(disciplina)) {
                    if (h.getNotas_Numericas().get(cont) < 60) {
                        System.out.println(h.getAlunos().getNome() + " - Reprovado");
                    }
                    else{
                        System.out.println(h.getAlunos().getNome() + " - Aprovado");
                    }
                    
                }
            }
        });
    }
    
    public static void main(String[] args) {
        int n = 0;
        do{
            System.out.println("""
                               Digite o número do que deseja fazer:
                               1- Cadastrar disciplina
                               2- Matricular aluno
                               3- Registro de nota de um aluno na disciplina 
                               4- Listar alunos aprovados e reprovados em uma disciplina 
                               5- Sair""");
            
            n = numeros.nextInt();
            if(n>6 || n<0){
                System.out.println("Digite um número válido!");
            }else{
                switch(n){
                    case 1 -> {
                        System.out.println("Digite o curso correspondente a essa disciplina: ");
                        String curso = palavras.nextLine();
                        Curso cursos = null;
                        for(Curso c : cursinho){
                            if(c.getNome().equals(curso)){
                                cursos = c;
                            }
                        }
                        Cadastrar_Disciplina(cursos);
                    }

                    case 2 -> Matricular_Aluno_na_disciplina();
                    case 3 -> Adicionar_Nota();
                    case 4 -> Listar();
                    default -> {
                    }
                }
            }
        }while(n!=5);
      
    }

}
