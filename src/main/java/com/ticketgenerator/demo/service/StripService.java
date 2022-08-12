package com.ticketgenerator.demo.service;

import com.ticketgenerator.demo.domain.Strip;
import com.ticketgenerator.demo.domain.Ticket;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StripService {

    Strip strip = new Strip();
    Map<Integer, List<Integer>> ranges = new HashMap<>();


    public void firstStep(){
        generateStrip();

        System.out.println();
        showStrip(strip);
        System.out.println();System.out.println();
    }

    public void generateStrip() {
        strip = new Strip();

        for(Ticket t :  strip.tickets){
            putZeroesOnRaw(t);
        }

        for(int i=0;i<9;i++){
            putZeroesOnLastRaw(strip,i);
        }

        for(int i=0;i<9;i++){
            int countZ = countZeroesOnColumn(strip,i);
            if(countZ != 8){
                generateStrip();
            }
        }

    }

    public void putZeroesOnLastRaw(Strip strip, int column){
        int remaining = 8 - countZeroesOnColumn(strip, column);
        if(remaining > 5){
            generateStrip();
        }else {
            List<Integer> ticketLastRaw = IntStream.range(0, 6).boxed().collect(Collectors.toList());
            Collections.shuffle(ticketLastRaw);
//            System.out.println(ticketLastRaw);
//            System.out.println(remaining);
            ticketLastRaw = ticketLastRaw.subList(0, remaining);

            for (int i : ticketLastRaw) {
                int idTicket = i;

                int count = 0;
                while ((countZeroesOnRaw(strip.tickets[idTicket], 2) >= 4 || strip.tickets[idTicket].numbers[2][column] != null) && count <= 25) {
                    Random rd = new Random();
                    ;
                    idTicket = rd.nextInt(6);
                    count++;

                }
                if (count == 25) {
                    generateStrip();
                }else {
                    strip.tickets[idTicket].numbers[2][column] = 0;
                }
            }
        }

    }

    public void putZeroesOnRaw(Ticket t){
        List<Integer> firstRawZeroes = IntStream.range(0,9).boxed().collect(Collectors.toList());
        Collections.shuffle(firstRawZeroes);
        firstRawZeroes = firstRawZeroes.subList(0,4);

        for(int nr : firstRawZeroes){
            t.numbers[0][nr] = 0;
        }

        List<Integer> finalFirstRawZeroes = firstRawZeroes;
        List<Integer> secondRawZeroes = IntStream.range(0, 9)
                .filter(z -> !finalFirstRawZeroes.contains(z)).boxed().collect(Collectors.toList());

        Collections.shuffle(secondRawZeroes);

        secondRawZeroes = secondRawZeroes.subList(0,4);


        for(int nr : secondRawZeroes){
            t.numbers[1][nr] = 0;
        }

    }

    public int countZeroesOnColumn(Strip strip, int idColumn){
        int zeroes = 0;
        for(Ticket t: strip.tickets){
            for(int i=0;i<3;i++) {
                if(t.numbers[i][idColumn]!=null && t.numbers[i][idColumn] == 0){
                    zeroes++;
                }
            }
        }
        return zeroes;
    }

    public int countZeroesOnRaw(Ticket ticket, int idRaw){
        int zeroes = 0;
        for(int i=0;i<9;i++) {
            if(ticket.numbers[idRaw][i]!=null && ticket.numbers[idRaw][i] == 0){
                zeroes++;
            }
        }
        return zeroes;
    }

    public void initRanges(){
        IntStream.range(1,10).forEach(rangeNumber -> {
            List<Integer> range = IntStream.range((rangeNumber-1)*10+1, (rangeNumber-1)*10 + 11).boxed().collect(Collectors.toList());
            Collections.shuffle(range);

            ranges.put(rangeNumber, range);
        });
    }


    public List<Integer> mapNrZeros(Strip strip){
        List<Integer> list = new ArrayList<>();
        list.add(0);list.add(0);list.add(0);list.add(0);list.add(0);list.add(0);
        int ii=0;
        for(Ticket t : strip.tickets){
            for(int j=0;j<9;j++){
                if(t.numbers[2][j] != null && t.numbers[2][j] == 0){
                    list.set(ii, list.get(ii)==null?1:list.get(ii)+1);
                }
            }
        }

        Integer min = 999;
        for(Integer value : list){
            if(min > value){
                min = value;
            }
        }

        List<Integer> pollList = new ArrayList<>();

        for(int i=0; i<6; i++){
            if(min == list.get(i)){
                pollList.add(i);
            }
        }

        return pollList;

    }

    public void showStrip(Strip strip){
        initRanges();
        for(Ticket t : strip.tickets){
            for(int i=0;i<3;i++){
                for(int j=0;j<9;j++){
                    if(t.numbers[i][j] != null && t.numbers[i][j] == 0){
                        System.out.print(t.numbers[i][j] + " ");
                    }else{
                        int nr = ranges.get(j+1).get(0);
                        ranges.get(j+1).remove(0);
                        System.out.print(nr + " ");

                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");

    }



}
