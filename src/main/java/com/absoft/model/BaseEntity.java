package com.absoft.model;

import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 * Interface de base para entidades que serão persistidas
 *
 * @author Diego Arantes Data alteração 16/12/2016
 */
public interface BaseEntity extends Serializable {

    public ObjectId getId();
}
