package com.example.springboot.dao;
import com.example.springboot.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from User where id=: id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void editUser(User user) {entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> listUsers() {

        return entityManager.createQuery("from User").getResultList();

    }

    @Override
    public User findUserByLogin(String login) {

        return (User) entityManager.createQuery(" from User where login=: login")
                .setParameter("login", login)
                .getSingleResult();
    }

}
