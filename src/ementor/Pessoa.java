/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ementor;

/**
 *
 * @author Vincenzo
 */
public class Pessoa {
    protected String nome, data_nascimento, telefone;
    protected long CPF;

    public Pessoa () {
        this.nome = "";
        this.data_nascimento = "";
        this.CPF = 0;
        this.telefone = "";
    }
    public Pessoa(String nome, String data_nascimento, long CPF, String telefone) {
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.CPF = CPF;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
