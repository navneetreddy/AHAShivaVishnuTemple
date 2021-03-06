package com.navneetreddy.ahashivavishnutemple;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


/**
 * Contains the information about a single event.
 *
 * @author Navneet Reddy
 */
public class Event implements Comparable<Event> {

    @SerializedName("Event Name")
    private String name;
    @SerializedName("Event Location")
    private String location;
    @SerializedName("Event Date")
    private String date;
    @SerializedName("All Day")
    private boolean allDay;
    @SerializedName("Start Time")
    private String startTime;
    @SerializedName("End Time")
    private String endTime;

    private Date startDate;
    private Date endDate;

    @SerializedName("Contact Name")
    private String contactName;
    @SerializedName("Contact Phone")
    private String contactPhone;
    @SerializedName("Contact Email")
    private String contactEmail;

    @SerializedName("PDF")
    private String pdfLink;

    /**
     * Constructor for an event.
     */
    public Event() {}

    /**
     * Gets the event name.
     *
     * @return The event name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the event name.
     *
     * @param name The event name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the event.
     *
     * @return The location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the event.
     *
     * @param location The location of the event.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the event date.
     *
     * @return The event date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the event date.
     *
     * @param date The event date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns if the event is an all day event.
     *
     * @return True if the event is an all day event, otherwise false.
     */
    public boolean isAllDay() {
        return allDay;
    }

    /**
     * Sets if the event is an all day event.
     *
     * @param allDay True if the event is an all day event, otherwise false.
     */
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * Gets the start time of the event as a string.
     *
     * @return The start time of the event.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime The start time of the event.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of the event as a string.
     *
     * @return The end time of the event.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the event.
     *
     * @param endTime The end time of the event.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the start date and time as a date object.
     *
     * @return The start date and time as a date object.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date and time as a date object.
     *
     * @param startDate The start date and time as a date object.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date and time as a date object.
     *
     * @return The end date and time as a date object.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date and time as a date object.
     *
     * @param endDate The end date and time as a date object.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the name of the contact person for the event.
     *
     * @return Name of the contact person.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the name of the contact person for the event.
     *
     * @param contactName Name of the contact person.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Gets the phone number of the contact person for the event.
     *
     * @return Phone number of the contact person.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Sets the phone number of the contact person for the event.
     *
     * @param contactPhone Phone number of the contact person for the event.
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Gets the email of the contact person for the event.
     *
     * @return Email of the contact person for the event.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets the email of the contact person for the event.
     *
     * @param contactEmail Email of the contact person for the event.
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Gets the link to the PDF of the information about the event.
     *
     * @return URL link to the PDF.
     */
    public String getPdfLink() {
        return pdfLink;
    }

    /**
     * Sets the link to the PDF of the information about the event.
     *
     * @param pdfLink URL link to the PDF.
     */
    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    @Override
    public int compareTo(@NonNull Event anotherEvent) {
        int returnCode = 0;

        try {
            returnCode = getStartDate().compareTo(anotherEvent.getStartDate());
        } catch (NullPointerException e) {
            Log.e("EVENT ERROR", "Null date object was passed to: " + getName());
        }

        return returnCode;
    }
}