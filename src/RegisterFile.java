import java.util.*;

public class RegisterFile {
	
	byte registers[];
	byte pc;
	byte statusRegister;
	
	public RegisterFile()
	{
		registers = new byte[64];
		pc = 0;
		statusRegister = 0;
	}

}
