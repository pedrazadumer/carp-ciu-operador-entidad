package co.edu.uniandes.carpetaciudadana.operador.servicios.impl;

import co.edu.uniandes.carpetaciudadana.operador.dto.DocumentoDto;
import co.edu.uniandes.carpetaciudadana.operador.dto.SubCarpetaDto;
import co.edu.uniandes.carpetaciudadana.operador.servicios.ServicioSubCarpetas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicioSubCarpetasEnMemoria implements ServicioSubCarpetas {

    private static final Map<String, List<SubCarpetaDto>> subCarpetasPorNit = new HashMap<>();

    public ServicioSubCarpetasEnMemoria() {
        DocumentoDto documentoIdentidad = new DocumentoDto("DOC-001", "DOCUMENTO_IDENTIDAD");
        DocumentoDto tituloProfesional = new DocumentoDto("DOC-002", "TITULO_PROFESIONAL");
        DocumentoDto tituloPostGrado = new DocumentoDto("DOC-003", "TITULO_POSTGRADO");
        DocumentoDto tituloDoctorado = new DocumentoDto("DOC-004", "TITULO_DOCTORADO");
        DocumentoDto referenciaLaboral = new DocumentoDto("DOC-005", "REFERENCIA_LABORAL");
        DocumentoDto referenciaPersonal = new DocumentoDto("DOC-006", "REFERENCIA_PERSONAL");
        DocumentoDto tituloMaestria = new DocumentoDto("DOC-007", "TITULO_MAESTRIA");

        List<DocumentoDto> documentosRequeridos = new ArrayList<>();
        documentosRequeridos.add(documentoIdentidad);
        documentosRequeridos.add(tituloProfesional);
        SubCarpetaDto subCarpetaProcesoDianIngenieroI = new SubCarpetaDto("CPT-0025", "PROCESO_SELECCION_DIAN_INGENIERO_I",
                Arrays.asList(documentoIdentidad, tituloProfesional));
        SubCarpetaDto subCarpetaProcesoDianJefeSeccion = new SubCarpetaDto("CPT-0026", "PROCESO_SELECCION_DIAN_JEFE_DEPARTAMENTO",
                Arrays.asList(documentoIdentidad, tituloProfesional, referenciaLaboral, referenciaPersonal, tituloDoctorado));
        subCarpetasPorNit.put("800.197.268-4", Arrays.asList(subCarpetaProcesoDianIngenieroI, subCarpetaProcesoDianJefeSeccion));
    }

    @Override
    public List<SubCarpetaDto> consultarSubCarpetasPorNit(String nit) {
        return subCarpetasPorNit.get(nit);
    }

    @Override
    public boolean validarDocumentosSubCarpeta(String nit, String codigoSubCarpeta, List<DocumentoDto> documentos) {
        SubCarpetaDto subCarpetaDto = subCarpetasPorNit.get(nit).stream()
                .filter(c -> c.getCodigo().equals(codigoSubCarpeta))
                .findFirst()
                .get();


        Map<String, String> documentosDisponibles = documentos.stream()
                .collect(Collectors.toMap(d -> d.getCodigo(), d -> d.getNombre()));

        boolean faltanDocumentosRequeridos = subCarpetaDto.getDocumentosRequeridos().stream()
                .anyMatch(d -> documentosDisponibles.get(d.getCodigo()) == null);

        return !faltanDocumentosRequeridos;
    }
}
