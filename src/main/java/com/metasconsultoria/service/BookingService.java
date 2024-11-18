package com.metasconsultoria.service;

import com.metasconsultoria.database.ConnData;
import com.metasconsultoria.entities.Booking;
import com.metasconsultoria.repository.BookingRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookingService {
    private static final Connection conn = ConnData.connection;

    public static void insertBooking(Booking booking) throws SQLException {
        BookingRepository.insertInto(conn, booking);
    }

    public static void updateBooking(Booking booking) throws SQLException {
        BookingRepository.updateData(conn, booking);
    }

    public static void deleteBooking(int idBooking) throws SQLException {
        BookingRepository.deleteById(conn, idBooking);
    }

    public static List<Booking> getAllBookings() throws SQLException {
        return BookingRepository.selectAll(conn);
    }

    public static Booking getBookingById(int idBooking) throws SQLException {
        return BookingRepository.selectById(conn, idBooking);
    }
}
