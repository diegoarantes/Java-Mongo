package com.absoft.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Usuários do sistema
 *
 * @author Diego Arantes Data alteração 15/12/2016
 */
@Entity(value = "usuarios", noClassnameStored = true)
@Data
public class Usuario implements BasicEntity {

    @Id
    private ObjectId id;

    private String nome;

    @Indexed(options = @IndexOptions(unique = true))
    private String usuario;
    private String senha;
    private String email;
    private boolean ativo;
    private boolean online;
    private String ipUltimoLogon;
    private Date horaUltimoLogon;

    @Embedded
    private List<Permissao> permissoes;
}
