package thread;

import models.Bus;

import java.util.ArrayList;
import java.util.List;

public class avariaSetup {
    static List<Bus> Avariados = new ArrayList<Bus>();  //Lista dos autocarros avariados

    public static void addAvariados(Bus bus){
        avariaSetup.Avariados.add(bus);
    }  //Adiciona um autocarro avariado

    public static void rmAvariados(Bus bus){
        avariaSetup.Avariados.remove(bus);
    } //Remove um autocarro da lista de avariados

    public static List<Bus> getAvariados() {
        return avariaSetup.Avariados;
    }   //Vai buscar todos os autocarros avariados
}
