package co.edu.uniandes.carpetaciudadana.operador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SubCarpetaDto {

    private String codigo;
    private String nombre;
    private List<DocumentoDto> documentosRequeridos;

}
