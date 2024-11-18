package com.metasconsultoria.repository;

import com.metasconsultoria.entities.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private BookingRepository() {}

    public static void insertInto(Connection conn, Booking booking) throws SQLException {
        String sql = "INSERT INTO Reservation (start_time, end_time, day, fk_collaborator_cod_user, fk_client_cod_user) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTime(1, booking.getTimeStart());
            ps.setTime(2, booking.getTimeEnd());
            ps.setDate(3, new java.sql.Date(booking.getDate().getTime()));
            ps.setInt(6, booking.getIdCollaborator());
            ps.setInt(7, booking.getIdClient());

            ps.executeUpdate();
        }
    }

    public static void updateData(Connection conn, Booking booking) throws SQLException {
        String sql = "UPDATE Reservation SET start_time = ?, end_time = ?, day = ?, fk_collaborator_cod_user = ?, fk_client_cod_user = ? WHERE reservation_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTime(1, booking.getTimeStart());
            ps.setTime(2, booking.getTimeEnd());
            ps.setDate(3, new java.sql.Date(booking.getDate().getTime()));
            ps.setInt(6, booking.getIdCollaborator());
            ps.setInt(7, booking.getIdClient());
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
                Booking booking = Booking.builder()
                        .idBooking(rs.getInt("reservation_id"))
                        .timeStart(rs.getTime("start_time"))
                        .timeEnd(rs.getTime("start_time"))
                        .date(rs.getDate("day"))
                        .idCollaborator(rs.getInt("fk_collaborator_cod_user"))
                        .idClient(rs.getInt("fk_client_cod_user"))
                        .build();


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
                    booking = Booking.builder()
                            .idBooking(rs.getInt("reservation_id"))
                            .timeStart(rs.getTime("start_time"))
                            .timeEnd(rs.getTime("start_time"))
                            .date(rs.getDate("day"))
                            .idCollaborator(rs.getInt("fk_collaborator_cod_user"))
                            .idClient(rs.getInt("fk_client_cod_user"))
                            .build();
                }
            }
        }

        return booking;
    }
}
