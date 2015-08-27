package com.sj.model.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "p")
public class ProviderIndustryInfo extends IndustryInfo implements Serializable{

}
