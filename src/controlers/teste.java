package controlers;
import models.Bus;
import models.teste2;
import java.util.List;

//Código de testes


public class teste extends Thread{
    public void run() {
        while(true) {
            List<Bus> bus = teste2.getBuses();
            try {
                sleep(2000);
                System.out.println("________TEST________");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i= 0; i<bus.size();i++){

                System.out.println(bus.get(i).getId());
            }
        }
    }
}