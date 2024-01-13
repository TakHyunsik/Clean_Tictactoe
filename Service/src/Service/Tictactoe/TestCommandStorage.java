package Tictactoe;

import java.util.Optional;
import java.util.Stack;

import AppInterfaces.CommandService.ICommand;
import Repository.ICommandRepository;

public class TestCommandStorage implements ICommandRepository {
	Stack<ICommand> storage;

	public TestCommandStorage(Iterable<ICommand> cmds) {
		storage = new Stack<ICommand>();
		if (cmds != null) {
			for (ICommand cmd : cmds) {
				storage.push(cmd);
			}
		}
	}

	@Override
	public Optional<ICommand> pop_command() {
		// ���� ��� Empty�� ��ȯ
		if (get_size() <= 0) {
			return Optional.empty();
		}
		// �ݵ�� ���� �־�� �Ѵ�.
		return Optional.of(storage.pop());
	}

	@Override
	public Optional<ICommand> top_command() {
		// ���� ��� Empty�� ��ȯ
		if (get_size() <= 0) {
			return Optional.empty();
		}
		// �ݵ�� ���� �־�� �Ѵ�.
		return Optional.of(storage.peek());
		// TODO Auto-generated method stub
	}

	@Override
	public void push_command(ICommand cmd) {
		storage.push(cmd);
	}

	@Override
	public boolean check_empty() {
		// TODO Auto-generated method stub
		return storage.empty();
	}

	@Override
	public int get_size() {
		// TODO Auto-generated method stub
		return storage.size();
	}

}
