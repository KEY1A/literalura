package com.alura.literalura.dto;

import lombok.Data;
import java.util.List;

@Data
public class GutendexBookDTO {
    private String title;
    private List<GutendexAuthorDTO> authors;
    private List<String> languages;
    private Integer download_count;
}

@Data
class GutendexAuthorDTO {
    private String name;
    private Integer birth_year;
    private Integer death_year;
}