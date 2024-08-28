/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ementor;

/**
 *
 * @author Vincenzo
 */
public class Aluno extends Pessoa {
    private int matricula, periodo;
    
    public Aluno (){
        super();
        this.matricula = 0;
        this.periodo = 0;
    }

    public Aluno(int matricula, int periodo, String nome, String data_nascimento, long CPF, String telefone) {
        super(nome, data_nascimento, CPF, telefone);
        this.matricula = matricula;
        this.periodo = periodo;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    
    
    
}
