import java.util.*;

public class Instruction {
	
	byte opcode;
	short src1 , src2;
	short dst;
	short immediate;
	char type;
	
	
	public Instruction(byte opcode , short src1,short src2 , short dst, short immediate , char type)
	{
		this.opcode = opcode;
		this.src1 = src1;
		this.src2 = src2;
		this.dst = dst;
		this.immediate = immediate;
		this.type = type;
	}

}
