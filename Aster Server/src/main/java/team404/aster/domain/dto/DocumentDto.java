package team404.aster.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {
    private String firstName;
    private String lastName;
    private Long documentNumber;
    private Integer age;
    private String documentReceiveDate;
}
