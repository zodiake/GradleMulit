package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "u")
public class UserIndustryInfo extends IndustryInfo {

}
