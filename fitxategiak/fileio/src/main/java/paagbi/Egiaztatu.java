package paagbi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Egiaztatu {
    public static void exekutatu(Scanner sc) {
        System.out.print("Sartu path absolutua: ");
        String testua = sc.nextLine().trim();
 
        Path path = Path.of(testua);
 
        if (!path.isAbsolute()) {
            System.out.println("Hori ez da path absolutua.");
            return;
        }
 
        if (Files.exists(path)) {
            System.out.println("Existitzen da.");

            if (Files.isDirectory(path)) {
                System.out.println("Direktorioa da.");
            } else {
                System.out.println("Fitxategia da.");
            }
        } else {
            System.out.println("Ez da existitzen.");
        }
    }
}
