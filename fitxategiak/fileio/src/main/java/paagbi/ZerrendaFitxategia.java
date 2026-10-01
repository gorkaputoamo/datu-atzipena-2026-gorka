package paagbi;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JFileChooser;

public class ZerrendaFitxategia {
    public static void exekutatu() {
        // JFileChooser abre la ventana para elegir carpeta
        JFileChooser aukeratzailea = new JFileChooser();
        // Solo permitimos elegir carpetas (no ficheros)
        aukeratzailea.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int emaitza = aukeratzailea.showOpenDialog(null);
        if (emaitza != JFileChooser.APPROVE_OPTION) {
            System.out.println("Ez da karpetarik aukeratu.");
            return;
        }

        Path karpeta = aukeratzailea.getSelectedFile().toPath();
        Path irteera = Path.of("zerrenda.txt");

        // Abrimos el DirectoryStream (para leer la carpeta) y el BufferedWriter (para escribir el fichero)
        // Los dos se cierran solos al acabar el try
        try (DirectoryStream<Path> edukia = Files.newDirectoryStream(karpeta);
             BufferedWriter idazlea = Files.newBufferedWriter(irteera)) {

            for (Path elementua : edukia) {
                String mota = Files.isDirectory(elementua) ? "[K] " : "[F] ";
                idazlea.write(mota + elementua.getFileName());
                idazlea.newLine(); // salto de línea
            }
            System.out.println("Zerrenda gorde da: " + irteera.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Errorea: " + e.getMessage());
        }
    }
}