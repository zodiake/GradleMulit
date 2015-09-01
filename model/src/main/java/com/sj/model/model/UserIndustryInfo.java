package com.sj.model.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "u")
public class UserIndustryInfo extends IndustryInfo implements Serializable {
}
