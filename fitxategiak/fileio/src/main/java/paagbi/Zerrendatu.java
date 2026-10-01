package paagbi;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Zerrendatu {
    public static void exekutatu(Scanner sc) {
        System.out.print("Sartu karpetaren path-a: ");
        Path karpeta = Path.of(sc.nextLine().trim());
 
        // Comprobamos antes que sea una carpeta que existe
        if (!Files.isDirectory(karpeta)) {
            System.out.println("Ez da karpeta bat edo ez da existitzen.");
            return;
        }
 
        // DirectoryStream nos deja recorrer lo que hay dentro de la carpeta.
        // El try(...) lo cierra automáticamente al terminar.
        try (DirectoryStream<Path> edukia = Files.newDirectoryStream(karpeta)) {
            for (Path elementua : edukia) {
                // [K] = karpeta, [F] = fitxategia
                String mota = Files.isDirectory(elementua) ? "[K] " : "[F] ";
                // getFileName() da solo el nombre, sin la ruta completa
                System.out.println(mota + elementua.getFileName());
            }
        } catch (IOException e) {
            // Leer del disco puede fallar (permisos, etc.), así que hay que capturarlo
            System.out.println("Errorea: " + e.getMessage());
        }
    }
}
