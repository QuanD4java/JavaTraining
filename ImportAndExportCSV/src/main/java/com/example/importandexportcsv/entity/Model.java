package com.example.importandexportcsv.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    public Model(String series_reference, String period, Double data_value, String suppressed, String STATUS, String UNITS, Double magnitude, String subject, String group, String series_title_1, String series_title_2, String series_title_3, String series_title_4, String series_title_5) {
        Series_reference = series_reference;
        Period = period;
        Data_value = data_value;
        Suppressed = suppressed;
        this.STATUS = STATUS;
        this.UNITS = UNITS;
        Magnitude = magnitude;
        Subject = subject;
        this.Group = group;
        Series_title_1 = series_title_1;
        Series_title_2 = series_title_2;
        Series_title_3 = series_title_3;
        Series_title_4 = series_title_4;
        Series_title_5 = series_title_5;
    }

    //Series_reference,Period,Data_value,Suppressed,STATUS,UNITS,Magnitude,Subject,Group,Series_title_1,Series_title_2,Series_title_3,Series_title_4,Series_title_5
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Series_reference;
    private String Period;
    private Double Data_value;
    private String Suppressed;
    private String STATUS;
    private String UNITS;
    private Double Magnitude;
    private String Subject;
    @Column(name = "nhom")
    private String Group;
    private String Series_title_1;
    private String Series_title_2;
    private String Series_title_3;
    private String Series_title_4;
    private String Series_title_5;
}
