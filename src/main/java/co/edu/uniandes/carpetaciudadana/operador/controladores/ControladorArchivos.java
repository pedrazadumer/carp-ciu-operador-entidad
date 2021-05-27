package co.edu.uniandes.carpetaciudadana.operador.controladores;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class ControladorArchivos {

    public static final String SEPARADOR =
            "\n===============================================================\n";

    @PostMapping("operador/archivos/{nit}")
    public void loadFiles(@RequestParam("archivos") MultipartFile[] archivos,
                                       @PathVariable String nit) {
        if (isNotEmpty(archivos)) {
            System.out.printf("\nArchivos recibidos para la entidad con NIT [%s]:\n\n", nit);
            printFiles(archivos);
        } else {
            throw new IllegalArgumentException("No se encontraron archivos para cargar en el Request, " +
                    "por favor enviar los archivos a cargar en el Request Param: [archivos].");
        }
    }

    private boolean isNotEmpty(MultipartFile[] archivos) {
        return Arrays.stream(archivos)
                .anyMatch(f -> !StringUtils.isEmpty(f.getOriginalFilename()));
    }

    private static void printFiles(MultipartFile[] archivos) {
        System.out.printf("Nombre del Archivo%s", SEPARADOR);

        for (int i = 0; i < archivos.length; i++) {
            MultipartFile archivo = archivos[i];
            System.out.printf("=> %s", archivo.getOriginalFilename());
            if (i < archivos.length - 1) System.out.println();
        }

        System.out.print(SEPARADOR);
        System.out.printf("Total %s archivo(s).\n\n", archivos.length);
    }
}