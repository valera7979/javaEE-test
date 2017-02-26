package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private static final int USERS_ON_PAGE = 5;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getAllUsers (Model model) {
        model.addAttribute("user", new User());
            String mockString = "";
            List<User> users = this.userService.getAllUsers(1, USERS_ON_PAGE, mockString);
            model.addAttribute("getAllUsers", users);
            int numberOfPages = this.userService.getNumberOfPages(USERS_ON_PAGE, mockString);

            model.addAttribute("numberOfPages", numberOfPages);
            model.addAttribute("currentPage", 1);

        return "users";
    }

    @RequestMapping(value = "users/{i}", method = RequestMethod.GET)
    public String getUsersForOnePage (@PathVariable("i") int i, Model model) {
        model.addAttribute("user", new User());
        String mockString = "";
        List<User> users = this.userService.getAllUsers(i, USERS_ON_PAGE, mockString);
        model.addAttribute("getAllUsers", users);
        int numberOfPages = this.userService.getNumberOfPages(USERS_ON_PAGE, mockString);

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", i);

        return "users";
    }



    @RequestMapping(value = "/users/add/{currentPage}", method = RequestMethod.POST)
    public String addUser(@PathVariable("currentPage") int currentPage,@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/users/" + currentPage;
    }

    @RequestMapping(value = "/users/add/{searchString}/{currentPage}", method = RequestMethod.POST)
    public String addUserFromSearch(@PathVariable("searchString") String searchString,
                                    @PathVariable("currentPage") int currentPage,
                                    @ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "redirect:/doSearch/{searchString}/{currentPage}";
    }

    @RequestMapping("/remove/{id}/{currentPage}")
    public String deleteUser(@PathVariable("id") int id, @PathVariable int currentPage) {
        this.userService.deleteUser(id);
        return "redirect:/users/" + currentPage;
    }

    @RequestMapping("/remove/{id}/{searchString}/{currentPage}")
    public String deleteUserFromSearch(@PathVariable("id") int id,
                                       @PathVariable("id") String searchString,
                                       @PathVariable int currentPage) {
        this.userService.deleteUser(id);
        return "redirect:/doSearch/{searchString}/{currentPage}";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        String mockString = "";
        model.addAttribute("getAllUsers", this.userService.getAllUsers(1, USERS_ON_PAGE, mockString));

        return "users";
    }

    @RequestMapping(value = "/doSearch", method = RequestMethod.POST)
    public String getUsersBySearch(
            @RequestParam("searchString")
                    String searchString, Model model
    ) throws Exception {
        model.addAttribute("user", new User());

        List<User> users = this.userService.getUsersBySearch(1, USERS_ON_PAGE, searchString);
        model.addAttribute("getAllUsers", users);
        int numberOfPages = this.userService.getNumberOfPages(USERS_ON_PAGE, searchString);

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("searchString", searchString);
        model.addAttribute("currentPage", 1);
        return "users";
    }

    @RequestMapping(value = "/doSearch/{searchString}/{i}", method = RequestMethod.GET)
    public String getUsersBySearchForOnePage(
            @PathVariable("searchString")
                    String searchString, @PathVariable("i") int i, Model model) throws Exception {
        model.addAttribute("user", new User());

        List<User> users = this.userService.getUsersBySearch(i, USERS_ON_PAGE, searchString);
        model.addAttribute("getAllUsers", users);
        int numberOfPages = this.userService.getNumberOfPages(USERS_ON_PAGE, searchString);

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("searchString", searchString);
        model.addAttribute("currentPage", i);
        return "users";
    }


}
