package Tictactoe;

import AppInterfaces.IUndoCommandService;
import Repository.ICommandRepository;

public class UndoCommandTictactoeService implements IUndoCommandService {
	ICommandRepository repo;

	public UndoCommandTictactoeService(ICommandRepository repo) {
		this.repo = repo;
	}

	/**
	 * 최근 둔 명령어 두개를 뒤로 돌린다.
	 * 
	 * @throws 쌓인 undo에서 throw가 발생하는 경우, check_undoable를 확인 한하고 사용한 경우
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
