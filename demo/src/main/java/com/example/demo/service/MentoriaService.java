package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.entity.Mentoria;
import com.example.demo.mapper.MentoriaMapper;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentoriaService {
    @Autowired
    MentoriaRepository mentoriaRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    MentorRepository mentorRepository;
    @Autowired
    MentoriaMapper mentoriaMapper;

    public MentoriaDTO createMentoria(Long mentorId, Long alunoId) {
        Mentoria mentoria = new Mentoria();
        mentoria.setAluno(alunoRepository.findById(alunoId).get());
        mentoria.setMentor(mentorRepository.findById(mentorId).get());
        mentoriaRepository.save(mentoria);
        return mentoriaMapper.toMentoriaDTO(mentoria);
    }

    public List<MentoriaDTO> getMentorias() {
//        List<MentoriaDTO> listMentoriaDTO = new ArrayList<MentoriaDTO>();
//        for (Mentoria mentoria : mentoriaRepository.findAll()) {
//            MentoriaDTO mentoriaDTO = new MentoriaDTO();
//            mentoriaDTO.setId(mentoria.getId());
//            mentoriaDTO.setAluno(mentoria.getAluno());
//            mentoriaDTO.setMentor(mentoria.getMentor());
//            listMentoriaDTO.add(mentoriaDTO);
//        }
//        return listMentoriaDTO;
        return mentoriaRepository.findAll()
                .parallelStream()
                .map(mentoriaMapper::toMentoriaDTO)
                .collect(Collectors.toList());
    }

    public MentoriaDTO getMentoriaById(Long id) {
//        Mentoria mentoria = mentoriaRepository.findById(id).orElse(null);
//        MentoriaDTO mentoriaDTO = new MentoriaDTO();
//        mentoriaDTO.setId(mentoria.getId());
//        mentoriaDTO.setMentor(mentoria.getMentor());
//        mentoriaDTO.setAluno(mentoria.getAluno());
//        return mentoriaDTO;
        return mentoriaMapper.toMentoriaDTO(mentoriaRepository.findById(id).get());
    }

    public MentoriaDTO updateMentoria(Long mentoriaId, Long mentorId, Long alunoId) {
        Mentoria existingMentoria = mentoriaRepository.findById(mentoriaId).get();
        existingMentoria.setMentor(mentorRepository.findById(mentorId).get());
        existingMentoria.setAluno(alunoRepository.findById(alunoId).get());
        mentoriaRepository.save(existingMentoria);
        return mentoriaMapper.toMentoriaDTO(existingMentoria);
    }

    @Transactional
    public void deleteMentoria(Long id) {
        mentoriaRepository.findById(id).get().setActive(false);
    }
}