package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("r")
public class Reagents extends Product {

}
