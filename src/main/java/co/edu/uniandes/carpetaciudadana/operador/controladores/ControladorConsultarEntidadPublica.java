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
public class ControladorConsultarEntidadPublica {

    @PostMapping("operador/consultarEntidad/{nit}")
    public void consultarEntidad(@RequestParam("Entidad") String name,
                                       @PathVariable String nit) {
        String[] operadores = {"GovCarpeta", "PQCarpeta", "AlpesCarpeta", "EduCarpeta", "VisaCarpeta"};   
        if (name!=null && nit=='987654') {
            System.out.printf("\n La entidad:\n\n"+ name +" con NIT [%s]:\n\n"+ nit+" Se encuentra registrado en la carpeta ciudadana
            \n\n+ Con el operador : "+operadores[(int)(Math.random() * (5 - 1 + 1)) + 1] +".");
        } else {
            System.out.printf("\n La entidad:"+ name +" con NIT [%s]:\n\n", nit+" NO se encuentra registrado en la carpeta ciudadana");
            
        }
    }

}
