package models;

import java.util.ArrayList;
import java.util.List;

public class City {

    String name; //Nome da cidade

    List<Bus> parkedBus = new ArrayList<>(); //Lista dos autocarros estacionados
    List<Passenger> listOfPassengers = new ArrayList<>(); //Lista com todos os objetos dos passageiros
    int numPassStartingPoint; // Número de passageiros que começam nesta cidade
    int numPassDestiny; // Número de passageiros que acabam nesta cidade
    int numPassArrived; // Número de passageiros que chegaram.
    //Nota: Ordem das cidades: Cascais -> Lisboa -> Coimbra -> Porto -> Braga
    City next;  //Próxima cidade
    City previous; //Cidade anterior
    int numKmNext; //Número de km para a proxima cidade
    int numKMPrevious; //Número de km para a cidade anterior

    public City(String name) {      //Construtor da classe cidade
        this.name = name;
    }

    public String getName() {
        return name;
    }   //Vai buscar o nome da cidade

    public List<Bus> getParkedBus() {
        return parkedBus;
    }   //Lista de autocarros estacionados na cidade

    public List<Passenger> getListOfPassengers() {
        return listOfPassengers;    //Lista com todos os objetos dos passageiros
    }

    public int getNumPassStartingPoint() {
        return numPassStartingPoint; // Return número de passageiros que começam nesta cidade
    }

    public int getNumPassDestiny() {
        return numPassDestiny;  //Return número de passageiros que acabam nesta cidade
    }

    public int getNumPassArrived() {    //Vai buscar o número de passageiros que chegou a cidade
        return numPassArrived;
    }

    public City getNext() {
        return next;
    }       //Vai buscar a próxima cidade

    public City getPrevious() {
        return previous;
    }   //Vai buscar a cidade anterior

    public int getNumKmNext() {
        return numKmNext;
    }   //Vai buscar o número de km da próxima cidade

    public int getNumKMPrevious() {
        return numKMPrevious;
    }   //Vai buscar o número de km da cidade anterior

    public void setName(String name) {
        this.name = name;
    }   //Altera o nome da cidade

    public void setParkedBus(List<Bus> parkedBus) {
        this.parkedBus = parkedBus;
    }
    //Define quais são os autocarros estacionados

    public void setListOfPassengers(List<Passenger> listOfPassengers) {
        this.listOfPassengers = listOfPassengers;
    }
    //Define a lista de passageiros

    public void setNumPassStartingPoint(int numPassStartingPoint) {
        this.numPassStartingPoint = numPassStartingPoint;
    }
        //Define o ponto de começo dos passageiros

    public void setNumPassDestiny(int numPassDestiny) {
        this.numPassDestiny = numPassDestiny;
    }   //Define o destino dos passageiros

    public void setNumPassArrived(int numPassArrived) {
        this.numPassArrived = numPassArrived;
    }   //Define o número de passageiros que chegaram a cidade

    public void setNext(City next) {
        this.next = next;
    }   //Define a próxima cidade

    public void setPrevious(City previous) {
        this.previous = previous;
    }   //Define a cidade anterior

    public void setNumKmNext(int numKmNext) {
        this.numKmNext = numKmNext;
    }  //Define o número de km da próxima cidade

    public void setNumKMPrevious(int numKMPrevious) {
        this.numKMPrevious = numKMPrevious;
    } //Define o número de km da cidade anterior

    public void addPassenger(Passenger passenger){
        this.listOfPassengers.add(passenger);
    } //Adiciona um passageiro a cidade

    public void rmPassenger(Passenger passenger){
        this.listOfPassengers.remove(passenger);
    }   //remove um passageiro da cidade

    //0-> Não tem null 1-> Tem null no previous 2-> Tem null no next
    public int doesItHaveNull(){
        if(next == null){
            return 2;
        } else if(previous == null){
            return 1;
        } else{
            return 0;
        }
    }

}
