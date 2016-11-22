package csci4125.application.repository.impl;

import csci4125.application.model.BaseEntity;
import csci4125.application.repository.ICrudRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Implements data access methods for BaseEntity entities.
 *
 * @author tlmader.dev@gmail.com
 * @since 2016-11-16
 */
abstract class CrudRepository<T extends BaseEntity> implements ICrudRepository<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private BiConsumer<T, T> setFields;
    private Class<T> type;
    private String typeName;

    public CrudRepository(BiConsumer<T, T> setFields, Class<T> type) {
        this.setFields = setFields;
        this.type = type;
        this.typeName = this.type.getSimpleName();
    }

    @Override
    @Transactional
    public List<T> getAll() {
        Criteria allCriteria = getSession().createCriteria(type);
        return allCriteria.list();
    }

    @Override
    @Transactional
    public T get(String id) {
        if (id == null) {
            return null;
        }
        Criteria criteria = getSession().createCriteria(type);
        criteria.add(Restrictions.eq("id", id.toLowerCase()));
        List<T> results = criteria.list();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    @Transactional
    public T create(T entity) {
        if (entity.getId() == null) {
            throw new ClientErrorException(typeName + " ID is null", Response.Status.BAD_REQUEST);
        } else if (get(entity.getId()) != null) {
            throw new ClientErrorException(typeName + " ID in use", Response.Status.CONFLICT);
        }
        entity.setId(entity.getId().toLowerCase());
        Session session = getSession();
        session.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public T update(T entity) {
        T found = get(entity.getId());
        if (found == null) {
            throw new ClientErrorException(typeName + " not found", Response.Status.NOT_FOUND);
        }
        setFields.accept(found, entity);
        Session session = getSession();
        session.merge(found);
        return found;
    }

    @Override
    @Transactional
    public void delete(String id) {
        T found = get(id);
        if (found == null) {
            throw new ClientErrorException(typeName + " not found", Response.Status.NOT_FOUND);
        }
        Session session = getSession();
        session.delete(found);
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
