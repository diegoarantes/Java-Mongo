package com.absoft.infra;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Diego Arantes
 */
public enum MongoDBHelper {
    INSTANCE;

    private final Datastore datastore;

    private final String SERVER_URL = "mongodb";
    private final int SERVER_PORT = 27017;
    private final String USERNAME = "userY1G";
    private final String PASSWORD = "Q1GHhVg4D7aP0LQ0";
    private final String DATABASE_NAME = "absoft";

    private MongoDBHelper() {
        final Morphia morphia = new Morphia();

        //Adiciona o Conversor para BigDecimal
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

        //Adiciona Credenciais
        ServerAddress addr = new ServerAddress(SERVER_URL, SERVER_PORT);
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createCredential(USERNAME, DATABASE_NAME, PASSWORD.toCharArray()));
        
        // Cria a conexão com o MongoDB | MongoClient params = addr, credentials
        this.datastore = morphia.createDatastore(new MongoClient(addr, credentials), DATABASE_NAME);

        //Indica o pacote onde ficarão as entidades
        morphia.mapPackage("com.absoft.model");

        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        return this.datastore;
    }
}
