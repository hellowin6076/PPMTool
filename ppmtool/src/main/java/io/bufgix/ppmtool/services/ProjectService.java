package io.bufgix.ppmtool.services;

import io.bufgix.ppmtool.domain.Backlog;
import io.bufgix.ppmtool.domain.Project;
import io.bufgix.ppmtool.domain.User;
import io.bufgix.ppmtool.exceptions.ProjectIdException;
import io.bufgix.ppmtool.exceptions.ProjectNotFoundException;
import io.bufgix.ppmtool.repositories.BacklogRepository;
import io.bufgix.ppmtool.repositories.ProjectRepository;
import io.bufgix.ppmtool.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){
        try{
        	
        	User user = userRepository.findByUsername(username);
        	project.setUser(user);
        	project.setProjectLeader(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        }catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project Id '"+projectId+"' does not exist");
        }
        
        if(!project.getProjectLeader().equals(username)) {
        	throw new ProjectNotFoundException("Project not found in your account");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId, String username){

        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
