package com.captain.fresh.rideshareapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Dhareppa
 * class for Specifying the Address model class.
 */
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {

    private static final long serialVersionUID = 42L;

    private String city;
}