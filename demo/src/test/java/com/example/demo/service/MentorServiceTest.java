package com.example.demo.service;

import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Mentor;
import com.example.demo.mapper.MentorMapper;
import com.example.demo.repository.MentorRepository;
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
public class MentorServiceTest {

    @Mock
    MentorRepository mentorRepository;

    @Spy
    MentorMapper mentorMapper = Mappers.getMapper(MentorMapper.class);

    @InjectMocks
    MentorService mentorService;

    @Test
    public void testCreateMentor() {
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("João");
        mentorDTO.setClasse("1-A");
        mentorDTO.setActive(true);

        MentorDTO returnedMentorDTO = this.mentorService.createMentor(mentorDTO);

        assertAll(
                () -> assertEquals(mentorDTO.getId(), returnedMentorDTO.getId()),
                () -> assertEquals(mentorDTO.getName(), returnedMentorDTO.getName()),
                () -> assertEquals(mentorDTO.getClasse(), returnedMentorDTO.getClasse()),
                () -> assertEquals(mentorDTO.getActive(), returnedMentorDTO.getActive())
        );
    }

    @Test
    public void testGetMentores() {

        MentorDTO mentorDTOUm = new MentorDTO();
        mentorDTOUm.setId(1L);
        mentorDTOUm.setName("João");
        mentorDTOUm.setClasse("1-A");
        mentorDTOUm.setActive(true);

        MentorDTO mentorDTODois = new MentorDTO();
        mentorDTODois.setId(2L);
        mentorDTODois.setName("Maria");
        mentorDTODois.setClasse("2-B");
        mentorDTODois.setActive(false);

        List<MentorDTO> mentorDTOList = new ArrayList<MentorDTO>();
        mentorDTOList.add(mentorDTOUm);
        mentorDTOList.add(mentorDTODois);

        List<Mentor> mentorList = mentorDTOList
                .stream()
                .map(mentorMapper::toMentor)
                .collect(Collectors.toList());

        when(mentorRepository.findAll()).thenReturn(mentorList);

        List<MentorDTO> returnedMentorDTOList = this.mentorService.getMentores();

        assertAll(
                () -> assertEquals(mentorDTOList.get(0).getId(), returnedMentorDTOList.get(0).getId()),
                () -> assertEquals(mentorDTOList.get(0).getName(), returnedMentorDTOList.get(0).getName()),
                () -> assertEquals(mentorDTOList.get(0).getClasse(), returnedMentorDTOList.get(0).getClasse()),
                () -> assertEquals(mentorDTOList.get(0).getActive(), returnedMentorDTOList.get(0).getActive()),
                () -> assertEquals(mentorDTOList.get(1).getId(), returnedMentorDTOList.get(1).getId()),
                () -> assertEquals(mentorDTOList.get(1).getName(), returnedMentorDTOList.get(1).getName()),
                () -> assertEquals(mentorDTOList.get(1).getClasse(), returnedMentorDTOList.get(1).getClasse()),
                () -> assertEquals(mentorDTOList.get(1).getActive(), returnedMentorDTOList.get(1).getActive())
        );
    }

    @Test
    public void testGetMentorById() {
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("João");
        mentorDTO.setClasse("1-A");
        mentorDTO.setActive(true);

        Mentor mentor = mentorMapper.toMentor(mentorDTO);

        when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));

        MentorDTO returnedMentorDTO = this.mentorService.getMentorById(id).get();

        assertAll(
                () -> assertEquals(mentorDTO.getId(), returnedMentorDTO.getId()),
                () -> assertEquals(mentorDTO.getName(), returnedMentorDTO.getName()),
                () -> assertEquals(mentorDTO.getClasse(), returnedMentorDTO.getClasse()),
                () -> assertEquals(mentorDTO.getActive(), returnedMentorDTO.getActive())
        );
    }

    @Test
    public void testUpdateMentor() {
        Long id = 1L;

        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(1L);
        mentorDTO.setName("João");
        mentorDTO.setClasse("1-A");
        mentorDTO.setActive(true);

        MentorDTO returnedMentorDTO = this.mentorService.updateMentor(mentorDTO, id);

        assertAll(
                () -> assertEquals(mentorDTO.getId(), returnedMentorDTO.getId()),
                () -> assertEquals(mentorDTO.getName(), returnedMentorDTO.getName()),
                () -> assertEquals(mentorDTO.getClasse(), returnedMentorDTO.getClasse()),
                () -> assertEquals(mentorDTO.getActive(), returnedMentorDTO.getActive())
        );
    }

    @Test
    public void testDeleteMentor() {
        Long id = 1L;

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("João");
        mentor.setClasse("1-A");
        mentor.setActive(true);

        when(mentorRepository.findById(id)).thenReturn(Optional.of(mentor));

        this.mentorService.deleteMentor(id);

        assertFalse(mentor.getActive());
    }
}