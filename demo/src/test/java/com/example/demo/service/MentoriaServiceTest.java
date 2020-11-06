package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Mentor;
import com.example.demo.entity.Mentoria;
import com.example.demo.mapper.MentoriaMapper;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.repository.MentoriaRepository;
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
public class MentoriaServiceTest {

    @Mock
    MentoriaRepository mentoriaRepository;

    @Mock
    MentorRepository mentorRepository;

    @Mock
    AlunoRepository alunoRepository;

    @Spy
    MentoriaMapper mentoriaMapper = Mappers.getMapper(MentoriaMapper.class);

    @InjectMocks
    MentoriaService mentoriaService;

    @Test
    public void testCreateMentoria() {

        Long mentorId = 1L;
        Long alunoId = 1L;

        Mentor mentor = new Mentor();
        mentor.setId(mentorId);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(1L);
        mentoriaDTO.setMentor(mentor);
        mentoriaDTO.setAluno(aluno);
        mentoriaDTO.setActive(true);

        Mentoria mentoria = mentoriaMapper.toMentoria(mentoriaDTO);

        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mentor));
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        MentoriaDTO returnedMentoriaDTO = this.mentoriaService.createMentoria(mentorId, alunoId);

        Assertions.assertAll(
//                () -> assertEquals(mentoriaDTO.getId(), returnedMentoriaDTO.getId()),
                () -> assertEquals(mentoriaDTO.getMentor().getId(), returnedMentoriaDTO.getMentor().getId()),
                () -> assertEquals(mentoriaDTO.getAluno().getId(), returnedMentoriaDTO.getAluno().getId())
//                () -> assertEquals(mentoriaDTO.getActive(), returnedMentoriaDTO.getActive())
        );
    }

    @Test
    public void testGetMentorias() {
        Mentor mentor = new Mentor();
        mentor.setId(1L);

        Aluno aluno = new Aluno();
        aluno.setId(1L);

        MentoriaDTO mentoriaDTOUm = new MentoriaDTO();
        mentoriaDTOUm.setId(1L);
        mentoriaDTOUm.setMentor(mentor);
        mentoriaDTOUm.setAluno(aluno);
        mentoriaDTOUm.setActive(true);

        MentoriaDTO mentoriaDTODois = new MentoriaDTO();
        mentoriaDTODois.setId(2L);
        mentoriaDTODois.setMentor(mentor);
        mentoriaDTODois.setAluno(aluno);
        mentoriaDTODois.setActive(false);

        List<MentoriaDTO> mentoriaDTOList = new ArrayList<>();
        mentoriaDTOList.add(mentoriaDTOUm);
        mentoriaDTOList.add(mentoriaDTODois);

        List<Mentoria> mentoriaList = mentoriaDTOList
                .stream()
                .map(mentoriaMapper::toMentoria)
                .collect(Collectors.toList());

        when(mentoriaRepository.findAll()).thenReturn(mentoriaList);

        List<MentoriaDTO> returnedMentoriaDTOList = this.mentoriaService.getMentorias();

        assertAll(
                () -> assertEquals(mentoriaDTOList.get(0).getId(), returnedMentoriaDTOList.get(0).getId()),
                () -> assertEquals(mentoriaDTOList.get(0).getMentor().getId(), returnedMentoriaDTOList.get(0).getMentor().getId()),
                () -> assertEquals(mentoriaDTOList.get(0).getAluno().getId(), returnedMentoriaDTOList.get(0).getAluno().getId()),
                () -> assertEquals(mentoriaDTOList.get(0).getActive(), returnedMentoriaDTOList.get(0).getActive()),
                () -> assertEquals(mentoriaDTOList.get(1).getId(), returnedMentoriaDTOList.get(1).getId()),
                () -> assertEquals(mentoriaDTOList.get(1).getMentor().getId(), returnedMentoriaDTOList.get(1).getMentor().getId()),
                () -> assertEquals(mentoriaDTOList.get(1).getAluno().getId(), returnedMentoriaDTOList.get(1).getAluno().getId()),
                () -> assertEquals(mentoriaDTOList.get(1).getActive(), returnedMentoriaDTOList.get(1).getActive())
        );
    }

    @Test
    public void testGetMentoriaById() {

        Long id = 1L;
        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(1L);
        mentoriaDTO.setActive(true);

        Mentoria mentoria = mentoriaMapper.toMentoria(mentoriaDTO);

        when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));

        MentoriaDTO returnedMentoriaDTO = this.mentoriaService.getMentoriaById(id);

        assertAll(
                () -> assertEquals(mentoriaDTO.getId(), returnedMentoriaDTO.getId()),
                () -> assertEquals(mentoriaDTO.getActive(), returnedMentoriaDTO.getActive())
        );
    }

    @Test
    public void testUpdateMentoria() {
        Long mentoriaId = 1L;
        Long mentorId = 1L;
        Long alunoId = 1L;

        Mentoria mentoria = new Mentoria();
        mentoria.setId(mentoriaId);

        Mentor mentor = new Mentor();
        mentor.setId(mentorId);

        Aluno aluno = new Aluno();
        aluno.setId(alunoId);

        when(mentoriaRepository.findById(mentoriaId)).thenReturn(Optional.of(mentoria));
        when(mentorRepository.findById(mentorId)).thenReturn(Optional.of(mentor));
        when(alunoRepository.findById(alunoId)).thenReturn(Optional.of(aluno));

        MentoriaDTO returnedMentoriaDTO = this.mentoriaService.updateMentoria(mentoriaId, mentorId, alunoId);

        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(1L);
        mentoriaDTO.setMentor(mentor);
        mentoriaDTO.setAluno(aluno);

        assertAll(
                () -> assertEquals(mentoriaDTO.getId(), returnedMentoriaDTO.getId()),
                () -> assertEquals(mentoriaDTO.getActive(), returnedMentoriaDTO.getActive()),
                () -> assertEquals(mentoriaDTO.getMentor().getId(), returnedMentoriaDTO.getMentor().getId()),
                () -> assertEquals(mentoriaDTO.getAluno().getId(), returnedMentoriaDTO.getAluno().getId())
        );
    }

    @Test
    public void testDeleteMentoria() {
        Long id = 1L;
        Mentoria mentoria = new Mentoria();
        mentoria.setId(1L);
        mentoria.setActive(true);

        when(mentoriaRepository.findById(id)).thenReturn(Optional.of(mentoria));

        this.mentoriaService.deleteMentoria(id);

        assertFalse(mentoria.getActive());
    }
}
