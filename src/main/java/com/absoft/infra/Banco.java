package com.absoft.infra;

import com.mongodb.MongoClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Configuração da Conexão com o MongoDB
 * @author Diego Arantes
 * Data alteração 12/12/2016
 */
public class Banco {

    //Novo Objeto Morphia
    final Morphia morphia = new Morphia();

    public Datastore getDatastore() {
        //Adiciona o Conversor para BigDecimal
        morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);

        //Desabilita os logs do MongoDB
        Logger mongoLogger = Logger.getLogger("org.mongodb");
        mongoLogger.setLevel(Level.SEVERE);

        // Cria a conexão com o MongoDB
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "absoft");

        //Indica o pacote onde ficarão as entidades
        morphia.mapPackage("com.absoft.model");

        datastore.ensureIndexes();

        return datastore;
    }
}
