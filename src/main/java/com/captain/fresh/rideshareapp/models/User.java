package com.captain.fresh.rideshareapp.models;

import com.captain.fresh.rideshareapp.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import java.io.Serializable;

/**
 * @author Dhareppa
 * User Address model class.
 */
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = -4736936444848317674L;

    private int userId;

    private String userName;

    private Gender gender;

    private Integer age;

    private boolean driver;

    private boolean active;

    private boolean acceptingRides;

    private Address workAddress;

    public User(int userId, String userName, Gender gender, Integer age) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
