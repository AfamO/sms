package com.longbridge.sams.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Guardian extends AbstractSchoolEntity{

    @ManyToMany
    private List<Parent> parents;

    @ManyToMany
    private List<Student> students;

    private String relationship;
    private String relationshipOfChildToParent;
    
    
}
