package com.absoft.infra;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Diego Arantes
 */
public enum MongoDBHelper {
    INSTANCE;

    private final Datastore datastore;
    private final Morphia morphia;

//private final String SERVER_URL = "...";
//private final int SERVER_PORT = 0;
//private final String USERNAME= "...";
//private final String PASSWORD = "...";
//private final String DATABASE_NAME = "...";
    private MongoDBHelper() {
        morphia = new Morphia();

        //Adiciona o Conversor para BigDecimal
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

        // Cria a conexão com o MongoDB
        this.datastore = morphia.createDatastore(new MongoClient(), "absoft");

        //Indica o pacote onde ficarão as entidades
        morphia.mapPackage("com.absoft.model");

        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return this.datastore;
    }
}
