package com.absoft.model;

import lombok.Getter;

/**
 * Permissões dos Usuários
 * @author Diego Arantes
 * Data alteração 15/12/2016
 */
public enum Permissao {
    SYSALL("Todas as permissões do sistema");

    @Getter
    private final String descricao;

    private Permissao(String descricao) {
        this.descricao = descricao;
    }
}
