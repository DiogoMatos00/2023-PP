package thread;
import models.Bus;
import models.City;

import java.util.*;

public class offloadSetup {
    static List<Bus> buses;         //Lista de autocarros
    static List<City> cities;       //Lista de cidades

    public offloadSetup(List<Bus> buses, List<City> cities) {       //Construtor
        offloadSetup.buses = buses;
        offloadSetup.cities = cities;
    }

    public static List<City> getCities() {
        return cities;
    }   //Vai buscar todas as cidades

    public static void addbuses(Bus bus){
        offloadSetup.buses.add(bus);
    }   //Adiciona um autocarro a lista de autocarros

    public static void rmbuses(Bus bus){
        offloadSetup.buses.remove(bus);
    }   //Remove um autocarro da lista de autocarros

    public static List<Bus> getbuses(){
        return offloadSetup.buses;
    }   //Vai buscar todos os autocarros
}