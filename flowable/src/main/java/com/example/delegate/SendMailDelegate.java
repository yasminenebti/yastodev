package com.example.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String mailType = (String) execution.getVariable("mailType");
        String employeeName = (String) execution.getVariable("employeeName");
        int numberOfDays = (Integer) execution.getVariable("numberOfDays");

        // In a real application, implement email sending logic here
        System.out.println("Sending " + mailType + " email to " + employeeName +
                " for " + numberOfDays + " days of vacation");
    }
}
