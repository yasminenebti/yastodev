package com.yastodev.app;

import java.io.Serializable;
import java.util.Date;

public class VacationRequest implements Serializable {
    private String employeeName;
    private int numberOfDays;
    private Date startDate;
    private String vacationMotivation;

    // Getters and setters
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    public int getNumberOfDays() { return numberOfDays; }
    public void setNumberOfDays(int numberOfDays) { this.numberOfDays = numberOfDays; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public String getVacationMotivation() { return vacationMotivation; }
    public void setVacationMotivation(String vacationMotivation) { this.vacationMotivation = vacationMotivation; }
}
