package com.ibrahim.Booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Booking {

    private final UUID bookingId;
    private final UUID carId;
    private final UUID userId;
    private Status status;
    private final LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public Booking(UUID bookingId, UUID carId, UUID userId, LocalDateTime startDate, LocalDateTime endDate, BigDecimal price) {
        this.bookingId = bookingId;
        this.carId = carId;
        this.userId = userId;
        this.status = Status.ACTIVE;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getCarId() {
        return carId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) && Objects.equals(carId, booking.carId) && Objects.equals(userId, booking.userId) && status == booking.status && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && Objects.equals(price, booking.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, carId, userId, status, startDate, endDate, price);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", carId=" + carId +
                ", userId=" + userId +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}
