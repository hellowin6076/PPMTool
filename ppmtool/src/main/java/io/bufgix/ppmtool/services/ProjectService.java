package io.bufgix.ppmtool.services;

import io.bufgix.ppmtool.domain.Project;
import io.bufgix.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project){

        return projectRepository.save(project);
    }
}
