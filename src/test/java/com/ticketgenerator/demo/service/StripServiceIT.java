package com.ticketgenerator.demo.service;

import com.ticketgenerator.demo.domain.Strip;
import com.ticketgenerator.demo.domain.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StripServiceIT {

    @Test
    void generateStrip() {
        StripService stripService = new StripService();
        Strip s = stripService.firstStep();

        for(int i=0; i<9; i++){
            assertEquals(8,stripService.countZeroesOnColumn(s,i));
        }

        for(Ticket t: s.tickets) {
            for (int i = 0; i < 3; i++) {
                assertEquals(4, stripService.countZeroesOnRaw(t, i));
            }
        }

    }
}