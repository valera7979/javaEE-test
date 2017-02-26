package crud.dao;

import crud.model.User;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User was successfully ADDED. User details: " + user);
    }

    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully LOADED. User details: " + user);
        return user;
    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User was successfully UPDATED. User details: " + user);
    }

    public void deleteUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
       User user = (User) session.load(User.class, new Integer(id));

       if (user!=null) {
           session.delete(user);
       }
        logger.info("User was successfully REMOVED from DB. User details: " + user);
    }


    public List<User> getAllUsers(int page, int usersOnPage, String searchString) {
        Session session = this.sessionFactory.getCurrentSession();

       Query query = session.createQuery("from User");
       query.setFirstResult((page-1)*usersOnPage);
       query.setMaxResults(usersOnPage);
        List<User> users = query.list();

        for (User user: users) {
            logger.info("User %s added to List<User>", user);
        }
        return users;
    }


    public List<User> getUsersBySearch(int page, int usersOnPage, String searchString) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from User where name LIKE :searchText").
                setParameter("searchText","%"  + searchString + "%");

        query.setFirstResult((page-1)*usersOnPage);
        query.setMaxResults(usersOnPage);
        List<User> users = query.list();

        for (User user: users) {
            logger.info("User %s added to List<User>", user);
        }
        return users;
    }

    @Override
    public int getNumberOfPages(int usersOnPage, String searchString) {
        Session session = sessionFactory.getCurrentSession();
        Integer numberOfUsers =  ((Long) session.createQuery("select count(*) from User where name LIKE :searchString").
                setParameter("searchString" , "%" + searchString + "%").uniqueResult()).intValue();
        if (numberOfUsers - usersOnPage*(numberOfUsers/usersOnPage) > 0)
        return (int) numberOfUsers/usersOnPage + 1;
        else return numberOfUsers/usersOnPage;
    }
}
