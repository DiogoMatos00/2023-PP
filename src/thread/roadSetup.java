package thread;

import models.Bus;

import java.util.ArrayList;
import java.util.List;

public class roadSetup {
    //SETUP PARA A THREAD ROAD Serve para conseguir transferir data para a thread road

    static List<Bus> buses;         //Lista com todos os autocarros que estão na estrada
    static boolean pingAvariar;     //Manda um ping para controlar as ações
    static int tipoavaria;          //Tipo de avaria que o autocarro pode sofrer

    static int pingEmployee = 5; // 0 -> Stop  1 -> Resume  2 -> Change Driver 3 -> Inspection    5 -> neutral
    static String StopBus = " ";
    static List<Bus> parados = new ArrayList<Bus>(); //Criação de lista de autocarros parados

    static boolean Sfuncionar;




    public roadSetup(List<Bus> buses) {
        this.buses = buses;
    }

    public static List<Bus> getBuses() {        //Vai buscar todos os autocarros que estão na estrada
        return buses;
    }

    public static boolean getPingAvariar() {    //Vai buscar as ações do ping
        return pingAvariar;
    }

    public static void setPingAvariar(boolean pingAvariar) {    //Define a ação do ping
        roadSetup.pingAvariar = pingAvariar;
    }

    public static int getTipoavaria() {
        return tipoavaria;
    }   //Vai buscar o tipo de avaria

    public static void setTipoavaria(int tipoavaria) {
        roadSetup.tipoavaria = tipoavaria;
    }   //Define o tipo de avaria

    public static void addBuses(Bus bus){
        buses.add(bus);
    }   //Adiciona um autcarro a lista de autocarros que estão na estrada

    public static void rmBuses(Bus bus){
        buses.remove(bus);
    }   //Remove um autocarro da lista dos autocarros que estão na estrada

    public static String getStopBus() {
        return roadSetup.StopBus;
    }   //Vai buscar os autocarros que estão parados

    public static void setStopBus(String stopBus) {
        roadSetup.StopBus = stopBus;
    }   //Define os autocarros

    public static int getPingEmployee() {
        return pingEmployee;
    }   //Vai buscar o ping atual do empregado

    public static void setPingEmployee(int pingEmployee) {
        roadSetup.pingEmployee = pingEmployee;
    }   //Define o ping do empregado

    public static List<Bus> getParados() {
        return parados;
    }   //Vai buscar todos os autocarros parados

    public static void addParados(Bus bus) {
        roadSetup.parados.add(bus);
    }   //Adiciona um autocarro aos autocarros parados

    public static void rmParados(Bus bus) {
        roadSetup.parados.remove(bus);
    }   //Remove um autocarro da lista de autocarros parados

    public static boolean getSfuncionar() {
        return Sfuncionar;
    }       //Verifica se o autocarro está parado ounão
                                                                        //Ajuda a mandar as mensagens de erro
    public static void setSfuncionar(boolean sfuncionar) {
        Sfuncionar = sfuncionar;
    }
}
