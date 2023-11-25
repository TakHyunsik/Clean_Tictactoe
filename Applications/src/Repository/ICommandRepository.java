package Repository;

import java.util.Optional;

import AppInterfaces.CommandService.ICommand;

public interface ICommandRepository {
	/**
	 * ���� �ֱ� ����� ��ȯ�� �ڿ� ��Ͽ��� �����մϴ�. check_empty�� ���� ����� null�� ���� �� �ֽ��ϴ�. �����
	 * check_empty()�� Ȯ�� ���ּ���. output.pop_command().ifPresent(cmd -> cmd.undo());
	 * ���·� ����� �ּ���.
	 * 
	 * @return check_empty()�� ���� null�� ���� �� �ֽ��ϴ�.
	 */
	public Optional<ICommand> pop_command();

	/**
	 * ���� �ֱ� ����� ��ȯ�մϴ�. check_empty�� ���� ����� null�� ���� �� �ֽ��ϴ�. ����� check_empty()�� Ȯ��
	 * ���ּ���. output.pop_command().ifPresent(cmd -> cmd.undo()); ���·� ����� �ּ���.
	 * 
	 * @return check_empty()�� ���� null�� ���� �� �ֽ��ϴ�.
	 */
	public Optional<ICommand> top_command();

	/**
	 * Stack ���·� �ֽ� ��ɾ ���� ���� ���� �� �ֵ��� �״´�.
	 * 
	 * @param cmd : ���డ���� ��ɾ �־���Ѵ�. �ֱ��� cmd.check_executable�� Ȯ���ϰ� ����.
	 */
	public void push_command(ICommand cmd);

	public boolean check_empty();

	public int get_size();
}
