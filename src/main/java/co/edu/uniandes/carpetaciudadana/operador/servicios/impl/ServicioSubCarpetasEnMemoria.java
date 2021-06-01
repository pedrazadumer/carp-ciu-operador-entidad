package co.edu.uniandes.carpetaciudadana.operador.servicios.impl;

import co.edu.uniandes.carpetaciudadana.operador.dto.DocumentoDto;
import co.edu.uniandes.carpetaciudadana.operador.dto.SubCarpetaDto;
import co.edu.uniandes.carpetaciudadana.operador.servicios.ServicioSubCarpetas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioSubCarpetasEnMemoria implements ServicioSubCarpetas {

    private static final Map<String, List<SubCarpetaDto>> subCarpetasPorNit = new HashMap<>();

    public ServicioSubCarpetasEnMemoria() {
        DocumentoDto documentoIdentidad = new DocumentoDto("DOC-001", "DOCUMENTO_IDENTIDAD");
        DocumentoDto tituloProfesional = new DocumentoDto("DOC-002", "TITULO_PROFESIONAL");
        List<DocumentoDto> documentosRequeridos = new ArrayList<>();
        documentosRequeridos.add(documentoIdentidad);
        documentosRequeridos.add(tituloProfesional);
        SubCarpetaDto subCarpeta = new SubCarpetaDto("CPT-0025", "PROCESO_SELECCION_DIAN", documentosRequeridos);
        subCarpetasPorNit.put("800.197.268-4", Collections.singletonList(subCarpeta));
    }

    @Override
    public List<SubCarpetaDto> consultarSubCarpetasPorNit(String nit) {
        return subCarpetasPorNit.get(nit);
    }

    @Override
    public boolean validarDocumentosSubCarpeta(String codigoSubCarpeta, List<DocumentoDto> documentos) {
        return false;
    }
}
