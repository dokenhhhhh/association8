package itf;

import java.util.List;

import model.ErrorType;
import model.Space;
import util.BaseException;

public interface ISpaceControl {

	ErrorType addSpace(String spaceName) throws BaseException;
	
	ErrorType changeSpace(Space space,String spaceName) throws BaseException;
	
	ErrorType changeSpaceState(Space space) throws BaseException;
	
	ErrorType deleteSpace(Space space) throws BaseException;
	
	List<Space> loadSpace() throws BaseException;
}
