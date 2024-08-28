/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ementor;

/**
 *
 * @author Vincenzo
 */
public class Professor extends Pessoa {
    private String data_admissao;
    private double salario_bruto;
    
    public Professor() {
        super();
        this.data_admissao = "";
        this.salario_bruto = 0;
    }

    public Professor(String data_admissao, double salario_bruto, String nome, String data_nascimento, long CPF, String telefone) {
        super(nome, data_nascimento, CPF, telefone);
        this.data_admissao = data_admissao;
        this.salario_bruto = salario_bruto;
    }

    public String getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }

    public double getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(double salario_bruto) {
        this.salario_bruto = salario_bruto;
    }
    
    public double salario_liquido(double salario_bruto) {
        double salario_liquido;
        if (salario_bruto > 5000) {
            salario_liquido = salario_bruto * 0.225;
        } else {
            salario_liquido = salario_bruto * 0.14;
        }
        return salario_liquido;
    }
}
