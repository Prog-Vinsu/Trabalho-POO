<h1>Trabalho: Ementor</h1>

Trabalho desenvolvido na disciplina de Programação Orientada a Objeto, onde o foco é o desenvolvimento de um sistema que gerencia o cadastro de alunos e professores, através do uso de interfaçes gráficas, e armazanar os dados cadastrados no banco de dados usando MySQL.

<h3>Enunciado do trabalho:</h3>

Desenvolva um programa em Java, denominado eMentor, que contenha as Classes Pessoa,
Aluno e Professor.
A Classe mãe Pessoa deve possuir os atributos de Nome, Data de Nascimento, CPF e Telefone.
Em relação aos métodos, deve ser implementado o método construtor padrão e método
construtor para inicializar todos os atributos. A Subclasse Aluno, filha de Pessoa, deve conter
os atributos de Matrícula e Período e os métodos: Construtor padrão; construtor para
inicializar todos os atributos; setDados para inicializar todos os atributos após a criação dos
objetos; getMatricula e getPeriodo para recuperar esses atributos; e outro método para
imprimir os dados (preferencialmente utilizar mensagens gráficas de dialogo). Quanto a
Subclasse Professor, filha de Pessoa, deve possuir os atributos de Data de Admissão e Salário
Bruto. Os métodos a serem implementados para essa subclasse são: Construtor padrão;
construtor para inicializar todos os atributos; setDados para inicializar os atributos depois de
criados os objetos; getDataAdmissao e getSalarioBruto para recuperar esses atributos;
imprimir dados; e calcular salário líquido (de forma simplificada considerar desconto de 14%
de INSS para todas as faixas de salário e 22,5% de IRPF para salários maiores ou iguais que R$
5.000,00, os salários menores que o valor citado devem ter desconto apenas de INSS).
Requisitos gerais para a solução:
1) Os atributos da classe mãe devem ter o modificador de acesso protegido e das
subclasses privados;
2) Utilizar interfaces gráficas para Cadastrar Alunos e Professores. Deve ter um
componente Seletor com as opções de Aluno e Professor;
3) Utilizar Interfaces gráficas contendo duas tabelas (JTable) contendo os dados de
alunos e professores cadastrados;
4) Deverá permitir alterar e excluir professores e alunos;
5) Aplicar o uso de ArrayList para guardar os dados e posteriormente, conforme ser
apresentado em aula, utilizar banco de dados MySQL.
