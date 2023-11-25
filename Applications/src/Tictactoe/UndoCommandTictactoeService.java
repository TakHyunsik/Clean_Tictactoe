package Tictactoe;

import AppInterfaces.IUndoCommandService;
import Repository.ICommandRepository;

public class UndoCommandTictactoeService implements IUndoCommandService {
	ICommandRepository repo;

	public UndoCommandTictactoeService(ICommandRepository repo) {
		this.repo = repo;
	}

	/**
	 * �ֱ� �� ��ɾ� �ΰ��� �ڷ� ������.
	 * 
	 * @throws ���� undo���� throw�� �߻��ϴ� ���, check_undoable�� Ȯ�� ���ϰ� ����� ���
	 */
	@Override
	public void undo() throws Exception {
		if (!check_undoable()) {
			throw new Exception("UndoCommandTictactoeService undo Exception");
		}
		for (int i = 0; i < 2; i++) {
			repo.pop_command().ifPresent(cmd -> {
				try {
					cmd.undo();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public boolean check_undoable() {
		return repo.get_size() >= 2;
	}

}
