package br.com.softblue.bluefood.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class IOUtils {

    public static void copy(InputStream inputStream, String fileName, String outputDir) throws IOException {
        Files.copy(inputStream, Paths.get(outputDir, fileName), StandardCopyOption.REPLACE_EXISTING);
    }
}
