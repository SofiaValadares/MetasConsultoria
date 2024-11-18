package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private BookingRepository() {}

    public static void insertInto(Connection conn, Booking booking) throws SQLException {
        String sql = "INSERT INTO Reservation (start_time, end_time, day, reserved_by, cod_project, fk_collaborator_cod_user, fk_client_cod_user) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTime(1, booking.getStartTime());
            ps.setTime(2, booking.getEndTime());
            ps.setDate(3, new java.sql.Date(booking.getDay().getTime()));
            ps.setInt(4, booking.getReservedBy());
            ps.setInt(5, booking.getCodProject());
            ps.setInt(6, booking.getCollaborator().getIdUser());
            ps.setInt(7, booking.getWhoBooked().getIdUser());

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, Booking booking) throws SQLException {
        String sql = "UPDATE Reservation SET start_time = ?, end_time = ?, day = ?, reserved_by = ?, cod_project = ?, fk_collaborator_cod_user = ?, fk_client_cod_user = ? WHERE reservation_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTime(1, booking.getStartTime());
            ps.setTime(2, booking.getEndTime());
            ps.setDate(3, new java.sql.Date(booking.getDay().getTime()));
            ps.setInt(4, booking.getReservedBy());
            ps.setInt(5, booking.getCodProject());
            ps.setInt(6, booking.getCollaborator().getIdUser());
            ps.setInt(7, booking.getWhoBooked().getIdUser());
            ps.setInt(8, booking.getIdBooking());

            ps.executeUpdate();
        }
    }

    public static void deleteById(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE reservation_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static List<Booking> selectAll(Connection conn) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setIdBooking(rs.getInt("reservation_id"));
                booking.setStartTime(rs.getTime("start_time"));
                booking.setEndTime(rs.getTime("end_time"));
                booking.setDay(rs.getDate("day"));
                booking.setReservedBy(rs.getInt("reserved_by"));
                booking.setCodProject(rs.getInt("cod_project"));
                // Aqui você precisará buscar os objetos Collaborator e User correspondentes
                // e setar no objeto booking

                bookings.add(booking);
            }
        }

        return bookings;
    }

    public static Booking selectById(Connection conn, int id) throws SQLException {
        Booking booking = null;
        String sql = "SELECT * FROM Reservation WHERE reservation_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    booking = new Booking();
                    booking.setIdBooking(rs.getInt("reservation_id"));
                    booking.setStartTime(rs.getTime("start_time"));
                    booking.setEndTime(rs.getTime("end_time"));
                    booking.setDay(rs.getDate("day"));
                    booking.setReservedBy(rs.getInt("reserved_by"));
                    booking.setCodProject(rs.getInt("cod_project"));
                    // Aqui você precisará buscar os objetos Collaborator e User correspondentes
                    // e setar no objeto booking
                }
            }
        }

        return booking;
    }
}
