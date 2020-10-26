package com.example.demo.dto.mapper;

import com.example.demo.dto.MentorDTO;
import com.example.demo.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorMapper {
    private Long id;
    private String name;
    private String classe;
    private Boolean active;

    public static MentorDTO toMentorDTO(Mentor mentor) {
        MentorDTO mentorDTO = new MentorDTO();
        mentorDTO.setId(mentor.getId());
        mentorDTO.setName(mentor.getName());
        mentorDTO.setClasse(mentor.getClasse());
        mentorDTO.setActive(mentor.getActive());
        return mentorDTO;
    }

    public static Mentor toMentor(MentorDTO mentorDTO) {
        Mentor mentor = new Mentor();
        mentor.setId(mentorDTO.getId());
        mentor.setName(mentorDTO.getName());
        mentor.setClasse(mentorDTO.getClasse());
        mentor.setActive(mentorDTO.getActive());
        return mentor;
    }
}
