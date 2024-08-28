/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ementor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author vincenzo
 */
public class Usuario {
    protected String nome, email, login, senha;
    protected int nivel_acesso;
    
    public Usuario() {
        this.nome = "";
        this.email = "";
        this.login = "";
        this.senha = "";
        this.nivel_acesso = 0;
    }

    public Usuario(String nome, String email, String login, String senha, int nivel_acesso) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.nivel_acesso = nivel_acesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(int nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }
    
    public ResultSet autenticacaoUsuario(Usuario objusuario) {
        Connection cone;
        cone =  new ConexoesMySQL().realizaConexaoMySQL();

        try {
            String sql = "Select * from ementor.usuario where login = ? and senha = ?";
            
            PreparedStatement pstm =  cone.prepareStatement(sql);   //A string a ser modificada
            pstm.setString(1, objusuario.getNome());      //Na primeira interrogacao, coloca o que retornr de "objusuario.getNome_usuario()"
            pstm.setString(2, objusuario.getSenha());     //Na segunda interrogacao, coloca o que retornr de "objusuario.getSenha_usuario()"
            
            ResultSet rs = pstm.executeQuery();
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Usuario " + erro);
            return null;
        }

    }

}
