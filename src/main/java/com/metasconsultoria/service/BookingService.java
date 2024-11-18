package com.metasconsultoria.service;

import com.metasconsultoria.entities.Booking;
import com.metasconsultoria.repository.BookingRepository;

import java.sql.SQLException;
import java.util.List;

public class BookingService {

    public static void insertBooking(Booking booking) throws SQLException {
        BookingRepository.insertInto(booking);
    }

    public static void updateBooking(Booking booking) throws SQLException {
        BookingRepository.updateData(booking);
    }

    public static void deleteBooking(int idBooking) throws SQLException {
        BookingRepository.deleteById(idBooking);
    }

    public static List<Booking> getAllBookings() throws SQLException {
        return BookingRepository.selectAll();
    }

    public static Booking getBookingById(int idBooking) throws SQLException {
        return BookingRepository.selectById(idBooking);
    }
}
