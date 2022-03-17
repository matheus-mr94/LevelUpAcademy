package br.com.levelupacademy.main;

import br.com.levelupacademy.dao.CourseDAO;
import br.com.levelupacademy.models.course.Course;
import br.com.levelupacademy.models.output.ReportWriter;
import br.com.levelupacademy.models.subcategory.Subcategory;

import java.sql.Connection;
import java.sql.SQLException;

import static br.com.levelupacademy.dao.SubcategoryDAO.getSubcategory;
import static br.com.levelupacademy.factory.ConnectionFactory.recoverConnection;

public class PersistenceTest {
    public static void main(String[] args) {

        Subcategory subcategory = getSubcategory("java");
        Course course = new Course("Java JRE e JDK: Escreva o seu primeiro código com Eclipse", "java-primeiros-passos", 8, "Desenvolvedores que querem começar com a linguagem Java.", true,"Paulo Silveira", "-O que é Java? <br> *A plataforma Java <br> *Benefício da JVM <br> *Quais características? <br> *Quais sistemas? <br> *Bytecode vs EXE? <br> *Sobre o Bytecode <br> *Para saber mais: o nome Bytecode <br>  <br> -Instalação e o primeiro programa <br> *Instalação do JDK no Windows <br> *JRE vs JDK <br> *Para saber mais: JVM vs JRE vs JDK <br> *Compile e rode seu primeiro programa Java <br> *Entrada da aplicação <br> *Sobre a compilação e execução <br> *Compilar e executar <br> *Mão na massa: Instalando o JDK <br> *Mão na massa: Escrevendo nosso primeiro código <br>  <br> -Começando com Eclipse <br> *Instalando o Eclipse <br> *Mão na massa: Instale a IDE Eclipse <br> *Sobre IDE's e editores <br> *Dentro do Eclipse IDE <br> *Nosso programa rodando no Eclipse <br> *Mão na massa: Rodando nosso programa no Eclipse <br> *Projeto Java <br> *Sobre a View Navigator <br>  <br> -Tipos e variáveis <br> *Tipo inteiro: int <br> *Criando uma variável numérica <br> *Mão na massa: Utilizando o tipo int <br> *Tipo flutuante: double <br> *Operações entre numeros <br> *Mão na massa: Utilizando o tipo double <br> *Conversões e outros tipos <br> *Imprimindo texto e números <br> *Mão na massa: Algumas conversões em Java <br> *Para saber mais: Type Casting <br>  <br> -Trabalhando com caracteres <br> *Char e String <br> *Declarando String e char <br> *Qual será o resultado? <br> *Variáveis guardam valores <br> *Concatenação de String e inteiros <br> *Mão na massa: Praticando char e String <br>  <br> -Praticando condicionais <br> *Testes com IF <br> *Trabalhando com if <br> *Mão na massa: A condicional if <br> *Boolean condicionais <br> *Tipo boolean <br> *Operador lógico <br> *Mão na massa: Um pouco mais de if <br> *Escopo e inicialização de variáveis <br> *Declaração da variável <br> *Mão na massa: Escopo de variáveis <br> *Opcional: Alíquota com ifs <br> *Para saber mais: o comando switch <br>  <br> -Controlando fluxo com laços <br> *Laço com while <br> *Enquanto isso o while... <br> *Fixando o laço while <br> *Escopo nos laços <br> *Um erro de compilação... <br> *Laço com for <br> *Transformando while em for <br> *Mão na massa: Laços <br> *Laços encadeados <br> *Mais laços com break <br> *Fixando o comando break <br> *Exercitando laços aninhados e break <br> *Mão na massa: Aprofundando laços <br> *Desafio Opcional: Múltiplos de 3 <br> *Desafio opcional: Fatorial", "JVM? JDK? JRE? Aprenda o que são essas siglas? <br> Compile e execute código java <br> Aprenda a usar Eclipse <br> Veja como usar variáveis e controle de fluxo <br> Conheça os principais tipos do Java", subcategory);

        try(Connection connection = recoverConnection()) {
          CourseDAO courseDAO = new CourseDAO(connection);
          courseDAO.insertCourse(course);
//          courseDAO.deleteCourse("java-primeiros-passos");
//          courseDAO.updateCourseToPublic();
//            ReportWriter rw = new ReportWriter();
//            rw.writeReport();
//            System.out.println("Html created");

        } catch (SQLException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
