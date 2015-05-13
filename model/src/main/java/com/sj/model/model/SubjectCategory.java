package com.sj.model.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="subject_category")
@DiscriminatorValue("sc")
public class SubjectCategory extends Category {


}
