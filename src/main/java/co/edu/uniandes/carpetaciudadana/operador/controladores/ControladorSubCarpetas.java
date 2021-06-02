package co.edu.uniandes.carpetaciudadana.operador.controladores;

import co.edu.uniandes.carpetaciudadana.operador.dto.DocumentoDto;
import co.edu.uniandes.carpetaciudadana.operador.dto.RespuestaConsultarSubCarpetasDto;
import co.edu.uniandes.carpetaciudadana.operador.dto.SubCarpetaDto;
import co.edu.uniandes.carpetaciudadana.operador.servicios.ServicioSubCarpetas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ControladorSubCarpetas {

    private static final String SEPARADOR =
            "\n===============================================================\n";

    @Autowired
    private ServicioSubCarpetas servicioSubCarpetas;

    @GetMapping("operador/sub-carpetas/{nit}")
    public RespuestaConsultarSubCarpetasDto consultarSubCarpetasPorNit(@PathVariable String nit) {

        RespuestaConsultarSubCarpetasDto respuesta = new RespuestaConsultarSubCarpetasDto();
        List<SubCarpetaDto> subCarpetasEntidad = servicioSubCarpetas.consultarSubCarpetasPorNit(nit);
        respuesta.setSubCarpetas(subCarpetasEntidad);

        System.out.printf("%sOPERACION: Consultar Sub-Carpetas Entidad.%s\nNit: [%s]\n", SEPARADOR, SEPARADOR, nit);

        subCarpetasEntidad
                .forEach(this::imprimirSubCarpeta);

        System.out.println(SEPARADOR);
        return respuesta;
    }

    private void imprimirSubCarpeta(SubCarpetaDto subCarpetaDto) {
        System.out.printf("\n** Sub-Carpeta:**\n\t- Codigo: [%s]\n", subCarpetaDto.getCodigo());
        System.out.printf("\t- Nombre: [%s]\n", subCarpetaDto.getNombre());
        subCarpetaDto.getDocumentosRequeridos()
                .forEach(this::imprimirDocumento);
    }

    private void imprimirDocumento(DocumentoDto documentoDto) {
        System.out.printf("\t\tDocumento Requerido:\n\t\t\t- Codigo: [%s]\n", documentoDto.getCodigo());
        System.out.printf("\t\t\t- Nombre: [%s]\n", documentoDto.getNombre());
    }
}
