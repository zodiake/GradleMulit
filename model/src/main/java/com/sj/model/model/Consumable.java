package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//耗材
@Entity
@DiscriminatorValue("c")
public class Consumable extends Product {
}
