package model.dao;

import java.io.Serializable;
import java.util.List;
import Hibernate.HibernateUtil;
import model.dto.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModelUsuarios implements Serializable {

    public int Create(Usuario dto) {
        int result = 1;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.save(dto);
            t.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (t.isActive() && t != null) {
                t.rollback();
            }
            result = 0;
        }
        return result;
    }

    public Usuario Read(Usuario dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List resultados = null;
        try {
            t.begin();
            Query q = s.createQuery("FROM Usuario WHERE user = :user");
            q.setParameter(":user", dto.getUser());
            resultados = q.list();
            t.commit();
            if (resultados.size() > 0) {
                dto = (Usuario) resultados.get(0);
            } else {
                dto = null;
            }
        } catch (HibernateException he) {
            if (t.isActive() && t != null) {
                t.rollback();
            }
        }
        return dto;
    }

    public List ReadAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List resultados = null;
        try {
            t.begin();
            Query q = s.createQuery("FROM Usuario");
            resultados = q.list();
            t.commit();
        } catch (HibernateException he) {
            if (t.isActive() && t != null) {
                t.rollback();
            }
        }
        return resultados;
    }

    public int Update(Usuario dto) {
        int result = 1;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.update(dto);
            t.commit();
        } catch (HibernateException he) {
            result = 0;
            if (t.isActive() && t != null) {
                t.rollback();
            }
        }
        return result;
    }

    public int Delete(Usuario dto) {
        int result = 1;
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(dto);
            t.commit();
        } catch (HibernateException he) {
            result = 0;
            if (t.isActive() && t != null) {
                t.rollback();
            }
        }
        return result;
    }
}
