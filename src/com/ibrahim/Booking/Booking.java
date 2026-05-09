package com.ibrahim.Booking;


import java.util.Date;
import java.util.UUID;

public class Booking {

    private UUID bookingId;
    private UUID carId;
    private UUID userId;
    private Date bookinDate;
    private Status status;

    public Booking(UUID bookingId, UUID carId, UUID userId) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.userId = userId;
        this.status = Status.ACTIVE;
        this.bookinDate = new Date();

    }


    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Date getBookinDate() {
        return bookinDate;
    }

    public void setBookinDate(Date bookinDate) {
        this.bookinDate = bookinDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", carId=" + carId +
                ", userId=" + userId +
                ", bookinDate=" + bookinDate +
                ", status=" + status +
                '}';
    }
}
