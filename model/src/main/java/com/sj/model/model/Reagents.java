package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//试剂
@Entity
@DiscriminatorValue("r")
public class Reagents extends Product {

}
