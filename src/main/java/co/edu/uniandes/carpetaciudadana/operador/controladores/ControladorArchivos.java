package co.edu.uniandes.carpetaciudadana.operador.controladores;

import co.edu.uniandes.carpetaciudadana.operador.dto.DocumentoDto;
import co.edu.uniandes.carpetaciudadana.operador.servicios.ServicioSubCarpetas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class ControladorArchivos {

    public static final String SEPARADOR =
            "\n===============================================================\n";

    @Autowired
    private ServicioSubCarpetas servicioSubCarpetas;

    @PostMapping("operador/archivos/{nit}")
    public void loadFiles(@RequestParam("archivos") List<MultipartFile> archivos,
                          @RequestParam("codigoSubCarpeta") String codigoSubCarpeta,
                          @RequestParam("mapaArchivosDocumentos") List<String> mapaArchivosDocumentos,
                          @PathVariable String nit) {

        if (isNotEmpty(archivos)) {
            System.out.printf("\nArchivos recibidos para la entidad con NIT [%s]:\n\n", nit);
            printFiles(archivos);

            System.out.printf("Codigo Sub-Carpeta: %s\n", codigoSubCarpeta);
            System.out.printf("Mapa Archivos A Documentos: %s\n\n", mapaArchivosDocumentos);
            System.out.println("Validando si los documentos enviados estan completos...");

            if (!StringUtils.isEmpty(codigoSubCarpeta)) {
                validarDocumentosRequeridos(codigoSubCarpeta, mapaArchivosDocumentos, nit);
            }
        } else {
            throw new IllegalArgumentException("No se encontraron archivos para cargar en el Request, " +
                    "por favor enviar los archivos a cargar en el Request Param: [archivos].");
        }
    }

    private void validarDocumentosRequeridos(@RequestParam("codigoSubCarpeta") String codigoSubCarpeta, @RequestParam("mapaArchivosDocumentos") List<String> mapaArchivosDocumentos, @PathVariable String nit) {
        boolean documentosCompletos = servicioSubCarpetas.validarDocumentosSubCarpeta(nit, codigoSubCarpeta, mapearAListaDocumentoDto(mapaArchivosDocumentos));
        if (documentosCompletos) {
            System.out.printf("Validacion de documentos requeridos completada para el envío hacia NIT: [%s] con código de sub-carpeta: [%s], el resultado fue: [%s].\n",
                    nit, codigoSubCarpeta, "VALIDACION EXITOSA, TODOS LOS DOCUMENTOS ESTAN COMPLETOS");
        } else {
            System.out.printf("Validacion de documentos requeridos completada para el envío hacia NIT: [%s] con código de sub-carpeta: [%s], el resultado fue: [%s].\n",
                    nit, codigoSubCarpeta, "VALIDACION FALLIDA, FALTAN DOCUMENTOS POR ENVIAR");
            System.out.println("\nNOTIFICANDO A LOS INTERESADOS SOBRE LOS DOCUMENTOS FALTANTES VIA CORREO ELECTRÓNICO.");
        }
    }

    private List<DocumentoDto> mapearAListaDocumentoDto(List<String> mapaArchivosDocumentos) {
        return mapaArchivosDocumentos.stream()
                .map(s -> new DocumentoDto(s.split("=")[0], s.split("=")[0]))
                .collect(Collectors.toList());
    }

    private boolean isNotEmpty(List<MultipartFile> archivos) {
        return archivos.stream()
                .anyMatch(f -> !StringUtils.isEmpty(f.getOriginalFilename()));
    }

    private static void printFiles(List<MultipartFile> archivos) {
        System.out.printf("Nombre del Archivo%s", SEPARADOR);

        for (int i = 0; i < archivos.size(); i++) {
            MultipartFile archivo = archivos.get(i);
            System.out.printf("=> %s", archivo.getOriginalFilename());
            if (i < archivos.size() - 1) System.out.println();
        }

        System.out.print(SEPARADOR);
        System.out.printf("Total %s archivo(s).\n\n", archivos.size());
    }
}
