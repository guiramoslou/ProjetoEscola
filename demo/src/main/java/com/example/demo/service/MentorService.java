package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Mentor;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    public MentorDTO createMentor(MentorDTO mentorDTO) {
        Mentor mentor = new Mentor();
        mentor.setName(mentorDTO.getName());
        mentor.setClasse(mentorDTO.getClasse());
        mentorRepository.save(mentor);
        mentorDTO.setId(mentor.getId());
        return mentorDTO;
    }

    public List<MentorDTO> getMentores() {
        List<MentorDTO> listMentorDTO = new ArrayList<>();
        for (Mentor mentor : mentorRepository.findAll()) {
            MentorDTO mentorDTO = new MentorDTO();
            mentorDTO.setId(mentor.getId());
            mentorDTO.setName(mentor.getName());
            mentorDTO.setClasse(mentor.getClasse());
            listMentorDTO.add(mentorDTO);
        }
        return listMentorDTO;
    }

    public MentorDTO getMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(mentor.getId());
        mentorDTO.setName(mentor.getName());
        mentorDTO.setClasse(mentor.getClasse());
        return mentorDTO;
    }

    public Mentor getMentorByIdInRepository(Long id) {
        return mentorRepository.findById(id).orElse(null);
    }

    public MentorDTO updateMentor(MentorDTO mentorDTO, Long id) {
        Mentor existingMentor = getMentorByIdInRepository(id);
        existingMentor.setName(mentorDTO.getName());
        existingMentor.setClasse(mentorDTO.getClasse());
        mentorRepository.save(existingMentor);
        mentorDTO.setId(existingMentor.getId());
        return mentorDTO;
    }

    public String deleteMentor(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        mentor.setActive(false);
        return mentor.getName() + " deletado!";
    }
}
