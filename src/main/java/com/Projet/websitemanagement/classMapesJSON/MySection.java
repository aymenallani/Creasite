package com.Projet.websitemanagement.classMapesJSON;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MySection {
	private String sectionName;
    private Long templateSectionID;
    private List<NewContent> newContent;
}
