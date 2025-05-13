package com.airline.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class TicketRequest {
    @NotBlank(message = "Passenger name is required")
    private String passengerName;
    @NotNull(message = "Schedule ID is required")
    private Long scheduleId;
}