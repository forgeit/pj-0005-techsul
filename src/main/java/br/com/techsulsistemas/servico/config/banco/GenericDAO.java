/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.config.banco;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author kelvin
 */
public class GenericDAO<T> {

    private final Class<T> persistentClass;

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        
        try {
            DAO.rollback();
            DAO.close();
        } catch (Exception ex) {
            DAO.rollback();
            DAO.close();
        }
    }

    public List<T> findAll() {
        try {
            String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t";
            Query q = DAO.getEM().createQuery(sql);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
    
    public List<T> findAllOrderBy(String coluna) {
        try {
            String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t ORDER BY t." + coluna;
            Query q = DAO.getEM().createQuery(sql);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
    
    public List<T> buscarSemelhantes(String coluna, String valor) throws Exception {
        String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE t." + coluna + " LIKE :valor";
        System.out.println(sql);
        return (List<T>) DAO.getEM().createQuery(sql)
                .setParameter("valor", "%" + valor + "%")
                .getResultList();
    }
    
    public T encontrar(Object id) throws Exception {
        return DAO.getEM().find(persistentClass, id);
    }
    
    public T encontrarPrimeiro(String coluna) throws Exception {
        return (T) DAO.getEM().createQuery("SELECT t FROM " + persistentClass.getSimpleName() + " t ORDER BY t." + coluna + " ASC ")
                .setMaxResults(1)
                .getSingleResult();
    }
    
    public T proximo (String coluna, Integer ultimoId) throws Exception {
        return (T) DAO.getEM().createQuery("SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE t." + coluna + " > :id ORDER BY t." + coluna + " ASC")
                .setParameter("id", ultimoId)
                .setMaxResults(1)
                .getSingleResult();
    }
    
    public T anterior (String coluna, Integer ultimoId) throws Exception {
        return (T) DAO.getEM().createQuery("SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE t." + coluna + " < :id ORDER BY t." + coluna + " DESC")
                .setParameter("id", ultimoId)
                .setMaxResults(1)
                .getSingleResult();
    }
    
    public T encontrarUltimo(String coluna) throws Exception {
        return (T) DAO.getEM().createQuery("SELECT t FROM " + persistentClass.getSimpleName() + " t ORDER BY t." + coluna + " DESC ")
                .setMaxResults(1)
                .getSingleResult();
    }
    
    public void remover(Object id) throws Exception {
        T entidade = DAO.getEM().getReference(persistentClass, id);
        
        try {
            DAO.begin();
            DAO.getEM().remove(entidade);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            throw ex;
        }
    }
    
    public void remove(Object id) {
        T entity = DAO.getEM().getReference(persistentClass, id);
        DAO.getEM().remove(entity);
    }
    
    public long count() {
        try {
            String sql = "SELECT count(t) FROM " + persistentClass.getSimpleName() + " t";
            Query q = DAO.getEM().createQuery(sql);
            return (long) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }

    public List<T> findRange(int range[]) {
        try {
            String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t";

            Query q = DAO.getEM().createQuery(sql);

            return q.setFirstResult(range[1])
                    .setMaxResults(range[2])
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public boolean isNotUnique(String tabela, String coluna, Object value, Object id) {
        return isNotUnique(tabela, coluna, value, id, null);
    }

    public boolean isNotUnique(String tabela, String coluna, Object value, Object id, String nomePk) {
        if (value == null) {
            return false;
        }

        if (nomePk == null) {
            nomePk = "id_" + tabela;
        }

        Query q = DAO.getEM()
                .createNativeQuery(""
                        + " SELECT"
                        + " CASE WHEN COUNT(*) > 0 THEN true ELSE false END"
                        + " FROM " + tabela
                        + (value instanceof String ? " WHERE lutext(" + coluna + ") = lutext(?1)" : " WHERE " + coluna + " = ?1")
                        + (id == null ? "" : " AND " + nomePk + " <> ?2"));
        q.setParameter(1, value);

        if (id != null) {
            q.setParameter(2, id);
        }

        return (boolean) q.getSingleResult();
    }
}
