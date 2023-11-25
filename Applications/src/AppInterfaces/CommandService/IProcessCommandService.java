package AppInterfaces.CommandService;

public interface IProcessCommandService {
	/**
	 * cmd.execute() 실행해서 참이 나오면 명령어 기록을 하고, 거짓이 나오면 기록하지 않는다.
	 * 
	 * @param cmd
	 * @return cmd.execute()의 결과를 반환한다.
	 */
	public boolean process(ICommand cmd);
}