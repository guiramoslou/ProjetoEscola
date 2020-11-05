package com.example.demo.service;

import com.example.demo.dto.ProgramaDTO;
import com.example.demo.entity.Programa;
import com.example.demo.mapper.ProgramaMapper;
import com.example.demo.repository.ProgramaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProgramaServiceTest {

    @Mock
    ProgramaRepository programaRepository;

    @Spy
    ProgramaMapper programaMapper = Mappers.getMapper(ProgramaMapper.class);

    @InjectMocks
    ProgramaService programaService;

    @Test
    public void testCreatePrograma() {
        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(1L);
        programaDTO.setName("Insiders");
        programaDTO.setActive(false);

        ProgramaDTO returnedProgramaDTO = this.programaService.createPrograma(programaDTO);

        Assertions.assertAll(
                () -> assertEquals(programaDTO.getId(), returnedProgramaDTO.getId()),
                () -> assertEquals(programaDTO.getName(), returnedProgramaDTO.getName()),
                () -> assertEquals(programaDTO.getActive(), returnedProgramaDTO.getActive())
        );
    }

    @Test
    public void testGetProgramas() {
        ProgramaDTO programaDTOUm = new ProgramaDTO();
        programaDTOUm.setId(1L);
        programaDTOUm.setName("Insiders");
        programaDTOUm.setActive(false);

        ProgramaDTO programaDTODois = new ProgramaDTO();
        programaDTODois.setId(2L);
        programaDTODois.setName("Hackaton");
        programaDTODois.setActive(true);

        List<ProgramaDTO> programaDTOList = new ArrayList<>();
        programaDTOList.add(programaDTOUm);
        programaDTOList.add(programaDTODois);

        List<Programa> programaList = programaDTOList
                .stream()
                .map(programaMapper::toPrograma)
                .collect(Collectors.toList());

        when(programaRepository.findAll()).thenReturn(programaList);

        List<ProgramaDTO> returnedProgramaDTOList = this.programaService.getProgramas();

        assertAll(
                () -> assertEquals(programaDTOList.get(0).getId(), returnedProgramaDTOList.get(0).getId()),
                () -> assertEquals(programaDTOList.get(0).getName(), returnedProgramaDTOList.get(0).getName()),
                () -> assertEquals(programaDTOList.get(0).getActive(), returnedProgramaDTOList.get(0).getActive()),
                () -> assertEquals(programaDTOList.get(1).getId(), returnedProgramaDTOList.get(1).getId()),
                () -> assertEquals(programaDTOList.get(1).getName(), returnedProgramaDTOList.get(1).getName()),
                () -> assertEquals(programaDTOList.get(1).getActive(), returnedProgramaDTOList.get(1).getActive())
        );

    }

    @Test
    public void testGetProgramaById() {
        Long id = 1L;
        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(id);
        programaDTO.setName("Insiders");
        programaDTO.setActive(false);

        Programa programa = programaMapper.toPrograma(programaDTO);

        when(programaRepository.findById(id)).thenReturn(Optional.of(programa));

        ProgramaDTO returnedProgramaDTO = this.programaService.getProgramaById(id);

        assertAll(
                () -> assertEquals(programaDTO.getId(), returnedProgramaDTO.getId()),
                () -> assertEquals(programaDTO.getName(), returnedProgramaDTO.getName()),
                () -> assertEquals(programaDTO.getActive(), returnedProgramaDTO.getActive())
        );
    }

    @Test
    public void testUpdatePrograma() {
        Long id = 1L;
        ProgramaDTO programaDTO = new ProgramaDTO();
        programaDTO.setId(id);
        programaDTO.setName("Insiders");
        programaDTO.setActive(false);

        ProgramaDTO returnedProgramaDTO = this.programaService.updatePrograma(programaDTO, id);

        assertAll(
                () -> assertEquals(programaDTO.getId(), returnedProgramaDTO.getId()),
                () -> assertEquals(programaDTO.getName(), returnedProgramaDTO.getName()),
                () -> assertEquals(programaDTO.getActive(), returnedProgramaDTO.getActive())
        );
    }

    @Test
    public void testeDeletePrograma() {
        Long id = 1L;
        Programa programa = new Programa();
        programa.setId(id);
        programa.setName("Insiders");
        programa.setActive(true);

        when(programaRepository.findById(id)).thenReturn(Optional.of(programa));

        this.programaService.deleteProgramaById(id);

        assertFalse(programa.getActive());
    }
}
