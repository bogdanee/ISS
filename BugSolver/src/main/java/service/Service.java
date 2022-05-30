package service;

import model.Bug;
import model.Programmer;
import model.Tester;
import repository.orm.BugHbmRepository;
import repository.orm.ProgrammerHbmRepository;
import repository.orm.TesterHbmRepository;

import java.util.List;

public class Service {
    private BugHbmRepository bugRepository;
    private TesterHbmRepository testerRepository;
    private ProgrammerHbmRepository programmerRepository;

    public Service(BugHbmRepository bugRepository, TesterHbmRepository testerRepository, ProgrammerHbmRepository programmerRepository) {
        this.bugRepository = bugRepository;
        this.testerRepository = testerRepository;
        this.programmerRepository = programmerRepository;
    }

    public Tester findTesterByUsername(String username)
    {
        return testerRepository.findByUsername(username);
    }

    public Programmer findProgrammerByUsername(String username)
    {
        return programmerRepository.findByUsername(username);
    }

    public void addBug(Bug bug) throws Exception {
        if (bug.getName().isEmpty() || bug.getDescription().isEmpty() || bug.getFileAddress().isEmpty())
            throw new Exception("Invalid arguments!");
        bugRepository.add(bug);
    }

    public void deleteBug(Bug bug){
        bugRepository.delete(bug);
    }

    public void updateBug(Bug bug){
        bugRepository.update(bug, bug.getId());
    }

    public List<Bug> getAllBugs(){
        return (List<Bug>) bugRepository.getAll();
    }

}
