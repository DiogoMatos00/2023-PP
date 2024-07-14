package thread;

import models.Bus;

import java.util.List;

public class standby extends Thread {
    public void run(){
        List<Bus> avariados = avariaSetup.getAvariados();    //Vai buscar os autocarros avariados
        while(true){
            try { // Isto serve para os if funcionarem
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(avariados.size() == 0){  //Se não houver avariados, continua
                continue;
            }else{
                for(int i = 0; i<avariados.size();i++) {
                    Bus Thisbus = avariados.get(i);     //Vai buscar os avariados
                    if(Thisbus.getTempoAvaria() == 0){
                        avariados.remove(Thisbus);  //Quando o tempo de avaria for 0 removemos o autocarro dos avariados
                        roadSetup.addBuses(Thisbus); //Adicionar o bus a estrada (road)
                        Thisbus.setStatus(1); //ajudar a printar o status quando é pedido
                        Thisbus.setTempoAvaria(10); //dar reset tempo de avaria
                    }else{
                        try {
                            sleep(1000); //sleep de 1000 milisegundos
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Thisbus.setTempoAvaria(Thisbus.getTempoAvaria() - 1); //Counter para acabar o tempo de espera da avaria
                    }
                }
            }
        }
    }
}