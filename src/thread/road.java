package thread;

import models.Bus;
import models.City;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class road extends Thread {

    public void run(){

        //sout de teste
        /*
        List<Bus> bus = roadSetup.getBuses();
            System.out.println(bus.size());
            for(int i = 0; i<bus.size();i++) {
                Bus Thisbus = bus.get(i);

                System.out.println("Id: " + Thisbus.getId() + " Número de passageiros: " + Thisbus.getPassanger().size() + " Proximo destino: " + Thisbus.getDestination().getName());
            }

         */

        List<Bus> CDlist = new ArrayList<Bus>();     //Lista para mudar o motorista dos autocarros
        List<Bus> BMlist = new ArrayList<Bus>();     //Lista para fazer a manunteção dos autocarros
        Bus Thisbus = null;

        while(true){
            List<Bus> bus = roadSetup.getBuses();       //Lista dos autocarros na estrada
            if(bus.size() == 0){            //Se a lista dos autocarros estiver vazia continue
                continue;
            } else{
                for(int i = 0; i<bus.size();i++){           //Se tivermos autocarros vamos buscar aquele que queremos
                    Thisbus = bus.get(i);

                    if(roadSetup.getPingEmployee() == 0){       //Se o empregado receber um ping == 0
                        if(Thisbus.getId().matches(roadSetup.getStopBus())){    //Vamos buscar o ID do autocarro
                            roadSetup.rmBuses(Thisbus);         //Removemos o autocarro da lista de autocarros que estão na estrada
                            roadSetup.addParados(Thisbus);      //Adicionar o autocarro a lista de autocarros parados
                            roadSetup.setPingEmployee(5);       //Definimos o ping do empregado para 5
                            roadSetup.setSfuncionar(true);      //SFuncionar passa para true o que quer dizer que o autocarro não vai estar em funcionamento
                            //System.out.println("Autocarro parado com sucesso.");
                            break;
                        }
                    }

                    if(roadSetup.getPingEmployee() == 1){  //Se o empregado receber um ping == 1 quer dizer que vamos dar resume as atividades do autocarro
                        for(int f = 0; f < roadSetup.getParados().size();f++){
                            Bus StopedBus = roadSetup.getParados().get(f); //Vamos buscar a lista de autocarros que se encontram parados
                            if(roadSetup.getParados().get(f).getId().matches(roadSetup.getStopBus())){
                                roadSetup.addBuses(StopedBus);      //Adicionamos o autocarro que estava parado a lista de autocarros ativos
                                roadSetup.rmParados(StopedBus);     //Removemos da lista dos autocarros parados
                                roadSetup.setPingEmployee(5);       //Colocamos o ping a 5 que é neutro
                                roadSetup.setSfuncionar(true);
                                break;
                            }
                        }
                    }

                    if(roadSetup.getPingEmployee() == 2){ //Se o empregado receber um ping == 2 para mudar de condutor
                        if(Thisbus.getId().matches(roadSetup.getStopBus())){
                            roadSetup.rmBuses(Thisbus);     //Removemos o autocarro da estrada para lhe mudar o condutor
                            CDlist.add(Thisbus);            //Metemos o autocarro na lista de autocarros parados para mudar de condutor
                            Thisbus.setTimerCD(20000);      //Tem de esperar 20 segundos para mudar de condutor
                            roadSetup.setPingEmployee(5);   //Volta para o estado de neutro
                            roadSetup.setSfuncionar(true);
                            break;
                        }
                    }

                    if(roadSetup.getPingEmployee() == 3){       //Se o ping for igual 3 vamos fazer Maintenance for autocarro
                        if(Thisbus.getId().matches(roadSetup.getStopBus())){
                            roadSetup.rmBuses(Thisbus); //Removemos o autocarro da lista de autocarros que estão na estrada
                            BMlist.add(Thisbus);        //Adicionas o autocarro a lista de autocarros em maintenance
                            Thisbus.setTimerBM(20000);  //Tem de esperar 20 segundos para concluir a maintenance
                            roadSetup.setPingEmployee(5);   //Volta para o estado neutro
                            roadSetup.setSfuncionar(true);
                            break;
                        }
                    }


                    if(roadSetup.getPingAvariar()){
                        Thisbus.setTipoDeAvaria(roadSetup.getTipoavaria()); //Define o tipo de avaria do autocarro
                        Thisbus.setTempoAvaria(5);  //Define o tempo de avaria
                        avariaSetup.addAvariados(Thisbus);  //Adiciona o autocarro a lista de autocarros avariados
                        Thisbus.setStatus(2);       //Definimos o status do autocarro como avariado
                        bus.remove(Thisbus);        //Removemos o autocarro da lista de autocarros que estão na estrada
                        roadSetup.setPingAvariar(false);
                        }else{
                        if(Thisbus.getNumKmToFinish()<=0){      //Se for <= 0 quer dizer que chegou a uma cidade
                            Thisbus.setStatus(0);           //Definimos o estado como parado
                            offloadSetup.addbuses(Thisbus); //Adiciona o autocarro a lista de autocarros que estão a fazer offload
                            roadSetup.rmBuses(Thisbus);     //Removemos da lista de autocarros que estão na estrada
                        } else{
                            if(Thisbus.getType().matches("LongDrive")){
                                //LONG-DRIVE MAIS LENTO ANDA A 60km/h os outros andam a 90km/h
                                //60/(60^2) será o numero de km que se anda por segundo a 60km/h
                                Thisbus.setNumKmToFinish(Thisbus.getNumKmToFinish() - (60 / (60^2)));
                            }else{
                                Thisbus.setNumKmToFinish(Thisbus.getNumKmToFinish() - (90 / (60^2)));
                            }
                        }
                    }

                    if(CDlist.size() != 0){
                        for(int f = 0; f< CDlist.size(); f++){
                            Bus CDbus = CDlist.get(f);
                            if(CDbus.getTimerCD() == 0){    //Se o timer do Change driver for zero
                                CDlist.remove(CDbus);       //Remove o condutor do autocarro da lista de condutores de autocarros
                                roadSetup.addBuses(CDbus);  //Volta a colocar o autocarro na lista de autocarros na estrada
                            }else{
                                CDbus.setTimerCD(CDbus.getTimerCD()-10);    //Diminuimos o timer
                            }
                        }
                    }

                    if(BMlist.size() != 0) {            //Se a lista de autocarros em maintenance for diferente de zero
                        for (int f = 0; f < BMlist.size(); f++) {   //Percorremos a lista de autocarros em maintenance
                            Bus BMBus = BMlist.get(f);      //Vamos buscar o autocarro que queremos
                            if (BMBus.getTimerBM() == 0) {  //Se o timer for igual a zero
                                BMlist.remove(BMBus);       //Removemos o autocarro da lista de autocarros em maintenance
                                roadSetup.addBuses(BMBus);  //Voltamos a colocar o autocarro na lista de autocarros da road
                            } else {
                                BMBus.setTimerBM(BMBus.getTimerBM() - 10);  //Diminuimos o timer
                            }
                        }
                    }



                }

                int f =0;
                List<City> cities = Thisbus.getCities();
                for(int i = 0; i< cities.size(); i++){      //Verifica se os autocarros estão todos nas cidades de destini
                    if(cities.get(f).getNumPassArrived() == cities.get(f).getNumPassDestiny()){
                        f++;
                    }
                    if(f == 5){         //Se estiverem o programa foi realizado com sucesso
                        System.exit(1);
                    }
                }


                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}