package com.example.demo.service;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.entity.Mentoria;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.MentorRepository;
import com.example.demo.repository.MentoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentoriaService {
    @Autowired
    MentoriaRepository mentoriaRepository;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    MentorRepository mentorRepository;

    public MentoriaDTO createMentoria(Long mentorId, Long alunoId) {
        Mentoria mentoria = new Mentoria();
        mentoria.setAluno(alunoRepository.findById(alunoId).orElse(null));
        mentoria.setMentor(mentorRepository.findById(mentorId).orElse(null));
        mentoriaRepository.save(mentoria);
        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(mentoria.getId());
        mentoriaDTO.setAluno(mentoria.getAluno());
        mentoriaDTO.setMentor(mentoria.getMentor());
        return mentoriaDTO;
    }

    public List<MentoriaDTO> getMentorias() {
        List<MentoriaDTO> listMentoriaDTO = new ArrayList<MentoriaDTO>();
        for (Mentoria mentoria : mentoriaRepository.findAll()) {
            MentoriaDTO mentoriaDTO = new MentoriaDTO();
            mentoriaDTO.setId(mentoria.getId());
            mentoriaDTO.setAluno(mentoria.getAluno());
            mentoriaDTO.setMentor(mentoria.getMentor());
            listMentoriaDTO.add(mentoriaDTO);
        }
        return listMentoriaDTO;
    }

    public MentoriaDTO getMentoriaById(Long id) {
        Mentoria mentoria = mentoriaRepository.findById(id).orElse(null);
        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(mentoria.getId());
        mentoriaDTO.setMentor(mentoria.getMentor());
        mentoriaDTO.setAluno(mentoria.getAluno());
        return mentoriaDTO;
    }

    public MentoriaDTO updateMentoria(Long mentoriaId, Long mentorId, Long alunoId) {
        Mentoria existingMentoria = mentoriaRepository.findById(mentoriaId).orElse(null);
        existingMentoria.setMentor(mentorRepository.findById(mentorId).orElse(null));
        existingMentoria.setAluno(alunoRepository.findById(alunoId).orElse(null));
        mentoriaRepository.save(existingMentoria);
        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(existingMentoria.getId());
        mentoriaDTO.setMentor(existingMentoria.getMentor());
        mentoriaDTO.setAluno(existingMentoria.getAluno());
        return mentoriaDTO;
    }

    public String deleteMentoria(Long id) {
        Mentoria mentoria = mentoriaRepository.findById(id).orElse(null);
        mentoria.setActive(false);
        return "Mentoria " + mentoria.getId() + " deletada!";
    }
}