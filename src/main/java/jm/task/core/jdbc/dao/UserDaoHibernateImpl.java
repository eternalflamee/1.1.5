package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory SESSION_FACTORY = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("create table IF NOT EXISTS user\n" +
                    "(\n" +
                    "    ID        BIGINT auto_increment,\n" +
                    "    NAME      VARCHAR(45) not null,\n" +
                    "    LAST_NAME VARCHAR(45) null,\n" +
                    "    AGE       TINYINT(3)  not null,\n" +
                    "    constraint users_pk\n" +
                    "        primary key (ID)\n" +
                    ")").executeUpdate();
            transaction.commit();
            System.out.println("Table has been created");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
            System.out.println("Table has been dropped");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User has been deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            transaction = session.beginTransaction();
            usersList = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
            System.out.println("User has been deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = SESSION_FACTORY.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            transaction.commit();
            System.out.println("Table has been dropped");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
