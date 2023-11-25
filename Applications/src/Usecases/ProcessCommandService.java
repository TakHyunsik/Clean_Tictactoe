package Usecases;

import AppInterfaces.ICommand;
import AppInterfaces.IProcessCommandService;
import Repository.ICommandRepository;

public class ProcessCommandService implements IProcessCommandService {
	ICommandRepository repo;

	public ProcessCommandService(ICommandRepository repo) {
		this.repo = repo;
	}

	@Override
	public boolean process(ICommand cmd) {
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
