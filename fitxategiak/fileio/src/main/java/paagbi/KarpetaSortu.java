package paagbi;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class KarpetaSortu {
    public static final Path BASEA = Path.of("karpeta_berriak");
 
    public static void exekutatu() {

        String[] karpetak = {
            "animaliak/arrainak",
            "animaliak/ugaztunak",
            "elikagaiak/barazkiak",
            "elikagaiak/esnekiak"
        };
 
        try {
            for (String k : karpetak) {
                // resolve() pega una ruta a continuación de otra: karpeta_berriak/animaliak/arrainak
                // createDirectories() crea también las carpetas padre si faltan,
                // y no da error si ya existen
                Files.createDirectories(BASEA.resolve(k));
            }
            System.out.println("Karpeta egitura sortu da: " + BASEA.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Errorea: " + e.getMessage());
        }
    }
}
