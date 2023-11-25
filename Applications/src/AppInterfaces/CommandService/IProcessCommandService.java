package AppInterfaces.CommandService;

public interface IProcessCommandService {
	/**
	 * cmd.execute() �����ؼ� ���� ������ ��ɾ� ����� �ϰ�, ������ ������ ������� �ʴ´�.
	 * 
	 * @param cmd
	 * @return cmd.execute()�� ����� ��ȯ�Ѵ�.
	 */
	public boolean process(ICommand cmd);
}