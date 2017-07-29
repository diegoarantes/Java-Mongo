package com.absoft.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import com.absoft.model.BaseEntity;

/**
 *
 * @author Diego Arantes
 * @param <T> Classe
 */
public abstract class BaseRepository<T extends BaseEntity> {

    /**
     * Classe da entidade, necessário para o método
     * <code>EntityManager.find</code>.
     */
    protected Class<T> entityClass;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Salva ou Atualiza um Registro
     *
     * @param entity Entidade
     */
    public void save(T entity) {
        if (entity.getId() == null) {
            getDatastore().save(entity);
        } else {
            getDatastore().merge(entity);
        }
    }

    /**
     * Apaga um Registro
     *
     * @param entity Entidade
     */
    public void delete(T entity) {
        getDatastore().delete(entity);
    }

    /**
     * @param key Campo
     * @param value Valor
     * @return Um Registro de acordo com o campo filtrado
     */
    public T findOne(final String key, final Object value) {
        return getDatastore().find(entityClass).filter(key, value).get();
    }

    /**
     * @param key Campo
     * @param value Valor
     * @return Lista de Registro de acordo com o campo Filtrado
     */
    public List<T> find(String key, Object value) {
        return getDatastore().find(entityClass).filter(key, value).asList();
    }

    /**
     * @param id
     * @return Um Registro de acordo com ObjectId
     */
    public T findById(ObjectId id) {
        return getDatastore().get(entityClass, id);
    }

    /**
     * @return Lista de todos os Registros da entidade
     */
    public List<T> findAll() {
        Query<T> qr = getDatastore().find(entityClass);
        return qr.asList();
    }

    /**
     * @return Contagem de Registros da entidade
     */
    public long count() {
        return getDatastore().getCount(entityClass);
    }

    /**
     * Cria uma Query do Morphia
     *
     * @return Query
     */
    public Query<T> createQuery() {
        return getDatastore().createQuery(entityClass);
    }

    /**
     * Exige a definição do <code>EntityManager</code> responsável pelas
     * operações de persistência.
     *
     * @return
     */
    protected abstract Datastore getDatastore();
}
