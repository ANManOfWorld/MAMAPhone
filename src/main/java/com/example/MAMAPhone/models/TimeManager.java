package com.example.MAMAPhone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // класс является сущностью и будет сохраняться в БД
@Table(name = "timeManager")
@Data //аннотация сгенирует при компиляции необходимый код от LOMBOK
@AllArgsConstructor
@NoArgsConstructor
public class TimeManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*@Column(name = "dateOfUpdate")
    private Calendar calendar;*/

    @Column(name = "seconds")
    private Integer seconds;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "days")
    private Integer days;

    @Column(name = "month")
    private Integer month;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
