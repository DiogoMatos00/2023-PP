package controlers;

import models.City;
import models.Passenger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class tools {
    static Random rand = new Random(); //Criação de um objeto randomizer

    public static City getcity(List<City> list) { //Vai buscar a lista das cidades
        return list.get(rand.nextInt(list.size())); //Dá return a uma cidade random
    }

    public static City getcityDestination(List<City> list, City city) {
        List<City> arraylist = new ArrayList<>(list); //Criação de uma ArrayList
        arraylist.remove(city);
                                //Quando o bus chega à cidade destino esta é removida da lista
        return arraylist.get(rand.nextInt(arraylist.size())); //Dá return a uma cidade da ArrayList anterior
    }

    public static boolean getBoolean() {        //true =
        return rand.nextBoolean();
    }

    //Metodo para perceber se o passageiro ganha algum beneficio em entrar neste autocarro,
    //tendo como chave a cidade em que está a cidade para qual quer chegar e a direção do autocarro.
    //nextPrevious: true -> next /-/ false -> Previous
    public static boolean choiceBus(City city, Passenger passenger, boolean nextPrevious) {
        while (true) { //enquanto true
            if (nextPrevious) {
                if (passenger.getDestination() == city.getNext()) { //se o destino do passageiro for a proxima cidade
                    return true; //return true
                } else if (city.getNext() == null) { //se for a última cidade (visto que não existe proxima)
                    return false; //return false
                } else {
                    city = city.getNext();
                    continue;
                }
            } else {
                if (passenger.getDestination() == city.getPrevious()) { //se o destino do passageiro for a cidade anterior
                    return true; //return true
                } else if (city.getPrevious() == null) { //se a cidade for a primeira (não existe anterior)
                    return false; //return false
                } else {
                    city = city.getPrevious();
                    continue;
                }
            }
        }
    }
}
