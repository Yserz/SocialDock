/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.welovecoding.base.repository;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class is a Facade-pattern and provides methods for standard database
 * operations like create, read, update and delete for all types of
 * domainobjects.
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
public abstract class AbstractRepository<T> {

  private final static Logger LOG = Logger.getLogger(AbstractRepository.class.getName());
  private Class<T> entityClass;

  public AbstractRepository(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  public abstract EntityManager getEntityManager();

  public abstract void setEntityManager(EntityManager em);

  /**
   * @see EntityManager#flush()
   */
  public void flush() {
    getEntityManager().flush();
  }

  /**
   * @see EntityManager#contains(java.lang.Object)
   */
  public boolean contains(T entity) {
    return getEntityManager().contains(entity);
  }

  /**
   * @see EntityManager#refresh(java.lang.Object)
   */
  public void refresh(T entity) {
    getEntityManager().refresh(entity);
  }

  /**
   * @see EntityManager#persist(java.lang.Object)
   */
  public void create(T entity) {
    getEntityManager().persist(entity);
  }

  /**
   * @see EntityManager#merge(java.lang.Object)
   */
  public void edit(T entity) {
    getEntityManager().merge(entity);
  }

  /**
   * @see EntityManager#remove(java.lang.Object)
   */
  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }

  /**
   * @see EntityManager#find(java.lang.Class, java.lang.Object)
   */
  public T find(Object id) {
    return getEntityManager().find(entityClass, id);
  }

  public List<T> findAll() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }

  public List<T> findRange(int[] range) {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(entityClass));
    Query q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0]);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }

  public int count() {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    Query q = getEntityManager().createQuery(cq);
    return ((Long) q.getSingleResult()).intValue();
  }
}
