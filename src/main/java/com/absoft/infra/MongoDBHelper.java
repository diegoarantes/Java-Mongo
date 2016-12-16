package com.absoft.infra;

import com.mongodb.MongoClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author Diego Arantes
 */
public enum MongoDBHelper {
    INSTANCE;

    private Datastore datastore;
    private Morphia morphia;

//private final String SERVER_URL = "...";
//private final int SERVER_PORT = 0;
//private final String USERNAME= "...";
//private final String PASSWORD = "...";
//private final String DATABASE_NAME = "...";
    private MongoDBHelper() {
        morphia = new Morphia();

        //Adiciona o Conversor para BigDecimal
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

        //Desabilita os logs do MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb");
        mongoLogger.setLevel(Level.SEVERE);

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
