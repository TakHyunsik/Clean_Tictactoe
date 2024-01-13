package AppInterfaces.CommandService;

public interface ICommand {
	/**
	 * 정해진 명령을 실행한다.
	 * 
	 * @throws check_executable()이 거짓인 경우에 실행될 경우 throws를 일으킨다. 이것은 큰 오류이다.
	 * @return 명령 자체가 실패할 경우 거짓를 반환한다. 성공하는 모든경우 참을 반환한다.
	 */
	public void execute() throws Exception;

	/**
	 * execute의 반대되는 명령을 실행한다.
	 * 
	 * @throws execute의 값이 false인데도 실행된 경우 throws를 일으킨다. 이것은 큰 오류이다.
	 */
	public void undo() throws Exception;

	/**
	 * 명령어의 성공과 실패를 반환한다.
	 * 
	 * @return
	 */
	public boolean check_executable();

	String ToSting();
}
