package ementor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
 import java.sql.ResultSet;
import java.util.ArrayList;

public class ConexoesMySQL {
    
    /*Secão de atributos/variaveis iniciais */
    private final String caminho = "localhost"; //Indica que usaremos o server na máquina
    private final String porta = "3306"; //Porta padrão de Conexão do MySQL Server
    private final String nome = "ementor"; //Nome da nossa base de dados
    private final String usuario = "root"; //Usuario padrão do MySQL
    private final String senha = "admin"; // Senha definida no momento da instalação do MySQL
    private final String FusoHorario = "?useTimezone=true&serverTimezone=UTC";
    private final String URL ="jdbc:mysql://"+caminho+":"+porta+"/"+nome+FusoHorario; //Ajusta o fuso horário em relação a sede da Oracle 
    
    public Connection realizaConexaoMySQL(){
        try{
            return DriverManager.getConnection(URL,usuario,senha); //Estabelece a conexão via conector j
            
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          return null;
        }        
    }
    
    /////////////////////////////////////////////////////////////////////
    public void desconectaMySQL(Connection conexao){
        try{
           if (conexao != null) 
             conexao.close();
            
        }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }   
    }
    //////////////////////////////////////////////////////////////////////
    public void insereDadosNoMySQL(String nome, long CPF, String contato,String Data, long matricula, int periodo){
        Connection conexao = realizaConexaoMySQL();
        String sql_pessoa = "insert into pessoa (CPF, Nome, Telefone, DataNascimento) values (?,?,?,?)";
        String sql_aluno = "insert into aluno (Matricula,CPF_Pessoa,Periodo) values (?,?,?)";
        
        try{//Aqui serão aplicados todos os recursos para efetivar a inserção dos daddo nas Tabelas do MySQL
            PreparedStatement Atuador_pessoa = conexao.prepareStatement(sql_pessoa);
            PreparedStatement Atuador_aluno = conexao.prepareStatement(sql_aluno);
            
            /*Seção para setar os campos no atuador */
            Atuador_pessoa.setLong(1, CPF);//Substitui a primeira interrogação no insert into aluno
            Atuador_pessoa.setString(2, nome);//Substitui a segunda interrogação no insert into aluno
            Atuador_pessoa.setString(3, contato); //Substitui a terceira interrogação no insert into aluno
            Atuador_pessoa.setString(4, Data);//Substitui a última interrogação no insert into aluno
            
            Atuador_pessoa.execute();//Nesse momento estará sendo lançado o comando insert into "fisisicamente" no MySQL
            
            // Idem ao passos anteriores
            Atuador_aluno.setLong(1,matricula);
            Atuador_aluno.setLong(2, CPF);
            Atuador_aluno.setInt(3, periodo);   
            
            Atuador_aluno.execute();
            JOptionPane.showMessageDialog(null,"Cadastro Realizado com Sucesso","Salvar",JOptionPane.INFORMATION_MESSAGE);
          }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao);
    }  
    
    public void insereDadosProfessorNoMySQL(String nome, long CPF, String contato,String Data, String data_admissao, double salario_bruto){
        Connection conexao = realizaConexaoMySQL();
        String sql_pessoa = "insert into pessoa (CPF, Nome, Telefone, DataNascimento) values (?,?,?,?)";
        String sql_professor = "insert into professor (Data_Admissao,CPF_Pessoa,Salario_Bruto) values (?,?,?)";
        
        try{//Aqui serão aplicados todos os recursos para efetivar a inserção dos daddo nas Tabelas do MySQL
            PreparedStatement Atuador_pessoa = conexao.prepareStatement(sql_pessoa);
            PreparedStatement Atuador_professor = conexao.prepareStatement(sql_professor);
            
            /*Seção para setar os campos no atuador */
            Atuador_pessoa.setLong(1, CPF);//Substitui a primeira interrogação no insert into aluno
            Atuador_pessoa.setString(2, nome);//Substitui a segunda interrogação no insert into aluno
            Atuador_pessoa.setString(3, contato); //Substitui a terceira interrogação no insert into aluno
            Atuador_pessoa.setString(4, Data);//Substitui a última interrogação no insert into aluno
            
            Atuador_pessoa.execute();//Nesse momento estará sendo lançado o comando insert into "fisisicamente" no MySQL
            
            // Idem ao passos anteriores
            Atuador_professor.setString(1,data_admissao);
            Atuador_professor.setLong(2, CPF);
            Atuador_professor.setDouble(3, salario_bruto);   
            
            Atuador_professor.execute();
            JOptionPane.showMessageDialog(null,"Cadastro Realizado com Sucesso","Salvar",JOptionPane.INFORMATION_MESSAGE);
          }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao);
    }  
    //////////////////////////////////////////////////////////////////////
   public void atualizaDadosNoMySQL(String cpf, String matricula, String periodo) {
    Connection conexao = realizaConexaoMySQL();

    // SQL corrigido para garantir a atualização correta na tabela 'aluno' enquanto referencia 'pessoa'
    String sql = "UPDATE ementor.aluno "
               + "JOIN ementor.pessoa ON ementor.aluno.CPF_Pessoa = ementor.pessoa.CPF "
               + "SET ementor.aluno.Periodo = ?,"
               + "ementor.aluno.Matricula = ?"
               + "WHERE ementor.aluno.CPF_Pessoa = ?;";

    try {
        PreparedStatement Atuador = conexao.prepareStatement(sql);
        System.out.println("SQL: " + Atuador);
        // Debugging: Exibe os valores que serão usados para a atualização
        System.out.println("Matricula: " + matricula);
        System.out.println("Periodo: " + periodo);
        
        Atuador.setString(1, periodo);  // Substitui o primeiro '?' pelo valor de 'periodo'
        Atuador.setString(2, matricula); // Substitui o segundo '?' pelo valor de 'matricula'
        Atuador.setString(3, cpf); // Substitui o segundo '?' pelo valor de 'matricula'
        

        // Executa a atualização e captura o número de linhas afetadas
        Atuador.executeUpdate();
        Atuador.close();
        
        // Verifica se a atualização foi bem-sucedida
        JOptionPane.showMessageDialog(null, "Dados Atualizados com Sucesso", "Salvar", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    } finally {
        desconectaMySQL(conexao);  // Garante que a conexão será fechada
    }
}


    
    public void atualizaDadosProfessorNoMySQL(String cpf, String Data_Admissao, double Salario_Bruto) {
    Connection conexao = realizaConexaoMySQL();
    
    String sql = "UPDATE ementor.professor "
               + "JOIN ementor.pessoa ON ementor.professor.CPF_Pessoa = ementor.pessoa.CPF "
               + "SET ementor.professor.Salario_bruto = ?,"
               + "ementor.professor.Data_admissao = ?"
               + "WHERE ementor.professor.CPF_Pessoa = ?;";

    try {
        PreparedStatement Atuador = conexao.prepareStatement(sql);
        Atuador.setDouble(1, Salario_Bruto);
        Atuador.setString(2, Data_Admissao);
        Atuador.setString(3, cpf);
        Atuador.executeUpdate();
        Atuador.close();
        JOptionPane.showMessageDialog(null, "Dados Atualizados com Sucesso", "Salvar", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    }
    desconectaMySQL(conexao);
}

    ///////////////////////////////////////////////////////////
    public ArrayList<Aluno> recuperaDadosDoMySQL(String tipoOrdenacao){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        ArrayList<Aluno> Academico = new ArrayList();//Cria uma lista chamada Academico do Tipo Aluno
        
        try{            
            String sql_selecao_aluno = "SELECT *FROM ementor.pessoa, ementor.aluno WHERE pessoa.CPF=aluno.CPF_Pessoa ORDER BY "+tipoOrdenacao+";";
            PreparedStatement atuador_selecao_aluno = conexao.prepareStatement(sql_selecao_aluno);
            ResultSet ResultadoSelecao = atuador_selecao_aluno.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                Aluno ObjetoAluno = new Aluno();//Cria objeto aluno
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoAluno.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoAluno.nome = ResultadoSelecao.getString("Nome");
                ObjetoAluno.data_nascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoAluno.telefone = ResultadoSelecao.getString("Telefone");
                ObjetoAluno.setMatricula(ResultadoSelecao.getInt("Matricula"));
                ObjetoAluno.setPeriodo(ResultadoSelecao.getInt("Periodo"));
                       
                Academico.add(ObjetoAluno);//Adiciona à Lista o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_aluno.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return Academico;
    }
    
    public ArrayList<Professor> recuperaDadosProfessorDoMySQL(String tipoOrdenacao){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        ArrayList<Professor> Academico = new ArrayList();//Cria uma lista chamada Academico do Tipo Aluno
        
        try{            
            String sql_selecao_professor = "SELECT *FROM ementor.pessoa, ementor.professor WHERE pessoa.CPF=professor.CPF_Pessoa ORDER BY "+tipoOrdenacao+";";
            PreparedStatement atuador_selecao_professor = conexao.prepareStatement(sql_selecao_professor);
            ResultSet ResultadoSelecao = atuador_selecao_professor.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                Professor ObjetoProfessor = new Professor();//Cria objeto professor
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoProfessor.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoProfessor.nome = ResultadoSelecao.getString("Nome");
                ObjetoProfessor.data_nascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoProfessor.telefone = ResultadoSelecao.getString("Telefone");
                ObjetoProfessor.setData_admissao(ResultadoSelecao.getString("Data_Admissao"));
                ObjetoProfessor.setSalario_bruto(ResultadoSelecao.getDouble("Salario_Bruto"));
                       
                Academico.add(ObjetoProfessor);//Adiciona à Lista o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_professor.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return Academico;
    }
    ///////////////////////////////////////////////////////////////////////////
     public Aluno buscaAluno(String CPF){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        Aluno Academico  = new Aluno();//Cria um vetor chamado Academico do Tipo Aluno
        Academico=null;
        try{            
            String sql_selecao_aluno = "SELECT *FROM ementor.pessoa, ementor.aluno WHERE pessoa.CPF=aluno.CPF_Pessoa and aluno.CPF_Pessoa="+CPF+";";
            PreparedStatement atuador_selecao_aluno = conexao.prepareStatement(sql_selecao_aluno);
            ResultSet ResultadoSelecao = atuador_selecao_aluno.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
           // ResultadoSelecao.previous();
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                //ResultadoSelecao.first();
                Aluno ObjetoAluno = new Aluno();//Cria objeto aluno
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoAluno.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoAluno.nome = ResultadoSelecao.getString("Nome");
                ObjetoAluno.data_nascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoAluno.telefone = ResultadoSelecao.getString("Telefone");
                ObjetoAluno.setMatricula(ResultadoSelecao.getInt("Matricula"));
                ObjetoAluno.setPeriodo(ResultadoSelecao.getInt("Periodo"));
                       
                Academico=ObjetoAluno;//Adiciona ao vetor de Aluno o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_aluno.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return Academico;
    }
    
    public Professor buscaProfessor(String CPF){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        Professor Academico  = new Professor();//Cria um vetor chamado Academico do Tipo Aluno
        Academico=null;
        try{            
            String sql_selecao_professor = "SELECT *FROM ementor.pessoa, ementor.professor WHERE pessoa.CPF=professor.CPF_Pessoa and professor.CPF_Pessoa="+CPF+";";
            PreparedStatement atuador_selecao_professor = conexao.prepareStatement(sql_selecao_professor);
            ResultSet ResultadoSelecao = atuador_selecao_professor.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
           // ResultadoSelecao.previous();
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                //ResultadoSelecao.first();
                Professor ObjetoProfessor = new Professor();//Cria objeto aluno
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoProfessor.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoProfessor.nome = ResultadoSelecao.getString("Nome");
                ObjetoProfessor.data_nascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoProfessor.telefone = ResultadoSelecao.getString("Telefone");
                ObjetoProfessor.setData_admissao(ResultadoSelecao.getString("Data_Admissao"));
                ObjetoProfessor.setSalario_bruto(ResultadoSelecao.getDouble("Salario_Bruto"));
                       
                Academico=ObjetoProfessor;//Adiciona ao vetor de Aluno o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_professor.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return Academico;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////
    
    public void insereUsuariosNoMySQL(String nome, String email, String login,String senha, int nivel_acesso){
        Connection conexao = realizaConexaoMySQL();
        String sql_usuario = "insert into usuario (Nome, Email, Login, Senha, Nivel_Acesso) values (?,?,?,?,?)";
        
        try{//Aqui serão aplicados todos os recursos para efetivar a inserção dos daddo nas Tabelas do MySQL
            PreparedStatement Atuador_usuario = conexao.prepareStatement(sql_usuario);
            
            /*Seção para setar os campos no atuador */
            Atuador_usuario.setString(1, nome);//Substitui a segunda interrogação no insert into aluno
            Atuador_usuario.setString(2, email); //Substitui a terceira interrogação no insert into aluno
            Atuador_usuario.setString(3, login);//Substitui a última interrogação no insert into aluno
            Atuador_usuario.setString(4, senha);//Substitui a última interrogação no insert into aluno
            Atuador_usuario.setInt(5, nivel_acesso);//Substitui a última interrogação no insert into aluno
            
            Atuador_usuario.execute();//Nesse momento estará sendo lançado o comando insert into "fisisicamente" no MySQL
 
            JOptionPane.showMessageDialog(null,"Cadastro Realizado com Sucesso","Salvar",JOptionPane.INFORMATION_MESSAGE);
          }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao);
    }
    
    public void atualizaUsuariosNoMySQL(String email, String login, String senha, int nivel_acesso){
        Connection conexao = realizaConexaoMySQL();
        String sql ="update ementor.usuario set Email="+email+", login="+login+", Senha="+senha+"and Nivel_Acesso="+nivel_acesso+";";
       
        //update ementor.pessoa set pessoa.Nome="Fulado de tal" where pessoa.CPF="12345678910". 
        try{//Aqui serão aplicados todos os recursos para efetivar a inserção dos daddo nas Tabelas do MySQL
            PreparedStatement Atuador = conexao.prepareStatement(sql);  
            
            /*Seção para setar os campos no atuador */          
            Atuador.execute();//Nesse momento estará sendo lançado o comando update "fisisicamente" no MySQL
                     
           JOptionPane.showMessageDialog(null,"Dados Atualizados com Sucesso","Salvar",JOptionPane.INFORMATION_MESSAGE);
          }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao);
    }
        
    public ArrayList<Usuario> recuperaUsuariosDoMySQL(String tipoOrdenacao){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        ArrayList<Usuario> user = new ArrayList();//Cria uma lista chamada Academico do Tipo Aluno
        
        try{            
            String sql_selecao_usuario = "SELECT *FROM ementor.usuario ORDER BY "+tipoOrdenacao+";";
            PreparedStatement atuador_selecao_usuario = conexao.prepareStatement(sql_selecao_usuario);
            ResultSet ResultadoSelecao = atuador_selecao_usuario.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                Usuario ObjetoUsuario = new Usuario();//Cria objeto aluno
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoUsuario.nome = ResultadoSelecao.getString("Nome");
                ObjetoUsuario.email = ResultadoSelecao.getString("Email");
                ObjetoUsuario.login = ResultadoSelecao.getString("Login");
                ObjetoUsuario.senha = (ResultadoSelecao.getString("Senha"));
                ObjetoUsuario.nivel_acesso = (ResultadoSelecao.getInt("Nivel_Acesso"));
                       
                user.add(ObjetoUsuario);//Adiciona à Lista o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_usuario.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return user;
    }
    
    public Usuario buscaUsuario(String login, String senha){
        Connection conexao = realizaConexaoMySQL();//Estabelece conexão
        Usuario user  = new Usuario();//Cria um vetor chamado Academico do Tipo Aluno
        user = null;
        try{            
            String sql_selecao_usuario = "SELECT *FROM ementor.usuario WHERE usuario.Login='"+login+"' and usuario.Senha='"+senha+"';";
            PreparedStatement atuador_selecao_usuario = conexao.prepareStatement(sql_selecao_usuario);
            ResultSet ResultadoSelecao = atuador_selecao_usuario.executeQuery(); //É aqui que fica o resultado da selação do MySQL
            /*Secao para percorrer todas as linhas resultantes da seleção- Logo, deve-se usar um laço de repetição  */
           // ResultadoSelecao.previous();
            while(ResultadoSelecao.next()){//Laço de repetição para percorrer todo o conjuto de resultados "ResultSet" trazidos pela Query
                //ResultadoSelecao.first();
                Usuario ObjetoUsuario = new Usuario();//Cria objeto aluno
                /*Seção para inserir em cada atributo do objetoAluno os valores dos campos do MySQL */
                ObjetoUsuario.nome = ResultadoSelecao.getString("Nome");
                ObjetoUsuario.email = ResultadoSelecao.getString("Email");
                ObjetoUsuario.login = ResultadoSelecao.getString("Login");
                ObjetoUsuario.senha = ResultadoSelecao.getString("Senha");
                ObjetoUsuario.nivel_acesso = (ResultadoSelecao.getInt("Nivel_Acesso"));
                       
                user = ObjetoUsuario;//Adiciona ao vetor de Aluno o Objeto Atual        
                      
            }
              ResultadoSelecao.close();
              atuador_selecao_usuario.close();                   
                    
             }catch(SQLException e){
              JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e+"","ERRO",JOptionPane.ERROR_MESSAGE);
          
        }
        desconectaMySQL(conexao); //Fecha a conexão do Banco de Dados
        return user;
    }
   }
