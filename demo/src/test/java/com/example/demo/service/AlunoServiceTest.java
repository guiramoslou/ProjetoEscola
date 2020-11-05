package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.mapper.AlunoMapper;
import com.example.demo.repository.AlunoRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    AlunoRepository alunoRepository;

    @Spy
    AlunoMapper alunoMapper = Mappers.getMapper(AlunoMapper.class);

    @InjectMocks
    AlunoService alunoService;

    @Test
    public void testGetAlunos() {
        Aluno alunoUm = new Aluno();
        Aluno alunoDois = new Aluno();
        List<Aluno> alunoList = new ArrayList<Aluno>();
        alunoList.add(alunoUm);
        alunoList.add(alunoDois);

        AlunoDTO alunoUmDTO = new AlunoDTO();
        AlunoDTO alunoDoisDTO = new AlunoDTO();
        List<AlunoDTO> alunoListDTO = new ArrayList<AlunoDTO>();
        alunoListDTO.add(alunoUmDTO);
        alunoListDTO.add(alunoDoisDTO);

        when(alunoRepository.findAll()).thenReturn(alunoList);

        List<AlunoDTO> getAlunos = this.alunoService.getAlunos();

        assertEquals(alunoListDTO, getAlunos);

    }

    @Test
    public void testGetAlunoById() {
        Long id = 1L;

        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setActive(true);
        aluno.setName("t");
        aluno.setClasse("teste");
        aluno.setPrograma(null);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setActive(true);
        alunoDTO.setName("t");
        alunoDTO.setClasse("teste");
        alunoDTO.setPrograma(null);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));

        Optional<AlunoDTO> alunoByIndex = this.alunoService.getAlunoByIndex(id);

        assertAll(
                () -> assertTrue(alunoByIndex.isPresent()),
                () -> assertEquals(aluno.getName(), alunoByIndex.get().getName()),
                () -> assertEquals(aluno.getClasse(), alunoByIndex.get().getClasse()),
                () -> assertEquals(aluno.getId(), alunoByIndex.get().getId())
        );
    }

    @Test
    public void testCreateAluno() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(1L);
        alunoDTO.setName("João");
        alunoDTO.setClasse("1-A");
        alunoDTO.setActive(true);

        AlunoDTO returnedAlunoDTO = this.alunoService.createAluno(alunoDTO);

        assertAll(
                () -> assertEquals(alunoDTO.getId(), returnedAlunoDTO.getId()),
                () -> assertEquals(alunoDTO.getName(), returnedAlunoDTO.getName()),
                () -> assertEquals(alunoDTO.getClasse(), returnedAlunoDTO.getClasse()),
                () -> assertEquals(alunoDTO.getActive(), returnedAlunoDTO.getActive())
        );
    }

    @Test
    public void testUpdateAluno() {
        Long id = 1L;
        AlunoDTO receivedAlunoDTO = new AlunoDTO();
        receivedAlunoDTO.setId(id);
        receivedAlunoDTO.setName("João Dias");
        receivedAlunoDTO.setClasse("1-A");
        receivedAlunoDTO.setActive(true);

        AlunoDTO returnedALunoDTO = this.alunoService.updateAluno(receivedAlunoDTO, id);

        assertAll(
                () -> assertEquals(receivedAlunoDTO.getId(), returnedALunoDTO.getId()),
                () -> assertEquals(receivedAlunoDTO.getName(), returnedALunoDTO.getName()),
                () -> assertEquals(receivedAlunoDTO.getClasse(), returnedALunoDTO.getClasse()),
                () -> assertEquals(receivedAlunoDTO.getActive(), returnedALunoDTO.getActive())
        );

    }

    @Test
    public void testDeleteAlunoById(){
        Long id = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setActive(true);

        when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));

        this.alunoService.deleteAlunoById(id);

        Aluno deletedAluno = alunoRepository.findById(id).get();

        assertAll(
                () -> assertEquals(aluno.getId(), deletedAluno.getId()),
                () -> assertFalse(deletedAluno.getActive())
        );
    }
}