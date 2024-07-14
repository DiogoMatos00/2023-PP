package models;

public class Passenger {

    int id;                 //ID do passageiro
    City destination;       //Destino do passageiro
    City StartingPoint;     //Ponto de inicio do passageiro
    String BusId;           //ID do autocarro cujo passagieor se encontra

    public Passenger(int id, City startingPoint, City destination) {    //Construtor do passenger
        this.id = id;
        this.destination = destination;
        this.StartingPoint = startingPoint;
    }

    public int getId() {
        return this.id; //return Id do passageiro
    }

    public City getDestination() {
        return this.destination; //return destination do passageiro
    }

    public City getStartingPoint() {
        return this.StartingPoint; //return starting point do passageiro
    }

    public String getBusId() {
        return this.BusId; //Return ao id do autocarro onde o passageiro está
    }

    public void setBusId(String busId) {
        this.BusId = busId; //set do busId
    }
}
