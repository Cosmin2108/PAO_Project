package agenda;

import java.util.Scanner;

public final class Aplicatie {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        Boolean powerOn = true;
        while(powerOn) {
            Agenda ag;
            System.out.println("Your applications: ");
            System.out.println("1. Your Agenda\n2. Other app that can be added in other package for example\n3. Close your device");
            System.out.print("Choose an application: ");
            int app = Integer.parseInt(myScanner.nextLine());
            if(app == 1)
                ag = Agenda.getInstance();
            if(app == 3)
                powerOn = false;
        }
    }
}
