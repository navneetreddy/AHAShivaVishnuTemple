package com.example.navneetreddy.ahashivavishnutemple;

import com.google.gson.annotations.SerializedName;


/**
 * Created by NavneetReddy on 8/9/15.
 */
public class Event {

    @SerializedName("Event Name")
    private String name;
    @SerializedName("Date")
    private String date;
    @SerializedName("Time")
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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