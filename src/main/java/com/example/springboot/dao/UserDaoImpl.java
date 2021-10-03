package com.example.springboot.dao;
import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    @Override
    public Role getRole(String name) {
        TypedQuery<Role> role = entityManager.createQuery("from Role r where r.role = :name", Role.class)
                .setParameter("name", name);

        return role.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> roles = entityManager.createQuery(" from Role", Role.class);
        return roles.getResultList();
    }

    @Override
    public User getByEmail(String email) {
        Query query = entityManager.createQuery("from User u where u.email=:email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public User getByName(String firstName) {
        Query query = entityManager.createQuery("from User u where u.name=:first_name");
        query.setParameter("first_name", firstName);
        return (User) query.getSingleResult();
    }
}