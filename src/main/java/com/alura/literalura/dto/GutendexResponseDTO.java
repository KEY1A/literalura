package com.alura.literalura.dto;

import lombok.Data;
import java.util.List;

@Data
public class GutendexResponseDTO {
    private Integer count;
    private String next;
    private String previous;
    private List<GutendexBookDTO> results;
}