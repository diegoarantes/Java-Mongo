package com.absoft.repository;

import com.absoft.infra.MongoDBHelper;
import com.absoft.model.Usuario;
import javax.enterprise.context.RequestScoped;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author Diego Arantes
 */
@RequestScoped
public class Usuarios extends BaseRepository<Usuario> {

    Datastore datastore = MongoDBHelper.INSTANCE.getDatastore();

    public Usuarios() {
        super(Usuario.class);
    }

    public Usuario verificarUsuario(String usuario, String senha) {
        Query q = createQuery()
                .field("usuario").equal(usuario)
                .field("senha").equal(senha);
        if (q.count() > 0) {
            return (Usuario) q.get();
        } else {
            return null;
        }
    }

    @Override
    public Datastore getDatastore() {
        return datastore;
    }

}
