package paagbi;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maiuskulak {

    public static void exekutatu(Scanner sc) {
        System.out.print("Sartu karpetaren path-a: ");
        Path karpeta = Path.of(sc.nextLine().trim());

        if (!Files.isDirectory(karpeta)) {
            System.out.println("Ez da karpeta bat edo ez da existitzen.");
            return;
        }

        try {
            prozesatu(karpeta);
            System.out.println("Eginda.");
        } catch (IOException e) {
            System.out.println("Errorea: " + e.getMessage());
        }
    }

    private static void prozesatu(Path karpeta) throws IOException {
        // Primero guardamos el contenido en una lista, para no renombrar
        // ficheros mientras el DirectoryStream todavía está leyendo la carpeta.
        List<Path> elementuak = new ArrayList<>();

        try (DirectoryStream<Path> edukia = Files.newDirectoryStream(karpeta, "*")) {
            for (Path p : edukia) {
                elementuak.add(p);
            }
        }

        for (Path p : elementuak) {
            if (Files.isDirectory(p)) {
                
                prozesatu(p);
            } else {
                // Es un fichero: cambiamos la primera letra a mayúscula
                String izena = p.getFileName().toString();
                String izenBerria = izena.substring(0, 1).toUpperCase() + izena.substring(1);

                // Solo renombramos si el nombre cambia
                if (!izena.equals(izenBerria)) {
                    // resolveSibling() da una ruta en la misma carpeta pero con otro nombre
                    Files.move(p, p.resolveSibling(izenBerria));
                    System.out.println(izena + " -> " + izenBerria);
                }
            }
        }
    }
}
