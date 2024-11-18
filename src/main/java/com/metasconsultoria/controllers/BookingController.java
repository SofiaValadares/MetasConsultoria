package com.metasconsultoria.controller;

import com.metasconsultoria.entities.Booking;
import com.metasconsultoria.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAllBookings() throws SQLException {
        List<Booking> bookings = BookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) throws SQLException {
        Booking booking = BookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) throws SQLException {
        BookingService.insertBooking(booking);
        return ResponseEntity.ok("Reserva criada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable int id, @RequestBody Booking booking) throws SQLException {
        booking.setIdBooking(id);
        BookingService.updateBooking(booking);
        return ResponseEntity.ok("Reserva atualizada com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) throws SQLException {
        BookingService.deleteBooking(id);
        return ResponseEntity.ok("Reserva deletada com sucesso.");
    }
}
