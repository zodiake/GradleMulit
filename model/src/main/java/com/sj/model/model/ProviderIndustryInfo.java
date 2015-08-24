package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "p")
public class ProviderIndustryInfo extends IndustryInfo {

}
