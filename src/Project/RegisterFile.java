package Project;
import java.util.*;

public class RegisterFile {
	
	byte registers[];
	int pc;
	byte statusRegister;
	
	public RegisterFile()
	{
		registers = new byte[64];
		pc = 0;
		statusRegister = 0;
	}

}
