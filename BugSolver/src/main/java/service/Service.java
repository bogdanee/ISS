package service;

import model.Programmer;
import model.Tester;
import repository.orm.BugHbmRepository;
import repository.orm.ProgrammerHbmRepository;
import repository.orm.TesterHbmRepository;

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

}
