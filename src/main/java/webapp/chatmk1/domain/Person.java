package webapp.chatmk1.domain;

import lombok.*;

import java.io.Serializable;

//@Data
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    String name;
    int age;

}
