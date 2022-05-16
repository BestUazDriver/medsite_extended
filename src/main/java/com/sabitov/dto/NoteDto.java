package com.sabitov.dto;

import com.sabitov.models.Note;
import lombok.Data;

import java.util.Calendar;
import java.util.GregorianCalendar;


@Data
public class NoteDto {

    private String address;
    private String noteDate;

    public static Note from(NoteDto noteDto){
        Calendar date = getDate(noteDto.getNoteDate());
        return Note.builder().address(noteDto.getAddress()).noteDate(date).build();
    }

    private static Calendar getDate(String dateLine) {
        Calendar calendar = new GregorianCalendar();
        String[] dateAttributes = dateLine.split("-");
        String time = dateAttributes[2].split("T")[1];
        dateAttributes[2] = dateAttributes[2].split("T")[0];
        String[] timeAttributes = time.split(":");
        calendar.set(
                Integer.parseInt(dateAttributes[0]),
                Integer.parseInt(dateAttributes[1]) - 1,
                Integer.parseInt(dateAttributes[2]),
                Integer.parseInt(timeAttributes[0]),
                Integer.parseInt(timeAttributes[1]),
                0);
        System.out.println(calendar.getTime());
        return calendar;
    }
}
