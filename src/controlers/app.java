package controlers;

import models.Bus;
import models.City;
import models.Passenger;
import thread.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class app extends Thread{

    //public static void main(String[] args) throws InterruptedException {
    public void start(){
        //Inicialização dos atributos
        int TotalPassengers = 250;

        int NumberExpressBus = 3; //número de autocarros expresso que estão na simulação
        int NumberMiniBus = 3; //número de autocarros mini bus que estão na simulação
        int NumberConvetionalBus = 4; //número de autocarros convencionais que estão na simulação
        int NumberLongDriveBus = 0; //número de autocarros long drive que estão na simulação

        int NumberCoimbraBus = 2; //número de autocarros que estão em Coimbra
        int NumberPortoBus = 2; //número de autocarros que estão no Porto
        int NumberCascaisBus = 2; //número de autocarros que estão em Cascais
        int NumberBragaBus = 2; //número de autocarros que estão em Braga
        int NumberLisboaBus = 2; //número de autocarros que estão em Lisboa

        int CascaisLisboa = 38; //distância em km entre Cascais e Lisboa
        int LisboaCoimbra = 212; //distância em km entre Lisboa e Coimbra
        int CoimbraPorto = 119; //distância em km entre Coimbra e Porto
        int PortoBraga = 56; //distância em km entre Porto e Braga
        // int LisboaPorto = 304; //distância em km entre Lisboa e Porto


        //Read Config file
        try (FileReader reader = new FileReader("src/assets/config")) {
            Properties properties = new Properties();
            properties.load(reader);

            TotalPassengers = parseInt(properties.getProperty("N_de_passageiros_na_simulação")); //vai buscar o Nº Passageiros na simulação ao config
            NumberExpressBus = parseInt(properties.getProperty("N_de_autocarros_Expresso")); //vai buscar o nº de autocarros expresso ao config
            NumberMiniBus = parseInt(properties.getProperty("N_de_autocarros_Mini-Bus")); //vai buscar o nº de autocarros mini bus ao config
            NumberConvetionalBus = parseInt(properties.getProperty("N_de_autocarros_Convencionais")); //vai buscar o nº de autocarros convencionais ao config
            NumberLongDriveBus = parseInt(properties.getProperty("N_de_autocarros_Long_Drive")); //vai buscar o nº de autocarros long drive ao config

            NumberCoimbraBus = parseInt(properties.getProperty("N_de_autocarros_em_Coimbra")); //vai buscar o nº de autocarros em Coimbra ao config
            NumberPortoBus = parseInt(properties.getProperty("N_de_autocarros_em_Porto")); //vai buscar o nº de autocarros no Porto ao config
            NumberCascaisBus = parseInt(properties.getProperty("N_de_autocarros_em_Cascais")); //vai buscar o nº de autocarros em Cascais ao config
            NumberBragaBus = parseInt(properties.getProperty("N_de_autocarros_em_Braga")); //vai buscar o nº de autocarros em Braga ao config
            NumberLisboaBus = parseInt(properties.getProperty("N_de_autocarros_em_Lisboa")); //vai buscar o nº de autocarros em Lisboa ao config

            CascaisLisboa = parseInt(properties.getProperty("Cascais_Lisboa")); //vai buscar a distância (em KM) entre Cascais e Lisboa ao config
            LisboaCoimbra = parseInt(properties.getProperty("Lisboa_Coimbra")); //vai buscar a distância (em KM)entre Lisboa e Coimbra ao config
            CoimbraPorto = parseInt(properties.getProperty("Coimbra_Porto")); //vai buscar a distância (em KM) entre Coimbra e Porto ao config
            PortoBraga = parseInt(properties.getProperty("Porto_Braga")); //vai buscar a distância (em KM) entre Porto e Braga ao config
            //LisboaPorto = parseInt(properties.getProperty("Lisboa_Porto")); //vai buscar a distância (em KM) entre Lisboa e Porto ao config


            //sout de teste
            /*
            System.out.println(TotalPassengers);
            System.out.println(NumberExpressBus);
            System.out.println(NumberMiniBus);
            System.out.println(NumberConvetionalBus);
            System.out.println(NumberLongDriveBus);

            System.out.println(NumberCoimbraBus);
            System.out.println(NumberPortoBus);
            System.out.println(NumberCascaisBus);
            System.out.println(NumberBragaBus);
            System.out.println(NumberLisboaBus);

            System.out.println(CascaisLisboa);
            System.out.println(LisboaCoimbra);
            System.out.println(CoimbraPorto);
            System.out.println(PortoBraga);
            System.out.println(LisboaPorto);
             */
        } catch (IOException e) {
            System.out.println("Ficheiro Config inacessível!"); //Dá uma mensagem de erro quando não é possivel aceder ao ficheiro config mesmo que não se consiga ler o config file a aplicação irá correr com valores default
        }

        //Config Errors
        int totalBus = NumberExpressBus + NumberMiniBus + NumberConvetionalBus + NumberLongDriveBus; //número total do tipo de autocarros
        int TotalStartingPoint = NumberCoimbraBus + NumberPortoBus + NumberCascaisBus + NumberBragaBus + NumberLisboaBus; //soma total do número de autocarros em todas as cidades

        if (totalBus > 10) {
            System.out.println("Número total de autocarros ultrapassado"); //dá um print a dizer que o número total de autocarros foi ultrapassado
            System.exit(1);
        }
        if (4 > totalBus) { //se a soma total do número total do tipo de autocarros for inferior a 4
            System.out.println("Número total de autocarros insuficiente"); //dá um print a dizer que o número total de autocarros é insuficiente
            System.exit(1);
        }
        if (totalBus != TotalStartingPoint) { //se a soma total do número total do tipo de autocarros for diferente da soma total do número de autocarros em todas as cidade
            System.out.println("Existe uma diferença entre o numero de autocarros e a distribuição dos autocarros."); //dá um print a dizer que há diferança entre o número das duas variaveis
            System.exit(1);
        }

        if(NumberExpressBus == 0 || NumberMiniBus == 0 || NumberConvetionalBus == 0){
            System.out.println("Tem de haver pelo menos um autocarro de cada tipo!");
            System.exit(1);
        }

        //City initialization
        City Cascais = new City("Cascais"); //criação do objeto da cidade Cascais
        City Lisboa = new City("Lisboa"); //criação do objeto da cidade Lisboa
        City Coimbra = new City("Coimbra"); //criação do objeto da cidade Coimbra
        City Porto = new City("Porto"); //criação do objeto da cidade Porto
        City Braga = new City("Braga"); //criação do objeto da cidade Braga

        //Passagers Object initialization
        List<City> citiesStartingPoint = new ArrayList(); //Lista das cidades dos passageiros no ponto de partida
        List<City> citiesDestination = new ArrayList();   //Lista das cidades dos passageiros no destino
        List<City> Cities = new ArrayList();              //Todas as cidades no programa
        Cities.add(Cascais); //adicionar a cidade Cascais à lista "cities" que tem todas as cidades do programa
        Cities.add(Lisboa); //adicionar a cidade Lisboa à lista "cities" que tem todas as cidades do programa
        Cities.add(Coimbra); //adicionar a cidade Coimbra à lista "cities" que tem todas as cidades do programa
        Cities.add(Porto); //adicionar a cidade Porto à lista "cities" que tem todas as cidades do programa
        Cities.add(Braga); //adicionar a cidade Braga à lista "cities" que tem todas as cidades do programa




        for (int i = 0; i < TotalPassengers; i++) {
            City StartingCity = tools.getcity(Cities);
            citiesStartingPoint.add(StartingCity); //list cities para conseguir saber quantos começam em cada cidade.

            City DestinationCity = tools.getcityDestination(Cities, StartingCity);
            citiesDestination.add(DestinationCity); //list Destinationcities para conseguir saber quantos começam em cada cidade.

            Passenger passenger = new models.Passenger(i + 1, StartingCity, DestinationCity); //criação de um passageiro com um certo id, com uma cidade de ponto de partida e um certo destino

            if (StartingCity == Cascais) { //se o ponto de partida for Cascais
                Cascais.addPassenger(passenger); //adiciona o passageiro à cidade Cascais
            } else if (StartingCity == Lisboa) { //se o ponto de partida for Lisboa
                Lisboa.addPassenger(passenger); //adiciona o passageiro à cidade Lisboa
            } else if (StartingCity == Coimbra) { //se o ponto de partida for Coimbra
                Coimbra.addPassenger(passenger); //adiciona o passageiro à cidade Coimbra
            } else if (StartingCity == Porto) { //se o ponto de partida for Porto
                Porto.addPassenger(passenger); //adiciona o passageiro à cidade Porto
            } else if (StartingCity == Braga) { //se o ponto de partida for Braga
                Braga.addPassenger(passenger); //adiciona o passageiro à cidade Braga
            }
        }

        Cascais.setNumKmNext(CascaisLisboa); //set da distância em km entre Cascais e a proxima paragem (Lisboa)
        Lisboa.setNumKmNext(LisboaCoimbra); //set da distância em km entre Lisboa e a proxima paragem (Coimbra)
        Coimbra.setNumKmNext(CoimbraPorto); //set da distância em km entre Coimbra e a proxima paragem (Porto)
        Porto.setNumKmNext(PortoBraga); //set da distância em km entre Porto e a proxima paragem (Braga)

        Lisboa.setNumKMPrevious(CascaisLisboa); //set da distancia em km entre Lisboa e a paragem anterior (Cascais)
        Coimbra.setNumKMPrevious(LisboaCoimbra); //set da distancia em km entre Coimbra e paragem anterior (Lisboa)
        Porto.setNumKMPrevious(CoimbraPorto); //set da distancia em km entre Porto e a paragem anterior (Coimbra)
        Braga.setNumKMPrevious(PortoBraga); //set da distancia em km entre Braga e a paragem anterior (Porto)


        Cascais.setNumPassStartingPoint(Collections.frequency(citiesStartingPoint, Cascais)); //Set do número de passageiros que comecam em Cascais
        Lisboa.setNumPassStartingPoint(Collections.frequency(citiesStartingPoint, Lisboa)); //Set do número de passageiros que comecam em Lisboa
        Coimbra.setNumPassStartingPoint(Collections.frequency(citiesStartingPoint, Coimbra)); //Set do número de passageiros que comecam em Coimbra
        Porto.setNumPassStartingPoint(Collections.frequency(citiesStartingPoint, Porto)); //Set do número de passageiros que comecam em Porto
        Braga.setNumPassStartingPoint(Collections.frequency(citiesStartingPoint, Braga)); //Set do número de passageiros que comecam em Braga

        Cascais.setNumPassDestiny(Collections.frequency(citiesDestination, Cascais)); //Set ao número de passageiros que tem como destino a cidade Cascais
        Lisboa.setNumPassDestiny(Collections.frequency(citiesDestination, Lisboa)); //Set ao número de passageiros que tem como destino a cidade Lisboa
        Coimbra.setNumPassDestiny(Collections.frequency(citiesDestination, Coimbra)); //Set ao número de passageiros que tem como destino a cidade Coimbra
        Porto.setNumPassDestiny(Collections.frequency(citiesDestination, Porto)); //Set ao número de passageiros que tem como destino a cidade Porto
        Braga.setNumPassDestiny(Collections.frequency(citiesDestination, Braga)); //Set ao número de passageiros que tem como destino a cidade Braga

        Cascais.setPrevious(null); //Não existe cidade anterior a Cascais
        Cascais.setNext(Lisboa); //Set a próxima cidade a seguir a Cascais (Lisboa)

        Lisboa.setPrevious(Cascais); //Set da cidade anterior a Lisboa (Cascais)
        Lisboa.setNext(Coimbra); //Set a próxima cidade a seguir a Lisboa (Coimbra)

        Coimbra.setPrevious(Lisboa); //Set da cidade anterior a Coimbra (Lisboa)
        Coimbra.setNext(Porto); //Set a próxima cidade a seguir a Coimbra (Porto)

        Porto.setPrevious(Coimbra); //Set da cidade anterior a Porto (Coimbra)
        Porto.setNext(Braga); //Set a próxima cidade a seguir a Porto (Braga)

        Braga.setPrevious(Porto); //Set da cidade anterior a Braga (Porto)
        Braga.setNext(null); //Não existe cidade a seguir ao Porto

        List<Bus> bus = new ArrayList(); //All bus



        for (int i = 0; i < NumberExpressBus; i++) { //Enquanto que o número de autocarros Expresso for menor que 2
            String id = "E" + i; //E = expresso + 0/1
            bus.add(new Bus(id, "Express", 69, Cities)); //Cria-se um autocarro E0 e/ou E1
        }

        for (int i = 0; i < NumberMiniBus; i++) { //Enquanto que o número de autocarros MiniBus for menor que 2
            String id = "MB" + i; //MB = MiniBus + 0/1
            bus.add(new Bus(id, "Mini-Bus", 24, Cities)); //Cria-se um autocarro MB0 e MB1
        }

        for (int i = 0; i < NumberConvetionalBus; i++) { //Enquanto que o número de autocarros Convencionais for menor que 4
            String id = "C" + i; //C = convencional + 0/1/2/3
            bus.add(new Bus(id, "Convencional", 51, Cities)); //Cria-se um autocarro C1, C2, C3 e/ou C4
        }

        for (int i = 0; i < NumberLongDriveBus; i++) { //Enquanto que o número de autocarros LongDrive for menor que 2
            String id = "LD" + i; //LD = LongDrive + 0/1
            bus.add(new Bus(id, "LongDrive", 51, Cities));//Cria-se um autocarro LD1 e/ou LD2
        }


        Collections.shuffle(bus);

        List<Bus> buses = new ArrayList<>(); //Lista buses (lista para RoadSetup)
        List<Bus> AllBuses = new ArrayList<>(); //Criação lista AllBuses (lista para global setup, para fazer os prints [são testes])

        for (int i = 0; i < bus.size(); i++) {
            boolean token = false;
            Bus Thisbus = bus.get(i);
            if (i < NumberCoimbraBus) {
                boolean getboolean = tools.getBoolean(); //True-> Next False -> Previous
                Thisbus.setYaxis(getboolean);
                if (getboolean) {
                    Thisbus.setDestination(Coimbra.getNext());              //Se calhar True vamos definir a cidade
                    Thisbus.setNumKmToFinish(Coimbra.getNumKmNext());       //a seguir como o proximo destino
                    Thisbus.setOriginalKmToFinish(Coimbra.getNumKmNext());
                    Thisbus.setStatus(1);
                } else {
                    Thisbus.setDestination(Coimbra.getPrevious());          //Se calhar false vamos definir a cidade
                    Thisbus.setNumKmToFinish(Coimbra.getNumKMPrevious());   //anterior como próximo destino
                    Thisbus.setOriginalKmToFinish(Coimbra.getNumKMPrevious());
                    Thisbus.setStatus(1);
                }

                List<Passenger> lista = Coimbra.getListOfPassengers();
                for (int j = 0; j < lista.size(); j++) {
                    if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                        buses.add(Thisbus);         //Quando o autocarro estiver cheio vamos adiciona-lo a lita que
                        AllBuses.add(Thisbus);      // está na classe road para entrar na simulação
                        token = true;
                        break;
                    }
                    if (tools.choiceBus(Coimbra, lista.get(j), getboolean)) {
                        Thisbus.addPassanger(lista.get(j));
                        lista.remove(j);
                    } else {
                        continue;
                    }

                }
            } else if(i < NumberCoimbraBus + NumberPortoBus){
                boolean getboolean = tools.getBoolean();                        //Se for True vamos definir a cidade
                Thisbus.setYaxis(getboolean);
                if (getboolean) {                                               //a seguir como o proximo destino
                    Thisbus.setDestination(Porto.getNext());
                    Thisbus.setNumKmToFinish(Porto.getNumKmNext());
                    Thisbus.setOriginalKmToFinish(Porto.getNumKmNext());
                    Thisbus.setStatus(1);
                } else {
                    Thisbus.setDestination(Porto.getPrevious());            //Se calhar false vamos definir a cidade
                    Thisbus.setNumKmToFinish(Porto.getNumKMPrevious());     //anterior como próximo destino
                    Thisbus.setOriginalKmToFinish(Porto.getNumKMPrevious());
                    Thisbus.setStatus(1);
                }

                List<Passenger> lista = Porto.getListOfPassengers();
                for (int j = 0; j < lista.size(); j++) {
                    if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                        buses.add(Thisbus);         //Quando o autocarro estiver cheio vamos adiciona-lo a lista que está na classe road para entrar na simulação
                        AllBuses.add(Thisbus);
                        token = true;
                        break;
                    }
                    if (tools.choiceBus(Porto, lista.get(j), getboolean)) {
                        Thisbus.addPassanger(lista.get(j));
                        lista.remove(j);
                    } else {
                        continue;
                    }
                }
            } else if(i < NumberCoimbraBus + NumberPortoBus + NumberLisboaBus){
                boolean getboolean = tools.getBoolean();
                Thisbus.setYaxis(getboolean);
                if (getboolean) {
                    Thisbus.setDestination(Lisboa.getNext());                    //Se calhar True vamos definir a cidade
                    Thisbus.setNumKmToFinish(Lisboa.getNumKmNext());            //a seguir como o proximo destino
                    Thisbus.setOriginalKmToFinish(Lisboa.getNumKmNext());
                    Thisbus.setStatus(1);
                } else {
                    Thisbus.setDestination(Lisboa.getPrevious());               //Se calhar false vamos definir a cidade
                    Thisbus.setNumKmToFinish(Lisboa.getNumKMPrevious());        //anterior como próximo destino
                    Thisbus.setOriginalKmToFinish(Lisboa.getNumKMPrevious());
                    Thisbus.setStatus(1);
                }

                List<Passenger> lista = Lisboa.getListOfPassengers();
                for (int j = 0; j < lista.size(); j++) {
                    if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                        buses.add(Thisbus);             //Quando o autocarro estiver cheio vamos adiciona-lo a lista que está na classe road para entrar na simulação

                        AllBuses.add(Thisbus);
                        token = true;
                        break;
                    }
                    if (tools.choiceBus(Lisboa, lista.get(j), getboolean)) {
                        Thisbus.addPassanger(lista.get(j));
                        lista.remove(j);
                    } else {
                        continue;
                    }
                }
            }else if(i < NumberCoimbraBus + NumberPortoBus + NumberLisboaBus + NumberBragaBus) {
                Thisbus.setDestination(Braga.getPrevious());                //Neste caso Braga é o último destino do
                Thisbus.setYaxis(true);
                Thisbus.setNumKmToFinish(Braga.getNumKMPrevious());         //autocarro por isso só pode voltar para
                Thisbus.setOriginalKmToFinish(Braga.getNumKMPrevious());    //trás
                Thisbus.setStatus(1);

                List<Passenger> lista = Braga.getListOfPassengers();
                for (int j = 0; j < lista.size(); j++) {
                    if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                        buses.add(Thisbus);  //Quando o autocarro estiver cheio vamos adiciona-lo a lista que está na classe road para entrar na simulação

                        AllBuses.add(Thisbus);
                        token = true;
                        break;
                    }
                    if (tools.choiceBus(Braga, lista.get(j), false)) {
                        Thisbus.addPassanger(lista.get(j));
                        lista.remove(j);
                    } else {
                        continue;
                    }
                }

            }else if(i < NumberCoimbraBus + NumberPortoBus + NumberLisboaBus + NumberBragaBus + NumberCascaisBus){
                Thisbus.setDestination(Cascais.getNext());              //Cascais é a primeira cidade por isso o
                Thisbus.setYaxis(false);
                Thisbus.setNumKmToFinish(Cascais.getNumKmNext());       //autocarro só pode ir para a frente
                Thisbus.setOriginalKmToFinish(Cascais.getNumKmNext());
                Thisbus.setStatus(1);


                List<Passenger> lista = Cascais.getListOfPassengers();
                for (int j = 0; j < lista.size(); j++) {
                    if (Thisbus.getPassanger().size() == Thisbus.getNumMaxPassanger()) {
                        buses.add(Thisbus);     //Quando o autocarro estiver cheio vamos adiciona-lo a lita que
                                                // está na classe road para entrar na simulação
                        AllBuses.add(Thisbus);
                        token = true;
                        break;
                    }
                    if (tools.choiceBus(Cascais, lista.get(j), true)) {
                        Thisbus.addPassanger(lista.get(j));
                        lista.remove(j);
                    } else {
                        continue;
                    }
                }
            }
            if(token == false){         //Se o token for falso colocamos o bus na lista de autocarros
                buses.add(Thisbus);
                AllBuses.add(Thisbus);
            }
        }

        List<Bus> OffLoadbuses = new ArrayList<Bus>();
        //System.out.println(buses.size());
        GlobalSetup gs = new GlobalSetup(AllBuses);         //Coloca um setup para quando a simulação começa
        roadSetup rs = new roadSetup(buses);
        avariaSetup as = new avariaSetup();
        offloadSetup os = new offloadSetup(OffLoadbuses, Cities);

        //System.out.println(Cascais.getListOfPassengers().size());


        //Criação das threads
        road thread1 = new road(); //Criação da thread para os autocarros em andamento
        standby thread2 = new standby(); //Criação da thread para os autocarros ficarem em standby
        offload thread3 = new offload(); //Criação da thread para os passageiros sairem
        avaria thread4 = new avaria();  //Criação da thread para os autocarros que estão com uma avaria

        thread1.start(); //Ativa a Thread 1
        thread2.start(); //Ativa a Thread 2
        thread3.start(); //Ativa a Thread 3
        thread4.start(); //Ativa a Thread 4

    }
}


