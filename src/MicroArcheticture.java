import java.util.*;

public class MicroArcheticture {
	
	InstructionMemory im;
	
	DataMemory dm;
	
	RegisterFile rf;
	
	ALU alu;
	
	Instruction curr;
	
	HashSet<Byte>Rinstr;
	
	
	public MicroArcheticture(InstructionMemory im , DataMemory dm , RegisterFile rf, ALU alu)
	{
		
		this.im = im;
		
		this.dm = dm;
		
		this.rf = rf;
		
		this.alu = alu;
		
		Rinstr = new HashSet<Byte>();
		Rinstr.add((byte) 0);
		Rinstr.add((byte) 1);
		Rinstr.add((byte) 2);
		Rinstr.add((byte) 6);
		Rinstr.add((byte) 7);
		
	}
	
	public static byte getOpcode(short instruction)
	{
		
		
		String tmp = Main.BinaryInstruction(instruction);
		byte res = 0;
		for(int i = 0; i < 4; ++i)
		{
			if(tmp.charAt(i) == '1')
			res += (1<<(4-i-1) );
		}
		return res;
		
		
	}
	
	public static short getField1(short instruction)
	{
		/*String tmp = (Integer.toBinaryString(instruction));
		tmp = Main.BinaryInstruction(instruction);
		short res = 0;
		int cnt = 0;
		for(int i = 4; i < 10; ++i)
		{
			if(tmp.charAt(i) == '1')
			res += (1<<(5-cnt) );
			cnt++;
		}
		
		return res;
		*/
		instruction &= (0B0000111111000000);
		instruction = (short) (instruction >>> 6);
		return instruction;
	
		
	}
	
	public static short getField2(short instruction)
	{
		/*String tmp = (Integer.toBinaryString(instruction));
		tmp = Main.BinaryInstruction(instruction);
		short res = 0;
		int cnt = 0;
		for(int i = 10; i < 16; ++i)
		{
			if(tmp.charAt(i) == '1')
			res += (1<<(5-cnt) );
			cnt++;
		}
		
		return res;
		*/
		instruction &= (short)(0B0000000000111111);
		return instruction;
		
	}
	
	public static short getImmediate(short instruction)
	{
		
		if((instruction & (1<<5)) == 0) //Positive
			instruction &= (short)(0B0000000000111111);
		else //negative
			instruction |= (short)(0B1111111111000000);
		return instruction;
	}
	
	public short fetch()
	{
		System.out.println("Instruction "+rf.pc + " is getting fetched!");
		return im.instructions[rf.pc++];
	}
	
	public Instruction decode(short instruction)
	{
		//decoding the opcode
		short tmp = instruction;
		byte opcode = (byte)getOpcode(tmp);
		
		//Getting source1 and destination at the same time
		tmp = instruction;
		short src1 = getField1(tmp);
		short dst = src1;
		
		//Detect R-type instruction or I-type
		char type = ' ';
		if(Rinstr.contains(opcode) == true)
			type = 'R';
		else
			type = 'I';
		
		//getting field 2;
		short src2 = -1,immediate = -1;
		tmp = instruction;
		short field2 = getField2(tmp); //absolute value of any thing put in the field2 part
		tmp = instruction;
		if(type == 'R')
			src2 = field2;
		else {
			if(opcode == 3 || opcode == 5) {
				immediate = getImmediate(tmp);
			}
				
			else
				immediate = field2;
		}
			

		return new Instruction(opcode,src1,src2,dst,immediate,type);
	}
	
	public void execute(Instruction instruction)
	{
		
		switch(instruction.opcode)
		{
			case 0: 
			System.out.println("The Inputs are : \n"
					+ "R"+instruction.src1 + "(Destination Register) : " + rf.registers[instruction.src1]+"\n"
					+ "R"+instruction.src2 + " : "+rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.ADD(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of R"+instruction.dst +" "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 1: 
				System.out.println("The Inputs are : \n"
						+ "R"+instruction.src1 + "(Destination Register) : "+rf.registers[instruction.src1]+"\n"
								+ "R"+instruction.src2 + " : "+rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.SUB(rf.registers[instruction.src1] , rf.registers[instruction.src2]);
			System.out.println("The New Value of R"+instruction.dst + " "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 2: System.out.println("The Inputs are : \n"
					+ "R"+instruction.src1 + "(Destination Register) : "+rf.registers[instruction.src1]+"\n"
					+ "R"+instruction.src2 + " : "+rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.MUL(rf.registers[instruction.src1] , rf.registers[instruction.src2]);
			System.out.println("The New Value of R"+instruction.dst + " "+ rf.registers[instruction.dst]);
			break;
			
			
			
			case 3: rf.registers[instruction.dst] = (byte) instruction.immediate; 
			System.out.println("The Immediate to be moved : "+instruction.immediate + " and it will be moved to R"+instruction.dst);
			System.out.println("The New Value of R"+instruction.dst+" "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 4:int oldpc = rf.pc;
			rf.pc += alu.BEQZ(rf.registers[instruction.src1] , (byte)instruction.immediate);
			System.out.println("The Input is : R"+instruction.src1 + " and if equal to zero the jump on the pc willl be : "+instruction.immediate);
			System.out.println("The new value of the PC : "+rf.pc +" and " + ((oldpc == rf.pc)? "No branch happened":"a branch happened"));
			break;
			
			
			
			case 5: System.out.println("The Inputs are : \n"
					+ "R"+instruction.src1 + "(Destination Register) : "+rf.registers[instruction.src1]+"\n"
					+ "R"+instruction.src2 + " : "+rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.ANDI(rf.registers[instruction.src1] , (byte)instruction.immediate);
			System.out.println("The New Value of R"+instruction.dst + " "+rf.registers[instruction.dst]);
			break;
			
			case 6: System.out.println("The Inputs are : \n"
					+ "R"+instruction.src1 + "(Destination Register) : "+rf.registers[instruction.src1]+"\n"
					+ "R"+instruction.src2 + " : "+rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.EOR(rf.registers[instruction.src1] , rf.registers[instruction.src2]);
			System.out.println("The New Value of R"+instruction.dst + " "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 7: break;
			
			
			
			case 8:
				System.out.println("The Inputs are : \n"
						+ "R"+instruction.src1+"(Destination Register) : "+rf.registers[instruction.src1]+"\n"
								+ "Shift Amount Left: "+instruction.immediate);
			rf.registers[instruction.dst] = alu.SAL(rf.registers[instruction.src1] , (byte)instruction.immediate);
			System.out.println("The New Value of R"+instruction.dst + " "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 9:
				System.out.println("The Inputs are : \n"
						+ "R"+instruction.src1+"(Destination Register) : "+rf.registers[instruction.src1]+"\n"
								+ "Shift Amount Right: "+instruction.immediate);
			rf.registers[instruction.dst] = alu.SAL(rf.registers[instruction.src1] , (byte)instruction.immediate);
			System.out.println("The New Value of R"+instruction.dst + " "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 10: System.out.println("The Address from Memory to read from : "+instruction.immediate+"\n"
					+ "The Destination is R"+instruction.src1);
			rf.registers[instruction.dst] = dm.data[instruction.src2];
			System.out.println("The New Value of R"+instruction.dst+" : "+rf.registers[instruction.dst]);
			break;
			
			
			
			case 11: dm.data[instruction.immediate] = rf.registers[instruction.src1];
			System.out.println("The Address from Memory to write to : "+instruction.immediate+"\n"
					+ "The Value will be taken from R"+instruction.src1+"\n"
							+ "The new Value of Mem["+instruction.immediate+"] : "+rf.registers[instruction.src1]);
			break;
			
			
			
			default:
		}
		
		//Updating Status Register
		
		//Updating Carry Signal
		if(instruction.opcode == 0) //ADD operation so update the Carry
		{
			if(updateCarry(rf.registers[instruction.src1] , rf.registers[instruction.src2]))
				rf.statusRegister |= (1<<4);
			else
				rf.statusRegister &= (0B11101111);
		}
		
		//Updating OverFlow flag
		if(instruction.opcode == 0 || instruction.opcode == 1)
		{
			if(updateOverflow(rf.registers[instruction.src1] , rf.registers[instruction.src2]))
				rf.statusRegister |= (1<<3);
			else
				rf.statusRegister &= (0B11110111);
		}
		
		//Updating Negative flag; and updating also the Zero Flag
		if(instruction.opcode == 0 || instruction.opcode == 1 || instruction.opcode == 2
				||instruction.opcode == 5 || instruction.opcode == 6 || instruction.opcode == 8
				|| instruction.opcode == 9)
		{
			if(rf.registers[instruction.dst] < 0)
				rf.statusRegister |= (1<<2);
			else
				rf.statusRegister &= (0B11111011);
			
			if(rf.registers[instruction.dst] == 0)
				rf.statusRegister |= (1<<0);
			else
				rf.statusRegister &= (0B11111110);
		}
		
		//Updating the S Flag
		if(instruction.opcode == 0 || instruction.opcode == 1) {
			int NBit = (rf.statusRegister & (1<<2)) == 0 ? 0 : 1;
			int VBit = (rf.statusRegister & (1<<3)) == 0 ? 0 : 1;
			if((NBit^VBit) == 1)
				rf.statusRegister |= (1<<1);
			else
				rf.statusRegister &= (0B11111101);
		}
		
		System.out.println("Status Register : "+rf.statusRegister);
		
	}
	
	public boolean updateCarry(byte x, byte y)
	{
		int carry = 0;
		for(int i = 0 ; i < 8 ; ++i)
		{
			int bit1 = ((x&(1<<i)) == 0)? 0:1;
			int bit2 = ((y&(1<<i)) == 0)? 0:1;
			int check = bit1+bit2+carry;
			if(check > 1)
				carry = 1;
		}
		return carry == 1 ? true:false;
	}
	
	public boolean updateOverflow(byte x, byte y)
	{
		int carry2 = 0, carry = 0;
		for(int i = 0 ; i < 8 ; ++i)
		{
			int bit1 = ((x&(1<<i)) == 0)? 0:1;
			int bit2 = ((y&(1<<i)) == 0)? 0:1;
			int check = bit1+bit2+carry;
			if(check > 1)
				carry = 1;
			if(i == 6)
				carry2 = carry;
		}
		return (carry2^carry) == 1 ? true:false;
	}
	
	public void run()
	{
		for(int i = 0 ; i < im.count ; ++i)
		{
			short instr = fetch();
			Instruction b = decode(instr);
			System.out.println(b.opcode + " "+ b.src1 + " " + b.src2 + " " + b.immediate + " " + b.type);
			execute(b);
			System.out.println("-------------------------------------");
			if(rf.pc>=im.count)
				break;
		}
	}
	
	

}
