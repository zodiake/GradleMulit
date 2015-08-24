package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//设备
@Entity
@DiscriminatorValue("i")
public class Instrument extends Product {

}
