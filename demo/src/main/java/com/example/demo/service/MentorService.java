package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Mentor;
import com.example.demo.mapper.MentorMapper;
import com.example.demo.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    MentorMapper mentorMapper;

    public MentorDTO createMentor(MentorDTO mentorDTO) {
//        Mentor mentor = new Mentor();
//        mentor.setName(mentorDTO.getName());
//        mentor.setClasse(mentorDTO.getClasse());
        Mentor mentor = mentorMapper.toMentor(mentorDTO);
        mentorRepository.save(mentor);
        return mentorMapper.toMentorDTO(mentor);
    }

    public List<MentorDTO> getMentores() {
//        List<MentorDTO> listMentorDTO = new ArrayList<>();
//        for (Mentor mentor : mentorRepository.findAll()) {
//            MentorDTO mentorDTO = new MentorDTO();
//            mentorDTO.setId(mentor.getId());
//            mentorDTO.setName(mentor.getName());
//            mentorDTO.setClasse(mentor.getClasse());
//            listMentorDTO.add(mentorDTO);
//        }
//        return listMentorDTO;
        return mentorRepository.findAll()
                .parallelStream()
                .map(mentorMapper::toMentorDTO)
                .collect(Collectors.toList());
    }

    public Optional<MentorDTO> getMentorById(Long id) {
//        Mentor mentor = mentorRepository.findById(id).orElse(null);
//        MentorDTO mentorDTO = new MentorDTO();
//        mentorDTO.setId(mentor.getId());
//        mentorDTO.setName(mentor.getName());
//        mentorDTO.setClasse(mentor.getClasse());
//        return mentorDTO;
        return mentorRepository.findById(id).map(mentorMapper::toMentorDTO);
    }

    public Optional<Mentor> getMentorByIdInRepository(Long id) {
        return mentorRepository.findById(id);
    }

    public MentorDTO updateMentor(MentorDTO mentorDTO, Long id) {
//        Mentor existingMentor = getMentorByIdInRepository(id).get();
//        existingMentor.setName(mentorDTO.getName());
//        existingMentor.setClasse(mentorDTO.getClasse());
//        mentorRepository.save(existingMentor);
//        mentorDTO.setId(existingMentor.getId());
//        return mentorDTO;
        Mentor mentor = mentorMapper.toMentor(mentorDTO);
        mentor.setId(id);
        mentorRepository.save(mentor);
        return mentorMapper.toMentorDTO(mentor);
    }

    @Transactional
    public void deleteMentor(Long id) {
        mentorRepository.findById(id).get().setActive(false);
    }
}
