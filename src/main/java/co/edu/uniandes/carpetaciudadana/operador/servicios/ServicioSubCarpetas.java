package co.edu.uniandes.carpetaciudadana.operador.servicios;

import co.edu.uniandes.carpetaciudadana.operador.dto.DocumentoDto;
import co.edu.uniandes.carpetaciudadana.operador.dto.SubCarpetaDto;

import java.util.List;

public interface ServicioSubCarpetas {

    List<SubCarpetaDto> consultarSubCarpetasPorNit(String nit);

    boolean validarDocumentosSubCarpeta(String nit, String codigoSubCarpeta, List<DocumentoDto> documentos);
}
