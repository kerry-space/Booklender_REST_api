package se.lexicon.kerry.booklender_rest_api.model.entity.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private  int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;
}
