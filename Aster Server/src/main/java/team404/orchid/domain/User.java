package team404.orchid.domain;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends IdEntity {
    private String username;
    private String password;

}
