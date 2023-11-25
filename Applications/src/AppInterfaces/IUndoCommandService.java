package AppInterfaces;

public interface IUndoCommandService {
	/**
	 * is_undoable() ���� ��� : ������ ��å�� ���� �ֱ� ��ɺ��� cmd.undo()�� �����Ѵ�. ������ ���: �ƹ��͵� ���Ѵ�.
	 * 
	 * @throws �����ϴ� ��ɾ undo �ϴ� ��ɾ throws�� ����Ű�� �Լ��� ���� undo�� �߻���Ų��.
	 */
	public void undo() throws Exception;

	/**
	 * undo�� ��밡������ ���¸� ��ȯ�Ѵ�.
	 * 
	 * @return undo�� ���డ������ ���θ� ��ȯ�Ѵ�.
	 */
	public boolean check_undoable();
}
