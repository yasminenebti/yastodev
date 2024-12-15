package com.yastodev.app.functionalInterfaces.consumer.emailService;

import com.yastodev.app.functionalInterfaces.utils.User;

import java.util.function.Consumer;

public class EmailService {
    public void sendEmailToUser(User user, Consumer<User> emailSender) {

        // The Consumer interface allows us to pass different email sending strategies
        emailSender.accept(user);
    }

    public void sendEmailVerification(User user) {
        if (!user.isEmailVerified()){
            System.out.println("Sending email verification to: " + user.getEmail());
            System.out.println("Email verification sent successfully");
        }
    }

    public void sendWelcomeEmail(User user) {
        System.out.println("Sending welcome email to: " + user.getEmail());
        System.out.println("Welcome email sent successfully");
    }
}
