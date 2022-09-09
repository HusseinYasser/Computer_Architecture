package Project;
import java.util.*;

public class InstructionMemory
{
	
	public short instructions[];
	public int lastInstruction;
	public int count;
	
	public InstructionMemory()
	{
		instructions = new short[1024];
		lastInstruction = 0;
		count = 0;
	}
	
	
	
}