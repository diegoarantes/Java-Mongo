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

    private final String SERVER_URL = "localhost";
    private final int SERVER_PORT = 27017;
    private final String USERNAME = "";
    private final String PASSWORD = "";
    private final String DATABASE_NAME = "absoft";

    private MongoDBHelper() {
        final Morphia morphia = new Morphia();

        //Adiciona o Conversor para BigDecimal
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

//        ServerAddress addr = new ServerAddress(SERVER_URL, SERVER_PORT);
//        List<MongoCredential> credentials = new ArrayList<>();
//        credentials.add(MongoCredential.createCredential(USERNAME, DATABASE_NAME, PASSWORD.toCharArray()));
        // Cria a conexão com o MongoDB params = addr, credentials
        this.datastore = morphia.createDatastore(new MongoClient(), DATABASE_NAME);

        //Indica o pacote onde ficarão as entidades
        morphia.mapPackage("com.absoft.model");

        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return this.datastore;
    }
}
