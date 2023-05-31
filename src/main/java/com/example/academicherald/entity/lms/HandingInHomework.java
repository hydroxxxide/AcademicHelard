package com.example.academicherald.entity.lms;

import com.example.academicherald.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "HandingInHomework")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HandingInHomework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "student_id")
    private User studentId;
    private String linkGitHubHw;
}
