package Repository;

import java.util.Optional;

import AppInterfaces.CommandService.ICommand;

public interface ICommandRepository {
	/**
	 * 가장 최근 명령을 반환한 뒤에 기록에서 제거합니다. check_empty에 따라 결과가 null이 나올 수 있습니다. 사용전
	 * check_empty()로 확인 해주세요. output.pop_command().ifPresent(cmd -> cmd.undo());
	 * 형태로 사용해 주세요.
	 * 
	 * @return check_empty()에 따라 null이 나올 수 있습니다.
	 */
	public Optional<ICommand> pop_command();

	/**
	 * 가장 최근 명령을 반환합니다. check_empty에 따라 결과가 null이 나올 수 있습니다. 사용전 check_empty()로 확인
	 * 해주세요. output.pop_command().ifPresent(cmd -> cmd.undo()); 형태로 사용해 주세요.
	 * 
	 * @return check_empty()에 따라 null이 나올 수 있습니다.
	 */
	public Optional<ICommand> top_command();

	/**
	 * Stack 형태로 최신 명령어가 가장 먼저 나올 수 있도록 쌓는다.
	 * 
	 * @param cmd : 실행가능한 명령어를 넣어야한다. 넣기전 cmd.check_executable로 확인하고 넣자.
	 */
	public void push_command(ICommand cmd);

	public boolean check_empty();

	public int get_size();
}
