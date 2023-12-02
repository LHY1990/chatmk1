package com.lhy.springLettuce.Domain;

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
