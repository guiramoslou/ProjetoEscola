package com.example.demo.service;

import com.example.demo.dto.MateriaDTO;
import com.example.demo.entity.Materia;
import com.example.demo.mapper.MateriaMapper;
import com.example.demo.repository.MateriaRepository;
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
public class MateriaServiceTest {

    @Mock
    MateriaRepository materiaRepository;

    @Spy
    MateriaMapper materiaMapper = Mappers.getMapper(MateriaMapper.class);

    @InjectMocks
    MateriaService materiaService;

    @Test
    public void testCreateMateria() {

        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1L);
        materiaDTO.setName("Lógica");
        materiaDTO.setActive(true);

        MateriaDTO returnedMateriaDTO = this.materiaService.createMateria(materiaDTO);

        assertAll(
                () -> assertEquals(materiaDTO.getId(), returnedMateriaDTO.getId()),
                () -> assertEquals(materiaDTO.getName(), returnedMateriaDTO.getName()),
                () -> assertEquals(materiaDTO.getActive(), returnedMateriaDTO.getActive())
        );
    }

    @Test
    public void testGetMaterias() {

        MateriaDTO materiaDTOUm = new MateriaDTO();
        materiaDTOUm.setId(1L);
        materiaDTOUm.setName("Lógica");
        materiaDTOUm.setActive(true);

        MateriaDTO materiaDTODois = new MateriaDTO();
        materiaDTODois.setId(1L);
        materiaDTODois.setName("SQL");
        materiaDTODois.setActive(true);

        List<MateriaDTO> materiaDTOList = new ArrayList<>();
        materiaDTOList.add(materiaDTOUm);
        materiaDTOList.add(materiaDTODois);

        List<Materia> materiaList = materiaDTOList
                .stream()
                .map(materiaMapper::toMateria)
                .collect(Collectors.toList());

        when(materiaRepository.findAll()).thenReturn(materiaList);

        List<MateriaDTO> returnedMateriaDTOList = this.materiaService.getMaterias();

        assertAll(
                () -> assertEquals(materiaDTOList.get(0).getId(), returnedMateriaDTOList.get(0).getId()),
                () -> assertEquals(materiaDTOList.get(0).getName(), returnedMateriaDTOList.get(0).getName()),
                () -> assertEquals(materiaDTOList.get(0).getActive(), returnedMateriaDTOList.get(0).getActive()),
                () -> assertEquals(materiaDTOList.get(1).getId(), returnedMateriaDTOList.get(1).getId()),
                () -> assertEquals(materiaDTOList.get(1).getName(), returnedMateriaDTOList.get(1).getName()),
                () -> assertEquals(materiaDTOList.get(1).getActive(), returnedMateriaDTOList.get(1).getActive())
        );
    }

    @Test
    public void testGetMateriaById() {
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1L);
        materiaDTO.setName("Lógica");
        materiaDTO.setActive(true);

        Materia materia = materiaMapper.toMateria(materiaDTO);

        when(materiaRepository.findById(id)).thenReturn(Optional.of(materia));

        MateriaDTO returnedMateriaDTO = this.materiaService.getMateriaById(id).get();

        assertAll(
                () -> assertEquals(materiaDTO.getId(), returnedMateriaDTO.getId()),
                () -> assertEquals(materiaDTO.getName(), returnedMateriaDTO.getName()),
                () -> assertEquals(materiaDTO.getActive(), returnedMateriaDTO.getActive())
        );
    }

    @Test
    public void testeUpdateMateria() {
        Long id = 1L;

        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(1L);
        materiaDTO.setName("Lógica");
        materiaDTO.setActive(true);

        MateriaDTO returnedMateriaDTO = this.materiaService.updateMateria(materiaDTO, id);

        assertAll(
                () -> assertEquals(materiaDTO.getId(), returnedMateriaDTO.getId()),
                () -> assertEquals(materiaDTO.getName(), returnedMateriaDTO.getName()),
                () -> assertEquals(materiaDTO.getActive(), returnedMateriaDTO.getActive())
        );
    }

    @Test
    public void testeDeleteMateria() {
        Long id = 1L;

        Materia materia = new Materia();
        materia.setId(1L);
        materia.setName("Lógica");
        materia.setActive(true);

        when(materiaRepository.findById(id)).thenReturn(Optional.of(materia));

        this.materiaService.deleteMateria(id);

        assertFalse(materia.getActive());
    }
}
