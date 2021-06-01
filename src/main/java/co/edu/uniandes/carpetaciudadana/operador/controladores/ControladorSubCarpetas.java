package co.edu.uniandes.carpetaciudadana.operador.controladores;

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
        return respuesta;
    }
}
