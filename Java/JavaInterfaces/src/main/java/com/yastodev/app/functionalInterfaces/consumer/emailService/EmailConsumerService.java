package com.yastodev.app.functionalInterfaces.consumer.emailService;

public class EmailConsumerService {

    public static void main(String[] args) {
        User john = new User("John", "john@example.com", false);
        User elsa = new User("Elsa", "elsa@example.com", true);

        EmailService emailService = new EmailService();

        System.out.println("----Sending email Verification to users----");
        emailService.sendEmailToUser(john, emailService::sendEmailVerification);
        emailService.sendEmailToUser(elsa, emailService::sendEmailVerification);

        System.out.println("----Sending welcome email to users----");
        emailService.sendEmailToUser(john, emailService::sendWelcomeEmail);
        emailService.sendEmailToUser(elsa, emailService::sendWelcomeEmail);
    }
}
