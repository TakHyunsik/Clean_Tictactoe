package Fivewood;

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
		if (cmd.check_executable()) {
			try {
				System.out.println(cmd.ToSting());
				System.out.println("assertEquals(PlayerType.None, distinct_service.get_winner());");
				cmd.execute();
				repo.push_command(cmd);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}
}
