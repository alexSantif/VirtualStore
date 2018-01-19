package com.br.virtualstore.entity;

/**
 * Created by Alex on 18/01/2018.
 */

public class User {

    private String nome;
    private String email;
    private String miniBio;
    private char sexo;
    private int codProfissao;

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

    public String getMiniBio() {
        return miniBio;
    }

    public void setMiniBio(String miniBio) {
        this.miniBio = miniBio;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCodProfissao() {
        return codProfissao;
    }

    public void setCodProfissao(int codProfissao) {
        this.codProfissao = codProfissao;
    }
}
