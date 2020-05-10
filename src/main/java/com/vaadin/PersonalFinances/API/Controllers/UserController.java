package com.vaadin.PersonalFinances.API.Controllers;


import com.vaadin.PersonalFinances.API.Services.UserService;
import com.vaadin.PersonalFinances.API.Services.WalletService;
import com.vaadin.PersonalFinances.API.models.User;
import com.vaadin.PersonalFinances.API.models.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WalletService walletService;

    @GetMapping
    public Collection<User> getUser(){

        return userService.getUsers();
    }
    @GetMapping("/checkCookies")
    public boolean userCookies(@CookieValue(value = "personalFinances_userId", defaultValue = "null")String userId, @CookieValue(value = "personalFinances_walletId", defaultValue = "null")String walletId){
        System.out.println(userId+ walletId );
        return true;
    }
    //TODO: ciasteczka nie dzialaja pozdrawiwam
    @GetMapping("/setCookies/")
    public String getUserId(HttpServletResponse response){
        String userId = "5eb5c08bb114ed1567e58490";

        Optional<User> user = userService.getUser(userId);
        if(user.isPresent()) {
            System.out.println("user is present");
            Cookie cookieUserId = new Cookie("personalFinances_userId", "userId");
            response.addCookie(cookieUserId);
            Optional<Wallet> wallet = walletService.getWallet(user.get().getWalletId());
            if(wallet.isPresent()) {
                System.out.println("wallet is present");
                Cookie cookieWalletId = new Cookie("personalFinances_walletId", wallet.get().getId());

                response.addCookie(cookieWalletId);
            }
        }
        return "XD";
    }
    @GetMapping("/change-username")

    public String setCookie(HttpServletResponse response ) {

        // create a cookie
        System.out.println("controller");

        Cookie cookie = new Cookie("personalFinances_userId", "y");

        //add cookie to response

        response.addCookie(cookie);

        return "XD";

    }

    @PostMapping
    public User postUser(@RequestBody User user, HttpServletResponse response){
        //TODO: niedziala
        response.addCookie(new Cookie("personalFinances_userId", user.getId()));

        return userService.createUser(user);
    }
}
