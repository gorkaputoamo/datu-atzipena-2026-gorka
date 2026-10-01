package paagbi;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int aukera = -1;

        
        do {
            System.out.println();
            System.out.println("=== MENUA ===");
            System.out.println("1. Path-a egiaztatu");
            System.out.println("2. Karpeta baten edukia ikusi");
            System.out.println("3. Karpeta egitura sortu");
            System.out.println("4. Fitxategi bat sortu (animalia/elikagaia)");
            System.out.println("5. Karpeta baten zerrenda fitxategi batean gorde");
            System.out.println("6. Fitxategien lehen letra maiuskulara");
            System.out.println("0. Irten");
            System.out.print("Aukeratu: ");

            try {
                aukera = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                aukera = -1;
            }

            switch (aukera) {
                case 1 -> Egiaztatu.exekutatu(sc);
                case 2 -> Zerrendatu.exekutatu(sc);
                case 3 -> KarpetaSortu.exekutatu();
                case 4 -> Deskribatu.exekutatu(sc);
                case 5 -> ZerrendaFitxategia.exekutatu();
                case 6 -> Maiuskulak.exekutatu(sc);
                case 0 -> System.out.println("Agur!");
                default -> System.out.println("Aukera okerra.");
            }
        } while (aukera != 0);
    }
}
