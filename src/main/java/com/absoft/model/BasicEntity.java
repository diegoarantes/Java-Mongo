package com.absoft.model;

import org.bson.types.ObjectId;

/**
 * Interface de base para entidades que serão persistidas
 * @author Diego Arantes
 * Data alteração 12/12/2016
 */
public interface BasicEntity {

    public ObjectId getId();
}
