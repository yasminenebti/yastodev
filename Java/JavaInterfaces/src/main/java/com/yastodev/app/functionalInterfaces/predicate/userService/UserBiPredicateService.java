package com.yastodev.app.functionalInterfaces.predicate.userService;

import com.yastodev.app.functionalInterfaces.utils.User;

import java.util.function.BiPredicate;

public class UserBiPredicateService {

    public static void main(String[] args) {
        User user = new User("Alice", "alice@gmail.com", true);

        BiPredicate<User, String> isUserEmailVerified = (u, email) -> u.getEmail().equals(email) && u.isEmailVerified();

        if (isUserEmailVerified.test(user, "alice.gmail.com")) {
            System.out.println("User email is verified");
        } else {
            System.out.println("Error");
        }
    }
}
