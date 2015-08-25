package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("s")
public class Service extends Product {

}
