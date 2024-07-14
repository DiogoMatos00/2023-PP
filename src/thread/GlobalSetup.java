package thread;

import models.Bus;

import java.util.List;

public class GlobalSetup {

    static List<Bus> buses;     //Lista de autocarros
    static String teste;

    public GlobalSetup(List<Bus> buses) {
        this.buses = buses;
    }

    public static void addBuses(Bus bus) {       //Adiciona um autcarro a lista de autocarros
        buses.add(bus);
    }

    public static void rmBuses(Bus bus) {        //Remove um autocarro da lista de autocarros
        buses.remove(bus);
    }

    public static List<Bus> getBuses() {        //Vai buscar a lista de autocarros
        return buses;
    }

}