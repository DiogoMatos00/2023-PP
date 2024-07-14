package models;

import java.util.ArrayList;
import java.util.List;

public class Bus {

    String id;      //id do autocarro
    String type;    //tipo do autocarro
    int numMaxPassanger; //Número max de passageiros
    float numKmToFinish;   //Numero de km que faltam para chegar ao destino
    float originalKmToFinish;   //Numero de km originais que faltavam ao autocarro no inicio da viagem
    City destination;    //Cidade de destino
    List<Passenger> Passanger = new ArrayList<>(); //Lista dos passageiros que estão dentro do autocarro
    int tempoAvaria = 10;   //tempo de espera quando o autocarro sofre uma avaria
    List<City> cities;      //Lista de cidades que o autocarro passa
    int status; //0 -> Na cidade; 1 -> Em movimento; 2 -> Avariado.
    int tipoDeAvaria;       //Tipo de avaria que o autocarro sofre
    boolean Yaxis;

    int timerCD;
    int timerBM;


    public Bus(String id, String type, int numMaxPassanger, List<City> cities) {   //Construtor para a classe bus
        this.id = id;
        this.type = type;
        this.numMaxPassanger = numMaxPassanger;
        this.cities = cities;
    }

    public String getId() {
        return id;
    }      //Vai buscar o id do autocarro

    public String getType() {
        return type;
    }   //Vai buscar o tipo do autocarro

    public int getNumMaxPassanger() {
        return numMaxPassanger;
    }   //Vai buscar o nº máximo de passageiros do autocarro

    public float getNumKmToFinish() {
        return numKmToFinish;
    }   //Vai buscar os km que faltam para terminar a viagem

    public City getDestination() {
        return destination;
    }       //Vai buscar o destino do autocarro

    public List<Passenger> getPassanger() {
        return this.Passanger;
    }   //Vai buscar a lista dos passageiros que se encontram dentro do autcarro

    public void setNumKmToFinish(float numKmToFinish) {
        this.numKmToFinish = numKmToFinish;
    } //Define o nº de km para terminar a viagem

    public void setDestination(City destination) {
        this.destination = destination;
    }   //Define o destino do autocarro

    public void addPassanger(Passenger passenger){
        this.Passanger.add(passenger);
    } //Adiciona um passageiro ao autocarro

    public void rmPassanger(Passenger passenger){
        this.Passanger.remove(passenger);
    } //Remove um passageiro do autocarro

    public int getTempoAvaria() {
        return tempoAvaria;
    }   //Vai buscar o tempo de avaria

    public void setTempoAvaria(int tempoAvaria) {
        this.tempoAvaria = tempoAvaria;
    } //Define um tempo de avaria

    public List<City> getCities() { //Vai buscar a lista das cidades
        return cities;
    }

    public String getStatus() {     //Vai buscar os status dos autocarros
        if(status == 0){
            return String.format("O autocarro está em %s", destination.getName());
        }else if(status == 1){
            return String.format("O autocarro está a %.1f porcento de %s com %s passageiros.  ID: %s. Este autocarro está a andar no sentido: %s", (float) ((originalKmToFinish - numKmToFinish)/originalKmToFinish)*100, this.getDestination().getName(), this.getPassanger().size(), this.getId(), this.stYaxis(getYaxis()));
        }else if(status == 2){
            List<String> anomalias = new ArrayList<>();    //Lista com os tipos de erros que o autocarro pode ter
            anomalias.add("Erro no sistema.");
            anomalias.add("Passageiro sentiu-se mal.");
            anomalias.add("Pneu furado.");
            anomalias.add("Avaria no sistema de refrigeração do autocarro.");

            return String.format("O autocarro encontra-se avariado com %s ", anomalias.get(tipoDeAvaria));
        }
        return "";
    }

    public void setStatus(int status) {
        this.status = status;
    }   //Define o status do autocarro

    public void setOriginalKmToFinish(float originalKmToFinish) {
        this.originalKmToFinish = originalKmToFinish;
    } //Define o nº de km originais que faltavam ao autocarro no inicio da viagem

    public void setTipoDeAvaria(int tipoDeAvaria) {
        this.tipoDeAvaria = tipoDeAvaria;
    } //Define o tipo de avaria

    public int getTimerCD() {
        return timerCD; //return do tempo que autocarro vai parar para mudar de motorista
    }

    public void setTimerCD(int timerCD) {
        this.timerCD = timerCD; //set timer de cima
    }

    public int getTimerBM() {
        return timerBM; //return do tempo que o autocarro vai parar para fazer manutenção
    }

    public void setTimerBM(int timerBM) {
        this.timerBM = timerBM; //set do timer de cima
    }

    public boolean getYaxis() {
        return Yaxis; //get para saber se o autocarro vai para norte ou sul
    }

    public void setYaxis(boolean yaxis) {
        Yaxis = yaxis;
    }   //Define se o autocarro vai para norte ou para sul

    public String stYaxis(boolean yaxis){
        if(yaxis){
            return "Norte";  //Se o boolean for true vai para norte
        }else{
            return "Sul";   //Se o boolean for false vai para sul
        }
    }
}