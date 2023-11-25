package Applications;

import AppInterfaces.IUndoCommandService;
import Repository.ICommandRepository;

public class TestUndoCommand implements IUndoCommandService {

	ICommandRepository repo;

	public TestUndoCommand(ICommandRepository repo) {
		this.repo = repo;
	}

	@Override
	public void undo() throws Exception {
		System.out.print("���÷� �������� ����ü");
		repo.pop_command().ifPresent(cmd -> {
			try {
				cmd.undo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public boolean check_undoable() {
		return repo.get_size() > 0;
	}
}