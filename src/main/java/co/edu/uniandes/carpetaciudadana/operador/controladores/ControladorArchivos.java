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
            imprimirLogsRecepcionArchivos(archivos, mapaArchivosDocumentos, nit);

            if (!StringUtils.isEmpty(codigoSubCarpeta)) {
                validarDocumentosRequeridos(codigoSubCarpeta, mapaArchivosDocumentos, nit);
            }
        } else {
            throw new IllegalArgumentException("No se encontraron archivos para cargar en el Request, " +
                    "por favor enviar los archivos a cargar en el Request Param: [archivos].");
        }
        System.out.println(SEPARADOR);
    }

    private void validarDocumentosRequeridos(String codigoSubCarpeta, List<String> mapaArchivosDocumentos, String nit) {
        System.out.println("\n\nSub-Carpeta recibida. Validando los documentos requeridos aplicables a la Sub-Carpeta.");
        System.out.printf("\nCodigo Sub-Carpeta: %s\n", codigoSubCarpeta);
        boolean documentosCompletos = servicioSubCarpetas.validarDocumentosSubCarpeta(nit, codigoSubCarpeta, mapearAListaDocumentoDto(mapaArchivosDocumentos));
        if (documentosCompletos) {
            System.out.printf("=> Resultado Validacion: [%s].", "VALIDACION EXITOSA, TODOS LOS DOCUMENTOS ESTAN COMPLETOS");
        } else {
            System.out.printf("=> Resultado Validacion: [%s].\n\n", "VALIDACION FALLIDA, FALTAN DOCUMENTOS POR ENVIAR");
            System.out.print("== NOTIFICANDO A LOS INTERESADOS SOBRE LOS DOCUMENTOS FALTANTES VIA CORREO ELECTRONICO ==");
        }
    }

    private List<DocumentoDto> mapearAListaDocumentoDto(List<String> mapaArchivosDocumentos) {
        return mapaArchivosDocumentos.stream()
                .map(s -> new DocumentoDto(s.split("=")[0], s.split("=")[1]))
                .collect(Collectors.toList());
    }

    private boolean isNotEmpty(List<MultipartFile> archivos) {
        return archivos.stream()
                .anyMatch(f -> !StringUtils.isEmpty(f.getOriginalFilename()));
    }

    private void imprimirLogsRecepcionArchivos(List<MultipartFile> archivos, List<String> mapaArchivosDocumentos, String nit) {
        System.out.printf("%sOPERACION: Recibir Paquete Documentos para Entidad.%s\nNit: [%s]\n", SEPARADOR, SEPARADOR, nit);

        System.out.println("Documentos recibidos:");
        System.out.print(mapaArchivosDocumentos.stream().map(archivo -> String.format("- %s", archivo))
                .collect(Collectors.joining("\n")));
    }
}
