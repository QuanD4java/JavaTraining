package com.example.Basic_API.entiry;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Component
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date birthday;

    public int getAge(){
        LocalDate birthday=this.birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now=LocalDate.now();
        return Period.between(birthday,now).getYears();
    }


}
