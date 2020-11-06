package com.example.demo.service;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.MateriaDTO;
import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Avaliacao;
import com.example.demo.entity.Materia;
import com.example.demo.entity.Mentor;
import com.example.demo.mapper.AlunoMapper;
import com.example.demo.mapper.AvaliacaoMapper;
import com.example.demo.mapper.MateriaMapper;
import com.example.demo.mapper.MentorMapper;
import com.example.demo.repository.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoServiceTest {

    @Mock
    AvaliacaoRepository avaliacaoRepository;

    @InjectMocks
    AvaliacaoService avaliacaoService;

    @Mock
    AlunoService alunoService;

    @Mock
    MentorService mentorService;

    @Mock
    MateriaService materiaService;

    @Spy
    AlunoMapper alunoMapper = Mappers.getMapper(AlunoMapper.class);

    @Spy
    MentorMapper mentorMapper = Mappers.getMapper(MentorMapper.class);

    @Spy
    MateriaMapper materiaMapper = Mappers.getMapper(MateriaMapper.class);

    @Spy
    AvaliacaoMapper avaliacaoMapper = Mappers.getMapper(AvaliacaoMapper.class);

    public static Materia materia() {
        Materia materia = new Materia();
        materia.setId(1L);
        return materia;
    }

    public static Mentor mentor() {
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        return mentor;
    }

    public static Aluno aluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        return aluno;
    }

    public static LocalDate data() {
        LocalDate date = LocalDate.now();
        return date;
    }

    public static AvaliacaoDTO avaliacaoDTO() {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(1L);
        avaliacaoDTO.setMateria(materia());
        avaliacaoDTO.setMentor(mentor());
        avaliacaoDTO.setAluno(aluno());
        avaliacaoDTO.setData(data());
        avaliacaoDTO.setNota(10.0);
        avaliacaoDTO.setActive(true);
        return avaliacaoDTO;
    }

    @Test
    public void testGetAvaliacoes() {
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();
        AvaliacaoDTO avaliacaoDTOUm = avaliacaoDTO();
        AvaliacaoDTO avaliacaoDTODois = avaliacaoDTO();
        avaliacaoDTODois.setId(2L);
        avaliacaoDTOList.add(avaliacaoDTOUm);
        avaliacaoDTOList.add(avaliacaoDTODois);

        List<Avaliacao> avaliacaoList = avaliacaoDTOList
                .stream()
                .map(avaliacaoMapper::toAvaliacao)
                .collect(Collectors.toList());

        when(avaliacaoRepository.findAll()).thenReturn(avaliacaoList);

        List<AvaliacaoDTO> returnedAvaliacaoDTOList = this.avaliacaoService.getAvaliacoes();

        assertAll(
                () -> assertEquals(avaliacaoDTOList.get(0).getId(), returnedAvaliacaoDTOList.get(0).getId()),
                () -> assertEquals(avaliacaoDTOList.get(0).getActive(), returnedAvaliacaoDTOList.get(0).getActive()),
                () -> assertEquals(avaliacaoDTOList.get(1).getId(), returnedAvaliacaoDTOList.get(1).getId()),
                () -> assertEquals(avaliacaoDTOList.get(1).getActive(), returnedAvaliacaoDTOList.get(1).getActive())
        );
    }

    @Test
    public void testGetAvaliacaoById() {
        Long id = 1L;
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTO();
        Avaliacao avaliacao = avaliacaoMapper.toAvaliacao(avaliacaoDTO);

        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));

        AvaliacaoDTO returnedAvaliacaoDTO = this.avaliacaoService.getAvaliacaoById(id).get();

        assertAll(
                () -> assertEquals(avaliacaoDTO.getId(), returnedAvaliacaoDTO.getId()),
                () -> assertEquals(avaliacaoDTO.getMateria().getId(), returnedAvaliacaoDTO.getMateria().getId()),
                () -> assertEquals(avaliacaoDTO.getMentor().getId(), returnedAvaliacaoDTO.getMentor().getId()),
                () -> assertEquals(avaliacaoDTO.getAluno().getId(), returnedAvaliacaoDTO.getAluno().getId()),
                () -> assertEquals(avaliacaoDTO.getData(), returnedAvaliacaoDTO.getData()),
                () -> assertEquals(avaliacaoDTO.getNota(), returnedAvaliacaoDTO.getNota()),
                () -> assertEquals(avaliacaoDTO.getActive(), returnedAvaliacaoDTO.getActive())
        );
    }

    @Test
    public void testDeleteAvaliacao() {
        Long id = 1L;

        Avaliacao avaliacao = avaliacaoMapper.toAvaliacao(avaliacaoDTO());

        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));

        this.avaliacaoService.deleteAvaliacao(id);

        assertFalse(avaliacao.getActive());
    }

    @Test
    public void testAvaliarAluno() {
        Long alunoId = 1L;
        Long mentorId = 1L;
        Long materiaId = 1L;
        Double nota = 10.0;
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTO();
        AlunoDTO alunoDTO = alunoMapper.toAlunoDTO(aluno());
        MentorDTO mentorDTO = mentorMapper.toMentorDTO(mentor());
        MateriaDTO materiaDTO = materiaMapper.toMateriaDTO(materia());

        when(alunoService.getAlunoByIndex(alunoId)).thenReturn(Optional.of(alunoDTO));
        when(mentorService.getMentorById(mentorId)).thenReturn(Optional.of(mentorDTO));
        when(materiaService.getMateriaById(materiaId)).thenReturn(Optional.of(materiaDTO));

        AvaliacaoDTO returnedAvaliacaoDTO = this.avaliacaoService.avaliarAluno(alunoId, mentorId, materiaId, nota);

        assertAll(
//                () -> assertEquals(avaliacaoDTO.getId(), returnedAvaliacaoDTO.getId()),
                () -> assertEquals(avaliacaoDTO.getMateria().getId(), returnedAvaliacaoDTO.getMateria().getId()),
                () -> assertEquals(avaliacaoDTO.getMentor().getId(), returnedAvaliacaoDTO.getMentor().getId()),
                () -> assertEquals(avaliacaoDTO.getAluno().getId(), returnedAvaliacaoDTO.getAluno().getId()),
                () -> assertEquals(avaliacaoDTO.getData(), returnedAvaliacaoDTO.getData()),
                () -> assertEquals(avaliacaoDTO.getNota(), returnedAvaliacaoDTO.getNota()),
                () -> assertEquals(avaliacaoDTO.getActive(), returnedAvaliacaoDTO.getActive())
        );
    }

    @Test
    public void testUpdateNotaAvaliacao() {
        Long id = 1L;
        Double nota = 10.0;
        AvaliacaoDTO avaliacaoDTO = avaliacaoDTO();
        avaliacaoDTO.setId(id);
        avaliacaoDTO.setNota(nota);

        Avaliacao avaliacao = avaliacaoMapper.toAvaliacao(avaliacaoDTO());

        when(avaliacaoRepository.findById(id)).thenReturn(Optional.of(avaliacao));

        AvaliacaoDTO returnedAvaliacaoDTO = this.avaliacaoService.updateNotaAvaliacao(id, nota);

        assertAll(
                () -> assertEquals(avaliacaoDTO.getId(), returnedAvaliacaoDTO.getId()),
                () -> assertEquals(avaliacaoDTO.getMateria().getId(), returnedAvaliacaoDTO.getMateria().getId()),
                () -> assertEquals(avaliacaoDTO.getMentor().getId(), returnedAvaliacaoDTO.getMentor().getId()),
                () -> assertEquals(avaliacaoDTO.getAluno().getId(), returnedAvaliacaoDTO.getAluno().getId()),
                () -> assertEquals(avaliacaoDTO.getData(), returnedAvaliacaoDTO.getData()),
                () -> assertEquals(avaliacaoDTO.getNota(), returnedAvaliacaoDTO.getNota()),
                () -> assertEquals(avaliacaoDTO.getActive(), returnedAvaliacaoDTO.getActive())
        );
    }

}
