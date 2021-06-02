package co.edu.uniandes.carpetaciudadana.operador.contexto;

import co.edu.uniandes.carpetaciudadana.operador.servicios.ServicioSubCarpetas;
import co.edu.uniandes.carpetaciudadana.operador.servicios.impl.ServicioSubCarpetasEnMemoria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracionServicios {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ServicioSubCarpetas servicioSubCarpetas() {
        return new ServicioSubCarpetasEnMemoria();
    }
}
