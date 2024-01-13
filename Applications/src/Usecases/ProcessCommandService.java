package Usecases;

import AppInterfaces.CommandService.ICommand;
import AppInterfaces.CommandService.IProcessCommandService;
import Repository.ICommandRepository;

public class ProcessCommandService implements IProcessCommandService {
	ICommandRepository repo;

	public ProcessCommandService(ICommandRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean process(ICommand cmd) {
		System.out.println("process");
		System.out.println(cmd.check_executable());
		if (cmd.check_executable()) {
			try {
				cmd.execute();
				repo.push_command(cmd);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}
}
