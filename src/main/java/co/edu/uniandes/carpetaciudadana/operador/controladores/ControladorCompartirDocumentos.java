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
public class ControladorCompartirDocumentos{

    @PostMapping("operador/compartirDocumentos/")
    public void compartirDocumentos(@RequestParam("Person") String idPersona ) {
       
       if (idPersona!=null && idPersona=='987654') {
            System.out.printf("\n Los archivos fueron enviados exitosamente");
        } else {
            System.out.printf("\n Los archivos no pudieron ser enviados");
            
        }
    }

}
