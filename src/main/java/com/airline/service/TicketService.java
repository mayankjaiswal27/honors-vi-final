package com.airline.service;

import com.airline.dto.TicketRequest;
import com.airline.dto.TicketResponse;
import com.airline.entity.Schedule;
import com.airline.entity.Ticket;
import com.airline.repository.ScheduleRepository;
import com.airline.repository.TicketRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ScheduleRepository scheduleRepository;

    public TicketService(TicketRepository ticketRepository, ScheduleRepository scheduleRepository) {
        this.ticketRepository = ticketRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public TicketResponse createTicket(TicketRequest ticketRequest) {
        Schedule schedule = scheduleRepository.findById(ticketRequest.getScheduleId())
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + ticketRequest.getScheduleId()));

        Ticket ticket = new Ticket();
        ticket.setPassengerName(ticketRequest.getPassengerName());
        ticket.setSchedule(schedule);

        Ticket savedTicket = ticketRepository.save(ticket);
        return mapToTicketResponse(savedTicket);
    }

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
        return mapToTicketResponse(ticket);
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new EntityNotFoundException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
    }

    private TicketResponse mapToTicketResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setPassengerName(ticket.getPassengerName());

        Schedule schedule = ticket.getSchedule();
        if (schedule != null) {
            response.setScheduleId(schedule.getId());
            response.setDepartureTime(schedule.getDepartureTime());
            response.setArrivalTime(schedule.getArrivalTime());
            response.setPrice(schedule.getPrice());

            if (schedule.getFlight() != null) {
                response.setFlightId(schedule.getFlight().getId());
                response.setFlightNumber(schedule.getFlight().getFlightNumber());
                response.setOrigin(schedule.getFlight().getOrigin());
                response.setDestination(schedule.getFlight().getDestination());
            }
        }

        return response;
    }
}