package AppInterfaces.CommandService;

public interface ICommand {
	/**
	 * ������ ����� �����Ѵ�.
	 * 
	 * @throws check_executable()�� ������ ��쿡 ����� ��� throws�� ����Ų��. �̰��� ū �����̴�.
	 * @return ��� ��ü�� ������ ��� ������ ��ȯ�Ѵ�. �����ϴ� ����� ���� ��ȯ�Ѵ�.
	 */
	public void execute() throws Exception;

	/**
	 * execute�� �ݴ�Ǵ� ����� �����Ѵ�.
	 * 
	 * @throws execute�� ���� false�ε��� ����� ��� throws�� ����Ų��. �̰��� ū �����̴�.
	 */
	public void undo() throws Exception;

	/**
	 * ��ɾ��� ������ ���и� ��ȯ�Ѵ�.
	 * 
	 * @return
	 */
	public boolean check_executable();

	String ToSting();
}
