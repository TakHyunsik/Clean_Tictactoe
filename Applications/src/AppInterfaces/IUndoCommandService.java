package AppInterfaces;

public interface IUndoCommandService {
	/**
	 * is_undoable() 참인 경우 : 게임의 정책에 따라서 최근 명령부터 cmd.undo()를 실행한다. 거짓인 경우: 아무것도 안한다.
	 * 
	 * @throws 실행하는 명령어를 undo 하는 명령어가 throws를 일으키면 함수도 같이 undo를 발생시킨다.
	 */
	public void undo() throws Exception;

	/**
	 * undo를 사용가능한지 상태를 반환한다.
	 * 
	 * @return undo를 실행가능한지 여부를 반환한다.
	 */
	public boolean check_undoable();
}
