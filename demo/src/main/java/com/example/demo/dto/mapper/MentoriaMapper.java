package com.example.demo.dto.mapper;

import com.example.demo.dto.MentoriaDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Mentor;
import com.example.demo.entity.Mentoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MentoriaMapper {
    private Long id;
    private Aluno aluno;
    private Mentor mentor;
    private Boolean active;

    public static Mentoria toMentoria(MentoriaDTO mentoriaDTO) {
        Mentoria mentoria = new Mentoria();
        mentoria.setId(mentoriaDTO.getId());
        mentoria.setAluno(mentoriaDTO.getAluno());
        mentoria.setMentor(mentoriaDTO.getMentor());
        mentoria.setActive(mentoriaDTO.getActive());
        return mentoria;
    }

    public static MentoriaDTO toMentoriaDTO(Mentoria mentoria) {
        MentoriaDTO mentoriaDTO = new MentoriaDTO();
        mentoriaDTO.setId(mentoria.getId());
        mentoriaDTO.setAluno(mentoria.getAluno());
        mentoriaDTO.setMentor(mentoria.getMentor());
        mentoriaDTO.setActive(mentoria.getActive());
        return mentoriaDTO;
    }
}

