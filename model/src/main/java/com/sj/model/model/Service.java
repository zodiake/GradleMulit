package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//服务
@Entity
@DiscriminatorValue("s")
public class Service extends Product {

}
