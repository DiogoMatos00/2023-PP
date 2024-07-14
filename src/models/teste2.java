package models;

import java.util.List;

//Classe de testes

public class teste2 {
    static List<Bus> buses ;

    public teste2(List<Bus> buses) {
        this.buses = buses;
    }

     public static List<Bus> getBuses() {
        return buses;
    }

    public static void addBuses(Bus bus){
        buses.add(bus);
    }
}
