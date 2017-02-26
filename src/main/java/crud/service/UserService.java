package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);
    public User getUserById(int id);
    public void updateUser(User user);
    public void deleteUser(int id);
    public List<User> getAllUsers(int page, int usersOnPage, String searchString);

    public List<User> getUsersBySearch(int page, int usersOnPage, String searchString);
    public int getNumberOfPages(int usersOnPage, String searchString);
}
