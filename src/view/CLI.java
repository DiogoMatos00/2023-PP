package view;
import controlers.app;
import jdk.swing.interop.SwingInterOpUtils;
import models.Bus;
import thread.GlobalSetup;
import thread.offloadSetup;
import thread.roadSetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CLI extends Thread{

    public static void main(String[] args) throws InterruptedException {



        final var scanner = new Scanner(System.in); //criação
        app application = new app();
        application.start();

        while(true){
            System.out.println("_---------------------------CLI---------------------------_"); //Prints de mensagens que vão aparecer no menu
            System.out.println("_------ s ---> Status ------------------------------------_"); //Prints de mensagens que vão aparecer no menu
            System.out.println("_------ SBus ---> Stop Bus -------------------------------_"); //Prints de mensagens que vão aparecer no menu
            System.out.println("_-------RBus --->-Resume Bus------------------------------_"); //Prints de mensagens que vão aparecer no menu
            System.out.println("_-------CD ---> Change Driver ----------------------------_"); //Prints de mensagens que vão aparecer no menu
            System.out.println("_-------BM ---> Bus Maintenance---------------------------_"); //Prints de mensagens que vão aparecer no menu

            final var line =scanner.nextLine();
            var commands =new ArrayList<>(Arrays.asList(line.split(" ")));

            switch (commands.get(0)){ //Confirmar se os números estão a passar de um lado para o outro
                case "s":
                    List<Bus> buses = GlobalSetup.getBuses();
                    for(int i = 0; i<buses.size(); i++){
                        System.out.println(buses.get(i).getStatus());
                    }

                    continue;

                case "SBus":
                    List<Bus> buses1 = GlobalSetup.getBuses();
                    System.out.println("Lista de autocarros: ");
                    for(int i = 0; i<buses1.size(); i++){
                        System.out.println(buses1.get(i).getId());
                    }

                    final var line1 =scanner.nextLine();
                    var commands1 = new ArrayList<>(Arrays.asList(line1.split(" ")));


                    String busId = commands1.get(0);

                    roadSetup.setStopBus(busId);
                    roadSetup.setPingEmployee(0);

                    sleep(100);

                    if(roadSetup.getSfuncionar()){
                        System.out.println("Autocarro parado com sucesso!");
                        roadSetup.setSfuncionar(false);
                    }else{
                        System.out.println("Autocarro não encontrado ou não em andamento!");
                    }
                    continue;


                case "RBus":
                    List<Bus> buses2 = roadSetup.getParados();
                    if(buses2.size() == 0){
                        System.out.println("Não existe autocarros parados.");
                    }else{
                        System.out.println("Lista de autocarros: ");
                        for(int i = 0; i<buses2.size(); i++){
                            System.out.println(buses2.get(i).getId());
                        }

                        final var line2 =scanner.nextLine();
                        var commands2 = new ArrayList<>(Arrays.asList(line2.split(" ")));
                        String busId1 = commands2.get(0);

                        roadSetup.setStopBus(busId1);
                        roadSetup.setPingEmployee(1);

                        sleep(100);

                        if(roadSetup.getSfuncionar()){
                            System.out.println("Autocarro retoma com sucesso!");
                            roadSetup.setSfuncionar(false);
                        }else{
                            System.out.println("Autocarro não encontrado ou em andamento!");
                        }
                    }
                    continue;


                case "CD": //Change Driver
                    List<Bus> buses3 = GlobalSetup.getBuses();
                    System.out.println("Lista de autocarros: ");
                    for(int i = 0; i<buses3.size(); i++){
                        System.out.println(buses3.get(i).getId());
                    }

                    final var line3  = scanner.nextLine();
                    var commands3 = new ArrayList<>(Arrays.asList(line3.split(" ")));


                    String busId1 = commands3.get(0);

                    roadSetup.setStopBus(busId1);
                    roadSetup.setPingEmployee(2);

                    sleep(100);

                    if(roadSetup.getSfuncionar()){
                        System.out.println("Mensagem para mudar de Condutor Enviada com sucesso!");
                        roadSetup.setSfuncionar(false);
                    }else{
                        System.out.println("Autocarro não encontrado ou não em andamento!");
                    }
                    continue;

                case "BM": //Bus maintenance
                    List<Bus> buses4 = GlobalSetup.getBuses();
                    System.out.println("Lista de autocarros: ");
                    for(int i = 0; i<buses4.size(); i++){
                        System.out.println(buses4.get(i).getId());
                    }

                    final var line4  = scanner.nextLine();
                    var commands4 = new ArrayList<>(Arrays.asList(line4.split(" ")));


                    String busId2 = commands4.get(0);

                    roadSetup.setStopBus(busId2);
                    roadSetup.setPingEmployee(3);

                    sleep(100);

                    if(roadSetup.getSfuncionar()){
                        System.out.println("Mensagem para fazer a manuntenção foi Enviada com sucesso!");
                        roadSetup.setSfuncionar(false);
                    }else{
                        System.out.println("Autocarro não encontrado ou não em andamento!");
                    }
                    continue;


                default:
                    System.out.println("Código mal inserido!");
                    continue;
            }
        }
    }
}
