-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: LevelUpAcademy
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `section_id` bigint NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FKswq749psim40jm27n6alc2oa9` (`section_id`),
  CONSTRAINT `fk_activity_section` FOREIGN KEY (`section_id`) REFERENCES `Activity` (`id`),
  CONSTRAINT `FKswq749psim40jm27n6alc2oa9` FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Alternative`
--

DROP TABLE IF EXISTS `Alternative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Alternative` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `text` varchar(150) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `correct` tinyint(1) DEFAULT NULL,
  `justification` varchar(500) DEFAULT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_alternative_question` (`question_id`),
  CONSTRAINT `fk_alternative_question` FOREIGN KEY (`question_id`) REFERENCES `Question` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alternative`
--

LOCK TABLES `Alternative` WRITE;
/*!40000 ALTER TABLE `Alternative` DISABLE KEYS */;
/*!40000 ALTER TABLE `Alternative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `study_guide` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL,
  `hex_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (1,'Programação','programacao','Programe nas principais linguagens e plataformas. Iniciantes são bem vindos nos cursos de lógica e JavaScript.','',1,1,'https://www.alura.com.br/assets/api/formacoes/categorias/512/programacao-transparent.png','#00c86f'),(2,'DevOps','devops','Aprenda Git. Entenda a entrega contínua. Estude Linux. Gerencie servidores na nuvem. Explore o mundo de Internet das coisas e da robótica.','',1,2,'https://www.alura.com.br/assets/api/formacoes/categorias/512/devops-transparent.png','#f16165'),(3,'Business','business','Agilidade. Práticas de gestão. Vendas. Liderança.','',0,0,'https://www.alura.com.br/assets/api/formacoes/categorias/512/inovacao-gestao-transparent.png','#ff8c2a');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `estimated_time_in_hours` int unsigned NOT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `instructor` varchar(50) NOT NULL,
  `syllabus` text,
  `developed_skills` text,
  `subcategory_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_course_subcategory` (`subcategory_id`),
  CONSTRAINT `fk_course_subcategory` FOREIGN KEY (`subcategory_id`) REFERENCES `Subcategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'Git e Github para Sobrevivência','git-e-github-para-sobrevivencia',6,1,'Desenvolvedores em qualquer linguagem ou plataforma que desejam mais segurança para seus projetos com as ferramentas de controle de versão Git e GitHub.','Mario Souto','-O que é Git? <br> *Introdução <br> *Para que serve Git? <br> *Utilidade de um VCS <br> *Instalando o Git <br> *Para saber mais: Instalação <br> *Repositórios <br> *Primeiros passos <br>  <br> -Iniciando os trabalhos <br> *Salvando alterações <br> *Primeira configuração do Git <br> *Para saber mais: git status <br> *Vendo o histórico <br> *Para saber mais: git log <br> *Ignorando arquivos <br> *Para saber mais: Quando commitar <br>  <br> -Compartilhando o trabalho <br> *Repositórios remotos <br> *Servidor Git <br> *Trabalhando com repositórios remotos <br> *Sincronizando os dados <br> *Compartilhamos as alterações <br> *GitHub <br> *Para saber mais: GitHub <br>  <br> -Trabalhando em equipe <br> *Branches <br> *Para saber mais: Ramificações <br> *Unindo o trabalho <br> *Merge de branches <br> *Atualizando a branch <br> *Rebase vs Merge <br> *Resolvendo conflitos <br> *Para saber mais: Conflitos com rebase <br>  <br> -Manipulando as versões <br> *Ctrl+Z no Git <br> *Desfazendo o trabalho <br> *Guardando para depois <br> *Salvando temporariamente <br> *Viajando no tempo <br> *Checkout <br>  <br> -Gerando entregas <br> *Vendo as alterações <br> *Exibição das mudanças com o diff <br> *Tags e releases <br> *Tags no GitHub <br> *Consolidando o seu conhecimento','Descubra o que é Git e Github? <br> Entenda um sistema de controle de versão <br> Salve e recupere seu código em diferentes versões <br> Resolva merges e conflitos <br> Trabalhe com diferentes branches',5),(2,'Java e JPA: Consultas avançadas performance e modelos complexos','java-jpa-consultas-avancadas-performance-modelos-complexos',10,1,'Pessoas desenvolvedoras que já conhecem o básico de JPA e queiram aprofundar os conhecimentos.','Rodrigo Ferreira','-Mais relacionamentos <br> *Apresentação <br> *Mapeando novas entidades <br> *Relacionamentos many-to-many <br> *Relacionamentos bidirecionais <br> *Teste do relacionamento bidirecional <br>  <br> -Consultas avançadas <br> *Consultas com funções de agregação <br> *Consultas para relatórios <br> *Consultas com select new <br> *Utilizando Named Queries <br>  <br> -Performance de consultas <br> *Entendendo Lazy e Eager <br> *Consultas com Join Fetch <br>  <br> -Criteria API <br> *Consultas com parâmetros dinâmicos <br> *Consultas com Criteria API <br>  <br> -Outros tópicos <br> *Simplificando entidades com Embeddable <br> *Mapeamento de herança <br> *Mapeamento de chaves compostas <br> *Conclusão','Saiba como modelar corretamente relacionamentos bidirecionais <br> Aprenda a utilizar o recurso de select new para realizar consultas avançadas <br> Entenda a diferença entre relacionamentos EAGER e LAZY <br> Conheça o recurso de join fetch para planejar queries <br> Conheça a API de Criteria da JPA <br> Saiba como mapear entidades que utilizam herança e chave composta',2),(3,'Java OO: Introdução à Orientação a Objetos','java-introducao-orientacao-objetos',8,1,'Desenvolvedores que estão começando com Java e querem aprender mais sobre OO.','Paulo Silveira','-O problema do paradigma procedural <br> *Paradigma procedural vs Objetos <br> *Idéia central do paradigma OO <br> *Cheiro procedural <br>  <br> -Começando com Orientação a Objetos <br> *Primeira classe - Contas <br> *Características dos objetos <br> *Instanciação atributos e referências <br> *Definindo tipos <br> *Segunda Instância <br> *Valores default de atributos <br> *Definindo valor de atributos <br> *Referências vs Objetos <br> *Referências de objetos <br> *Mão na massa: Criando as primeiras classes <br>  <br> -Definindo comportamento <br> *Nosso primeiro método <br> *Sobre métodos <br> *Como chamar um método? <br> *Você conhece o this? <br> *Métodos com retorno <br> *Métodos validos <br> *Onde usar o this? <br> *Métodos com referência e mais retorno <br> *Declaração do método <br> *Mão na massa: Criando métodos <br>  <br> -Composição de objetos <br> *Composição de Objetos <br> *Extraindo o que é comum  <br> *Referência Null <br> *Problema não esperado <br> *Solucionando o problema no código  <br> *O que aprendemos? <br> *Mão na massa: Referências <br>  <br> -Encapsulamento e visibilidade <br> *Atributos privados e encapsulamento <br> *Público x Privado <br> *Getters e Setters <br> *Criando Getters e Setters <br> *Getters e Setters de referência <br> *Vantagens de atributos privados <br> *Mão na massa: Criando Getters e Setters <br> *Para saber mais: Cuidado com o Modelo Anêmico <br>  <br> -Construtores e membros estáticos <br> *Construtores <br> *Utilizando Construtores <br> *Aonde está o erro? <br> *Static <br> *Por que não soma? <br> *Mãos na massa: Criando construtores e variáveis estáticas <br> *Para saber mais: Reaproveitamento entre construtores','Domine o paradigma de programação mais usado no mercado de trabalho <br> Entenda o que são referências e objetos <br> Use atributos métodos da instancia e da classe <br> Define objetos através de construtores <br> Aprenda sobre encapsulamento',1),(4,'Java JRE e JDK: Escreva o seu primeiro código com Eclipse','java-primeiros-passos',8,1,'Desenvolvedores que querem começar com a linguagem Java.','Paulo Silveira','-O que é Java? <br> *A plataforma Java <br> *Benefício da JVM <br> *Quais características? <br> *Quais sistemas? <br> *Bytecode vs EXE? <br> *Sobre o Bytecode <br> *Para saber mais: o nome Bytecode <br>  <br> -Instalação e o primeiro programa <br> *Instalação do JDK no Windows <br> *JRE vs JDK <br> *Para saber mais: JVM vs JRE vs JDK <br> *Compile e rode seu primeiro programa Java <br> *Entrada da aplicação <br> *Sobre a compilação e execução <br> *Compilar e executar <br> *Mão na massa: Instalando o JDK <br> *Mão na massa: Escrevendo nosso primeiro código <br>  <br> -Começando com Eclipse <br> *Instalando o Eclipse <br> *Mão na massa: Instale a IDE Eclipse <br> *Sobre IDE\'s e editores <br> *Dentro do Eclipse IDE <br> *Nosso programa rodando no Eclipse <br> *Mão na massa: Rodando nosso programa no Eclipse <br> *Projeto Java <br> *Sobre a View Navigator <br>  <br> -Tipos e variáveis <br> *Tipo inteiro: int <br> *Criando uma variável numérica <br> *Mão na massa: Utilizando o tipo int <br> *Tipo flutuante: double <br> *Operações entre numeros <br> *Mão na massa: Utilizando o tipo double <br> *Conversões e outros tipos <br> *Imprimindo texto e números <br> *Mão na massa: Algumas conversões em Java <br> *Para saber mais: Type Casting <br>  <br> -Trabalhando com caracteres <br> *Char e String <br> *Declarando String e char <br> *Qual será o resultado? <br> *Variáveis guardam valores <br> *Concatenação de String e inteiros <br> *Mão na massa: Praticando char e String <br>  <br> -Praticando condicionais <br> *Testes com IF <br> *Trabalhando com if <br> *Mão na massa: A condicional if <br> *Boolean condicionais <br> *Tipo boolean <br> *Operador lógico <br> *Mão na massa: Um pouco mais de if <br> *Escopo e inicialização de variáveis <br> *Declaração da variável <br> *Mão na massa: Escopo de variáveis <br> *Opcional: Alíquota com ifs <br> *Para saber mais: o comando switch <br>  <br> -Controlando fluxo com laços <br> *Laço com while <br> *Enquanto isso o while... <br> *Fixando o laço while <br> *Escopo nos laços <br> *Um erro de compilação... <br> *Laço com for <br> *Transformando while em for <br> *Mão na massa: Laços <br> *Laços encadeados <br> *Mais laços com break <br> *Fixando o comando break <br> *Exercitando laços aninhados e break <br> *Mão na massa: Aprofundando laços <br> *Desafio Opcional: Múltiplos de 3 <br> *Desafio opcional: Fatorial','JVM? JDK? JRE? Aprenda o que são essas siglas? <br> Compile e execute código java <br> Aprenda a usar Eclipse <br> Veja como usar variáveis e controle de fluxo <br> Conheça os principais tipos do Java',1);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Explanation`
--

DROP TABLE IF EXISTS `Explanation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Explanation` (
  `text` varchar(255) DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_explanation_activity` (`activity_id`),
  CONSTRAINT `fk_explanation_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Explanation`
--

LOCK TABLES `Explanation` WRITE;
/*!40000 ALTER TABLE `Explanation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Explanation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Question`
--

DROP TABLE IF EXISTS `Question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Question` (
  `statement` varchar(255) NOT NULL,
  `question_type` enum('SINGLE_CHOICE','MULTIPLES_CHOICES','TRUE_OR_FALSE') DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_question_activity` (`activity_id`),
  CONSTRAINT `fk_question_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_STUDENT');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Section`
--

DROP TABLE IF EXISTS `Section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Section` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `exam` tinyint(1) DEFAULT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_section_course` (`course_id`),
  CONSTRAINT `fk_section_course` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Section`
--

LOCK TABLES `Section` WRITE;
/*!40000 ALTER TABLE `Section` DISABLE KEYS */;
/*!40000 ALTER TABLE `Section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Subcategory`
--

DROP TABLE IF EXISTS `Subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Subcategory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `code` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `study_guide` varchar(255) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `sequence` int unsigned DEFAULT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `fk_subcategory_category` (`category_id`),
  CONSTRAINT `fk_subcategory_category` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Subcategory`
--

LOCK TABLES `Subcategory` WRITE;
/*!40000 ALTER TABLE `Subcategory` DISABLE KEYS */;
INSERT INTO `Subcategory` VALUES (1,'Java','java','Java é uma grande plataforma presente em todo lugar: de corporações à bancos e governo. Desenvolva aplicações robustas com um back-end e construa APIs.','',1,1,1),(2,'Java e Persistência','java-e-persistencia','','',1,2,1),(3,'PHP','php','PHP é uma das linguagens mais utilizadas.','',1,3,1),(4,'COBOL','cobol','','',0,0,1),(5,'Builds e Controle de versão','builds-e-controle-de-versao','As ferramentas mais utilizadas para desenvolvimento: controle de versão com Git e Github além de build da aplicação através de Maven.','',1,1,2);
/*!40000 ALTER TABLE `Subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'admin@email.com','admin','$2a$10$wDnEKq1mEfRqbMC/tT57weGzdsWmfAB4x.4aAbzlqqBtyo4Vnzq4i'),(2,'aluno@email.com','aluno','$2a$10$wDnEKq1mEfRqbMC/tT57weGzdsWmfAB4x.4aAbzlqqBtyo4Vnzq4i');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Role`
--

DROP TABLE IF EXISTS `User_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User_Role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FK250qsoghxra2h8tqpfildi605` (`role_id`),
  KEY `FKpamkaxhj7jlv9f3lpsis1r018` (`user_id`),
  CONSTRAINT `FK250qsoghxra2h8tqpfildi605` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
  CONSTRAINT `FKpamkaxhj7jlv9f3lpsis1r018` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Role`
--

LOCK TABLES `User_Role` WRITE;
/*!40000 ALTER TABLE `User_Role` DISABLE KEYS */;
INSERT INTO `User_Role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `User_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Video`
--

DROP TABLE IF EXISTS `Video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Video` (
  `url` varchar(255) NOT NULL,
  `duration_in_minutes` int unsigned DEFAULT NULL,
  `transcription` varchar(255) DEFAULT NULL,
  `activity_id` bigint NOT NULL,
  KEY `fk_video_activity` (`activity_id`),
  CONSTRAINT `fk_video_activity` FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Video`
--

LOCK TABLES `Video` WRITE;
/*!40000 ALTER TABLE `Video` DISABLE KEYS */;
/*!40000 ALTER TABLE `Video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-02 17:16:07
