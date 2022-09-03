package com.ticketgenerator.demo.service;

import com.ticketgenerator.demo.domain.Strip;
import com.ticketgenerator.demo.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StripServiceTest {

    @InjectMocks
    StripService stripService;

    @Test
    void putZeroesOnRaw() {

        Ticket t = new Ticket();

        stripService.putZeroesOnRaw(t);

        assertEquals(4, stripService.countZeroesOnRaw(t,0));
        assertEquals(4, stripService.countZeroesOnRaw(t,1));
    }


    @Test
    void countZeroesOnRaw() {

        Ticket t = new Ticket();
        t.numbers = new Integer[][]{{0, 1, 0, 0, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 1, 1, 1}};

        assertEquals(4, stripService.countZeroesOnRaw(t,0));

    }

    @Test
    void countZeroesOnColumn() {

        Strip strip = new Strip();
        Ticket t = new Ticket();
        t.numbers = new Integer[][]{{0, 1, 0, 0, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 1, 1, 1}, {0, 1, 0, 0, 1, 0, 1, 1, 1}};

        strip.tickets = new Ticket[]{t,t,t,t,t,t};

        assertEquals(18, stripService.countZeroesOnColumn(strip,0));

    }
}