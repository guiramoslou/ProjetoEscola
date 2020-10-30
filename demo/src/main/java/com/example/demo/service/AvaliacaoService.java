package com.example.demo.service;

import com.example.demo.dto.AvaliacaoDTO;
import com.example.demo.dto.mapper.AlunoMapper;
import com.example.demo.dto.mapper.AvaliacaoMapper;
import com.example.demo.dto.mapper.MateriaMapper;
import com.example.demo.dto.mapper.MentorMapper;
import com.example.demo.entity.Avaliacao;
import com.example.demo.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    AlunoService alunoService;

    @Autowired
    MentorService mentorService;

    @Autowired
    MateriaService materiaService;

    @Deprecated
    public AvaliacaoDTO createAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = AvaliacaoMapper.toAvaliacao(avaliacaoDTO);
        avaliacaoRepository.save(avaliacao);
        return AvaliacaoMapper.toAvaliacaoDTO(avaliacao);
    }

    public List<AvaliacaoDTO> getAvaliacoes() {
        return avaliacaoRepository.findAll()
                .stream()
                .map(AvaliacaoMapper::toAvaliacaoDTO)
                .collect(Collectors.toList());
    }

    public Optional<AvaliacaoDTO> getAvaliacaoById(Long id) {
        return avaliacaoRepository.findById(id).map(AvaliacaoMapper::toAvaliacaoDTO);
    }

    @Deprecated
    public AvaliacaoDTO updateAvaliacao(AvaliacaoDTO avaliacaoDTO, Long id) {
        Avaliacao avaliacao = AvaliacaoMapper.toAvaliacao(avaliacaoDTO);
        avaliacao.setId(id);
        avaliacaoRepository.save(avaliacao);
        return AvaliacaoMapper.toAvaliacaoDTO(avaliacao);
    }

    @Transactional
    public void deleteAvaliacao(Long id) {
        avaliacaoRepository.findById(id).get().setActive(false);
    }

    public AvaliacaoDTO avaliarAluno(Long alunoId, Long mentorId, Long materiaId, Double nota) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setActive(true);
        avaliacao.setAluno(AlunoMapper.toAluno(alunoService.getAlunoByIndex(alunoId).get()));
        avaliacao.setMentor(MentorMapper.toMentor(mentorService.getMentorById(mentorId).get()));
        avaliacao.setMateria(MateriaMapper.toMateria(materiaService.getMateriaById(materiaId).get()));
        avaliacao.setNota(nota);
        avaliacao.setData(LocalDate.now());
        avaliacaoRepository.save(avaliacao);
        return AvaliacaoMapper.toAvaliacaoDTO(avaliacao);
    }

    public AvaliacaoDTO updateNotaAvaliacao(Long id, Double nota) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        avaliacao.setNota(nota);
        avaliacaoRepository.save(avaliacao);
        return AvaliacaoMapper.toAvaliacaoDTO(avaliacao);
    }
}
