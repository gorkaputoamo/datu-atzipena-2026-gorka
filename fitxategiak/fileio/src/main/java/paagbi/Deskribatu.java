package paagbi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Deskribatu {

    public static void exekutatu(Scanner sc) {

        // Erabiltzaileari galdetzen diogu zer motatako elementua deskribatu nahi duen.
        // Sartutako testua trim() erabiliz garbitzen dugu eta letra xehez bihurtzen dugu,
        // gero switch-ean konparaketak errazago egiteko.
        System.out.print("Zer zoaz deskribatzera? (arraina/ugaztuna/barazkia/esnekia) ");
        String mota = sc.nextLine().trim().toLowerCase();

        // Elementuaren izena eskatzen diogu erabiltzaileari.
        // Izen hori gero fitxategiaren izen gisa erabiliko da.
        System.out.print("Zein? ");
        String izena = sc.nextLine().trim();

        // Erabiltzaileak elementuari buruzko deskribapena sartzen du.
        // Testu hau sortuko dugun .txt fitxategiaren barruan gordeko da.
        System.out.print("Nolakoa da? ");
        String deskribapena = sc.nextLine();

        // Hemen aukeratuko dugu zein karpetatan gorde behar den fitxategia,
        // erabiltzaileak lehenago aukeratutako motaren arabera.
        Path karpeta;

        switch (mota) {

            // "arraina" edo "arrainak" sartzen bada, arrainen karpetara joango gara.
            case "arraina", "arrainak" ->
                    karpeta = KarpetaSortu.BASEA.resolve("animaliak/arrainak");

            // "ugaztuna" edo "ugaztunak" sartzen bada, ugaztunen karpetara joango gara.
            case "ugaztuna", "ugaztunak" ->
                    karpeta = KarpetaSortu.BASEA.resolve("animaliak/ugaztunak");

            // "barazkia" edo "barazkiak" sartzen bada, barazkien karpetara joango gara.
            case "barazkia", "barazkiak" ->
                    karpeta = KarpetaSortu.BASEA.resolve("elikagaiak/barazkiak");

            // "esnekia" edo "esnekiak" sartzen bada, esnekien karpetara joango gara.
            case "esnekia", "esnekiak" ->
                    karpeta = KarpetaSortu.BASEA.resolve("elikagaiak/esnekiak");

            // Erabiltzaileak aurreko aukeretatik kanpoko zerbait sartzen badu,
            // mota hori ezezaguna dela adierazten dugu eta metodoa amaitzen dugu.
            default -> {
                System.out.println("Mota ezezaguna.");
                return;
            }
        }

        try {
            // Aukeratutako karpeta oraindik existitzen ez bada, sortu egiten dugu.
            // createDirectories() erabiliz, beharrezkoak diren goiko karpetak ere
            // automatikoki sortuko dira.
            Files.createDirectories(karpeta);

            // Fitxategiaren bidea sortzen dugu.
            // Erabiltzaileak adibidez "tigrea" sartzen badu, fitxategia
            // "tigrea.txt" izenarekin sortuko da aukeratutako karpetaren barruan.
            Path fitxategia = karpeta.resolve(izena + ".txt");

            // Fitxategia sortu eta deskribapena barruan idazten dugu.
            // Fitxategia lehendik existitzen bada, bere edukia ordezkatu egingo da.
            Files.writeString(fitxategia, deskribapena);

            // Erabiltzaileari fitxategia behar bezala sortu dela jakinarazten diogu,
            // eta fitxategiaren bide osoa erakusten dugu.
            System.out.println("Fitxategia sortu da: " + fitxategia);

        } catch (IOException e) {

            // Fitxategia sortzean edo idaztean arazoren bat gertatzen bada,
            // IOException harrapatzen dugu eta errorearen mezua erakusten dugu.
            System.out.println("Errorea: " + e.getMessage());
        }
    }
}
