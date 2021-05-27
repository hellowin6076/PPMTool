package io.bufgix.ppmtool.services;

import io.bufgix.ppmtool.domain.Project;
import io.bufgix.ppmtool.exceptions.ProjectIdException;
import io.bufgix.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }


    }
}
