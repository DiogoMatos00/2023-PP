package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class avaria extends Thread {
    public void run(){
        Random rand = new Random();                 //Definição da função random
        while(true) {
            try {
                sleep(5000);                    //Coloca a thread a dormir por 5000 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (30 <= rand.nextInt(100)){
                roadSetup.setPingAvariar(true); //Manda um ping a confirmar que o autocarro está avariado
                roadSetup.setTipoavaria(rand.nextInt(4));   //Gera um nº random para definir a avaria do autocarro
            }else{
                continue;
            }
        }
    }
}
