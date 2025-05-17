package com.airline.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TicketResponse {
    private Long id;
    private String passengerName;
    private Long scheduleId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Double price;
    private Long flightId;
    private String flightNumber;
    private String origin;
    private String destination;
}