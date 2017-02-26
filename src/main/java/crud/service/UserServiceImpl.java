package crud.service;

import crud.dao.UserDAO;
import crud.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private TransactionTemplate transactionTemplate;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);

    }

    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(int id) {
        this.userDAO.deleteUser(id);
    }

    @Transactional
    public List<User> getAllUsers(int page, int usersOnPage, String searchString) {

        return this.userDAO.getAllUsers(page, usersOnPage, searchString);

    }

    @Transactional
    public List<User> getUsersBySearch(int page, int usersOnPage, String searchString) {
        return this.userDAO.getUsersBySearch(page, usersOnPage, searchString);
    }

    @Transactional
    public int getNumberOfPages(int usersOnPage, String searchString) {
        return this.userDAO.getNumberOfPages(usersOnPage, searchString);
    }
}
