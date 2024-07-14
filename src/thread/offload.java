package thread;

import models.City;
import models.Passenger;
import models.Bus;
import java.util.List;
import controlers.tools;

public class offload extends Thread {

    public void run() {

        while (true) {
            List<Bus> bus = offloadSetup.getbuses();
            List<City> cities = offloadSetup.getCities();

            try { // Isto serve para os if funcionarem
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (offloadSetup.getbuses().size() == 0) {
                continue;
            } else {
                for (int i = 0; i < offloadSetup.getbuses().size(); i++) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    boolean token = false;
                    Bus Thisbus = bus.get(i);               //Vai buscar o autocarro desejado
                    City city = Thisbus.getDestination();   //Vai buscar o destino desse mesmo autocarro
                    //System.out.println(city.getName());
                    //System.out.println(city.getListOfPassengers().size());
                    for (int j = 0; j < Thisbus.getPassanger().size(); j++) {
                        Passenger thisPassenger = Thisbus.getPassanger().get(j);    //Vai buscar todos os passageiros do
                                                                                    //autocarro
                        if (thisPassenger.getDestination() == Thisbus.getDestination()) {   //Se o destino do passageiro for igual a cidade atual
                            Thisbus.rmPassanger(thisPassenger);                             //Removemos o passageiro do autocarro
                            j--;
                            city.setNumPassArrived(city.getNumPassArrived() + 1);           //Incrementamos o número de passageiros que chegaram a cidade
                        } else {
                            //System.out.println("Sim");
                            city.addPassenger(thisPassenger);       //Adicionamos os passageiros ao autocarro
                            Thisbus.rmPassanger(thisPassenger);
                        }
                    }
                    //System.out.println(city.getListOfPassengers().size());
                        //cascais
                        if (city == cities.get(0)) {
                            Thisbus.setDestination(city.getNext());         //Define o destino do autocarro como a próxima cidade
                            Thisbus.setNumKmToFinish(city.getNumKmNext());  //Define o nº de km para terminar como os km da próxima cidade
                            Thisbus.setOriginalKmToFinish(city.getNumKmNext());
                            List<Passenger> lista = city.getListOfPassengers();
                            Thisbus.setYaxis(false);
                            for (int f = 0; f < lista.size(); f++) {
                                if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                                    //System.out.println("3");
                                    roadSetup.addBuses(Thisbus);        //Se os autocarros tiverem atingido o seu limite de passageiros
                                    offloadSetup.rmbuses(Thisbus);      //São colocados na estrada
                                    Thisbus.setStatus(1);
                                    token = true;

                                    //System.out.println("ESTOU A SAIR: " + Thisbus.getId() + " PARA IR: " + Thisbus.getDestination().getName() + " Com o numero de passageiros: " + Thisbus.getPassanger().size());
                                    break;
                                }
                                if (tools.choiceBus(city, lista.get(f), true)) {
                                    Thisbus.addPassanger(lista.get(f));
                                    city.rmPassenger(lista.get(f));
                                    f--;
                                } else {
                                    continue;
                                }
                            }
                        //braga
                        } else if (city == cities.get(4)) {
                            Thisbus.setDestination(city.getPrevious());             //Define o destino do autocarro como a cidade anterior
                            Thisbus.setNumKmToFinish(city.getNumKMPrevious());      //Define o nº de km para terminar os km da cidade anterior
                            Thisbus.setOriginalKmToFinish(city.getNumKMPrevious());
                            List<Passenger> lista = city.getListOfPassengers();
                            Thisbus.setYaxis(true);
                            for (int f = 0; f < lista.size(); f++) {
                                if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                                    //System.out.println("2");
                                    roadSetup.addBuses(Thisbus);                //Se os autocarros tiverem atingido o seu limite de passageiros
                                    Thisbus.setStatus(1);                       //São colocados na road
                                    offloadSetup.rmbuses(Thisbus);
                                    token = true;

                                    //System.out.println("ESTOU A SAIR: " + Thisbus.getId() + " PARA IR: " + Thisbus.getDestination().getName() + " Com o numero de passageiros: " + Thisbus.getPassanger().size());
                                    break;
                                }

                                if (tools.choiceBus(city, lista.get(f), false)) {
                                    Thisbus.addPassanger(lista.get(f)); //Se der true vai adicionar o passageiro ao autocarro
                                    city.rmPassenger(lista.get(f)); //e remove-lo da cidade
                                    f--;
                                } else {
                                    continue;
                                }
                            }
                        } else {
                            boolean getboolean = tools.getBoolean();
                            Thisbus.setYaxis(getboolean);
                            if (getboolean) {
                                Thisbus.setDestination(city.getNext());             //Se o boolean der verdadeiro
                                Thisbus.setNumKmToFinish(city.getNumKmNext());      //Vai definir o próximo destino como a cidade a seguir
                                Thisbus.setOriginalKmToFinish(city.getNumKmNext());//Define o nº de km para a cidade a seguir
                            } else {
                                Thisbus.setDestination(city.getPrevious());         //Se o boolean der falso
                                Thisbus.setNumKmToFinish(city.getNumKMPrevious());  //Vai definir o próximo destino como a cidade anterior
                                Thisbus.setOriginalKmToFinish(city.getNumKMPrevious()); //Define o nº de km para a cidade anterior
                            }

                            List<Passenger> lista = city.getListOfPassengers();
                            for (int f = 0; f < lista.size(); f++) {

                                if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                                    //System.out.println("1");
                                    roadSetup.addBuses(Thisbus);        //Se os autocarros tiverem atingido o seu limite de passageiros
                                                                        //São colocados nao road
                                    Thisbus.setStatus(1);
                                    offloadSetup.rmbuses(Thisbus);
                                    token = true;

                                    //System.out.println("ESTOU A SAIR: " + Thisbus.getId() + " PARA IR: " + Thisbus.getDestination().getName() + " Com o numero de passageiros: " + Thisbus.getPassanger().size());
                                    break;
                                }
                                if (tools.choiceBus(city, lista.get(f), getboolean)) {
                                    Thisbus.addPassanger(lista.get(f)); //Se der true vai adicionar o passageiro ao autocarro
                                    city.rmPassenger(lista.get(f));   //e remove-lo da cidade
                                    f--;
                                } else {
                                    continue;
                                }

                            }
                        }
                        if(token == false){
                            //System.out.println("0");
                            roadSetup.addBuses(Thisbus); //se o autocarro não encher o token vai ser falso e vai adicionar o autocarro como está a estrada
                            Thisbus.setStatus(1);
                            offloadSetup.rmbuses(Thisbus);
                            //System.out.println("ESTOU A SAIR: " + Thisbus.getId() + " PARA IR: " + Thisbus.getDestination().getName() + " Com o numero de passageiros: " + Thisbus.getPassanger().size());
                            break;
                        }


                }
                }
            }
        }
    }
