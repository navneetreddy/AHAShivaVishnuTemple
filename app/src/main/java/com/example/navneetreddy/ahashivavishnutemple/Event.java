package com.example.navneetreddy.ahashivavishnutemple;

import com.google.gson.annotations.SerializedName;


/**
 * Created by NavneetReddy on 8/9/15.
 */
public class Event {

    @SerializedName("Event Name")
    private String name;
    @SerializedName("Event Date")
    private String date;
    @SerializedName("All Day")
    private boolean allDay;
    @SerializedName("Start Time")
    private String startTime;
    @SerializedName("End Time")
    private String endTime;

    @SerializedName("Contact Name")
    private String contactName;
    @SerializedName("Contact Phone")
    private String contactPhone;
    @SerializedName("Contact Email")
    private String contactEmail;

    @SerializedName("PDF")
    private String pdfLink;


    public Event() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }
}