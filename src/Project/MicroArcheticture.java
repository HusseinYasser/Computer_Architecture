package Project;

import java.util.*;

class Pair
{
	String str;
	boolean take;
	
	Pair(String str, boolean take)
	{
		this.str = str;
		this.take = take;
	}
}

public class MicroArcheticture {

	InstructionMemory im;

	DataMemory dm;

	RegisterFile rf;

	ALU alu;

	Instruction curr;

	HashSet<Byte> Rinstr;

	public MicroArcheticture(InstructionMemory im, DataMemory dm, RegisterFile rf, ALU alu) {

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

	public static byte getOpcode(short instruction) {

		String tmp = Main.BinaryInstruction(instruction);
		byte res = 0;
		for (int i = 0; i < 4; ++i) {
			if (tmp.charAt(i) == '1')
				res += (1 << (4 - i - 1));
		}
		return res;

	}

	public static short getField1(short instruction) {
		/*
		 * String tmp = (Integer.toBinaryString(instruction)); tmp =
		 * Main.BinaryInstruction(instruction); short res = 0; int cnt = 0; for(int i =
		 * 4; i < 10; ++i) { if(tmp.charAt(i) == '1') res += (1<<(5-cnt) ); cnt++; }
		 * 
		 * return res;
		 */
		instruction &= (0B0000111111000000);
		instruction = (short) (instruction >>> 6);
		return instruction;

	}

	public static short getField2(short instruction) {
		/*
		 * String tmp = (Integer.toBinaryString(instruction)); tmp =
		 * Main.BinaryInstruction(instruction); short res = 0; int cnt = 0; for(int i =
		 * 10; i < 16; ++i) { if(tmp.charAt(i) == '1') res += (1<<(5-cnt) ); cnt++; }
		 * 
		 * return res;
		 */
		instruction &= (short) (0B0000000000111111);
		return instruction;

	}

	public static short getImmediate(short instruction) {

		if ((instruction & (1 << 5)) == 0) // Positive
			instruction &= (short) (0B0000000000111111);
		else // negative
			instruction |= (short) (0B1111111111000000);
		return instruction;
	}

	public short fetch() {
		System.out.println("Instruction " + rf.pc + " is getting fetched!");
		return im.instructions[rf.pc++];
	}

	public Instruction decode(short instruction) {
		// decoding the opcode
		short tmp = instruction;
		byte opcode = (byte) getOpcode(tmp);

		// Getting source1 and destination at the same time
		tmp = instruction;
		short src1 = getField1(tmp);
		short dst = src1;

		// Detect R-type instruction or I-type
		char type = ' ';
		if (Rinstr.contains(opcode) == true)
			type = 'R';
		else
			type = 'I';

		// getting field 2;
		short src2 = -1, immediate = -1;
		tmp = instruction;
		short field2 = getField2(tmp); // absolute value of any thing put in the field2 part
		tmp = instruction;
		if (type == 'R')
			src2 = field2;
		else {
			if (opcode == 3 || opcode == 5) {
				immediate = getImmediate(tmp);
			}
			else
				immediate = field2;
		}

		return new Instruction(opcode, src1, src2, dst, immediate, type);
	}

	public Pair execute(Instruction instruction) {
		System.out.println("(*)Execution Description: ");
		String res = "(*)Execution Description: \n";

		boolean taken = false;
		switch (instruction.opcode) {
		case 0:
			System.out.println("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ rf.registers[instruction.src2]);
			res += "The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ rf.registers[instruction.src2] + "\n";
			rf.registers[instruction.dst] = alu.ADD(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst] + "\n";
			break;

		case 1:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.SUB(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " "
					+ rf.registers[instruction.dst] + "\n";
			break;

		case 2:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.MUL(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " "
					+ rf.registers[instruction.dst] + "\n";
			break;

		case 3:
			rf.registers[instruction.dst] = (byte) instruction.immediate;
			System.out.println("The Immediate to be moved : " + instruction.immediate + " and it will be moved to R"
					+ instruction.dst);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The Immediate to be moved : " + instruction.immediate + " and it will be moved to R"
					+ instruction.dst + "\n" + "The New Value of R" + instruction.dst + " "
					+ rf.registers[instruction.dst] + "\n";
			break;
//-->
		case 4:
			int oldpc = rf.pc - 2;
			//rf.pc += alu.BEQZ(rf.registers[instruction.src1], (byte) instruction.immediate);
			int jump = alu.BEQZ(rf.registers[instruction.src1], (byte) instruction.immediate);
			if(alu.BEQZ(rf.registers[instruction.src1], (byte) instruction.immediate) != 0)
				taken = true;
			System.out.println("The Input is : R" + instruction.src1
					+ " and if equal to zero the jump on the pc willl be : " + instruction.immediate);
			System.out.println("The new value of the PC : " + rf.pc + " and "
					+ ((oldpc == rf.pc) ? "No branch happened" : "a branch happened"));
			res += "The Input is : R" + instruction.src1 + " and if equal to zero the jump on the pc willl be : "
					+ instruction.immediate + "\n" + "The new value of the PC : " + (rf.pc +(int)((taken==true)? (-2+jump):0))+" and "
					+ ((taken == false) ? "No branch happened" : "a branch happened") + "\n";
			break;

		case 5:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ instruction.immediate);
			rf.registers[instruction.dst] = alu.ANDI(rf.registers[instruction.src1], (byte) instruction.immediate);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res +=  "The New Value of R" + instruction.dst + " "
					+ rf.registers[instruction.dst] + "\n";
			break;

		case 6:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "R" + instruction.src2 + " : "
					+ rf.registers[instruction.src2]);
			rf.registers[instruction.dst] = alu.EOR(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " "
					+ rf.registers[instruction.dst] + "\n";
			break;

		case 7:
			System.out.println("The Inputs are : \n" + "R" + instruction.src1 + " : " + rf.registers[instruction.src1]
					+ "\n" + "R" + instruction.src2 + " : " + rf.registers[instruction.src2]);
			rf.pc = alu.BR(rf.registers[instruction.src1], rf.registers[instruction.src2]);
			System.out.println("The New Value of the PC is " + rf.pc);
			res += "The Inputs are : \n" + "R" + instruction.src1 + " : " + rf.registers[instruction.src1] + "\n" + "R"
					+ instruction.src2 + " : " + rf.registers[instruction.src2] + "\n" + "The New Value of the PC is "
					+ rf.pc + "\n";
			taken = true;
			break;

		case 8:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "Shift Amount Left: " + instruction.immediate);
			rf.registers[instruction.dst] = alu.SAL(rf.registers[instruction.src1], (byte) instruction.immediate);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst] + "\n";
			break;

		case 9:
			res+=("The Inputs are : \n" + "R" + instruction.src1 + "(Destination Register) : "
					+ rf.registers[instruction.src1] + "\n" + "Shift Amount Right: " + instruction.immediate);
			rf.registers[instruction.dst] = alu.SAR(rf.registers[instruction.src1], (byte) instruction.immediate);
			System.out.println("The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst]);
			res += "The New Value of R" + instruction.dst + " " + rf.registers[instruction.dst] + "\n";
			break;

		case 10:
			res+=("The Address from Memory to read from : " + instruction.src2 + "\n"
					+ "The Destination is R" + instruction.src1);
			rf.registers[instruction.src1] = dm.data[instruction.immediate];
			System.out.println("The New Value of R" + instruction.dst + " : " + rf.registers[instruction.dst]);
			res +="The New Value of R" + instruction.dst + " : "
					+ rf.registers[instruction.dst] + "\n";
			break;

		case 11:
			dm.data[instruction.src1] = (byte) instruction.immediate;
			System.out.println("The Address from Memory to write to : " + instruction.immediate + "\n"
					+ "The Value will be taken from R" + instruction.src1 + "\n" + "The new Value of Mem["
					+ instruction.immediate + "] : " + rf.registers[instruction.src1]);
			res += "The Address from Memory to write to : " + instruction.immediate + "\n"
					+ "The Value will be taken from R" + instruction.src1 + "\n" + "The new Value of Mem["
					+ instruction.immediate + "] : " + rf.registers[instruction.src1] + "\n";
			break;

		default:
		}

		// Updating Status Register

		// Updating Carry Signal
		if (instruction.opcode == 0) // ADD operation so update the Carry
		{
			if (updateCarry(rf.registers[instruction.src1], rf.registers[instruction.src2]))
				rf.statusRegister |= (1 << 4);
			else
				rf.statusRegister &= (0B11101111);
		}

		// Updating OverFlow flag
		// Only Addition Wrong
		if (instruction.opcode == 0 || instruction.opcode == 1) {
			if (updateOverflow(rf.registers[instruction.src1], rf.registers[instruction.src2], instruction.opcode))
				rf.statusRegister |= (1 << 3);
			else
				rf.statusRegister &= (0B11110111);
		}

		// Updating Negative flag; and updating also the Zero Flag
		if (instruction.opcode == 0 || instruction.opcode == 1 || instruction.opcode == 2 || instruction.opcode == 5
				|| instruction.opcode == 6 || instruction.opcode == 8 || instruction.opcode == 9) {
			if (rf.registers[instruction.dst] < 0)
				rf.statusRegister |= (1 << 2);
			else
				rf.statusRegister &= (0B11111011);

			if (rf.registers[instruction.dst] == 0)
				rf.statusRegister |= (1 << 0);
			else
				rf.statusRegister &= (0B11111110);
		}

		// Updating the S Flag
		if (instruction.opcode == 0 || instruction.opcode == 1) {
			int NBit = (rf.statusRegister & (1 << 2)) == 0 ? 0 : 1;
			int VBit = (rf.statusRegister & (1 << 3)) == 0 ? 0 : 1;
			if ((NBit ^ VBit) == 1)
				rf.statusRegister |= (1 << 1);
			else
				rf.statusRegister &= (0B11111101);
		}

		System.out.println("Status Register : " + rf.statusRegister);
		res += "Status Register : " + rf.statusRegister;

		return new Pair(res, taken);

	}

	public boolean updateCarry(byte x, byte y) {
		short res = (short) (x + y);
		return (res & (0B0000001000000000)) == 0 ? false : true;
	}

	public boolean updateOverflow(byte x, byte y, int flag) {
		short res = -1;
		if (flag == 0) {
			res = (short) (x + y);
		} else
			res = (short) (x - y);
		System.out.println(res);
		return (res < 128 && res >= -128) ? false : true;
	}

	public String runPipelining() {
		System.out.println(im.count);
		String res = "";
		int count = im.count; // number of instructions
		short tt = 0;
		short instrDecoded = 0; // instruction to be decoded
		boolean decoded = false; // Wether we can decode or not
		Instruction exec = null; // Instruction to be exeecuted
		boolean executed = false; // if yopu can execute or not
		Instruction tmp = null; // ??
		int counter = 0; // ??

		// Law 7asal jump aw branch l mfrod nbd2 pipelining mn awl we gded
		for (int i = 0; i < count + 2 && rf.pc <= im.lastInstruction; ++i) {
			int tobefetched = rf.pc;
			System.out.println("In Cycle " + (i + 1) + ":\n" + ((tobefetched  <= im.lastInstruction)? "Fetching : Instruction " + tobefetched + "\n":"")
					+ ((decoded == true) ? "Decoding : Instruction " + (tobefetched - 1) : "") + "\n"
					+ ((executed == true) ? "Executing : Instruction " + (tobefetched - 2) : ""));
			res += "In Cycle " + (i + 1) + ":\n" + "Fetching : Instruction " + tobefetched + "\n"
					+ ((decoded == true) ? "Decoding : Instruction " + (tobefetched - 1) : "") + "\n"
					+ ((executed == true) ? "Executing : Instruction " + (tobefetched - 2) : "");
			res += "\n";

			// fetch
			if (tobefetched >= 0 && tobefetched < im.lastInstruction) {
				tt = fetch(); // l instruction l et3mlha fetch
				counter++;
			}

			// decode
			if (decoded == true && i < count + 1) {
				tmp = decode(instrDecoded);
			}
			// execute
			if (executed == true) {
				int oldpc = tobefetched - 2;
				res += execute(exec);
			    if((exec.opcode == 4 || exec.opcode == 7) && rf.pc != tobefetched-1)
				{
				 counter = 0;
				}
				res += "\n";
			}
			instrDecoded = tt;
			exec = tmp;
			if (counter > 0)
				decoded = true;
			else
				decoded = false;
			if (counter > 1)
				executed = true;
			else
				executed = false;
			System.out.println("---------------------------------------------------------------");
			res += "---------------------------------------------------------------\n";
		}
		return res;
	}

	public String pipeline() {
		String res = "";
		int number = im.count;
		System.out.println(number);
		
		int clockCycles = 1;
		
		int tobefetched = rf.pc;
		boolean fetched = true;
		
		short tobedecoded = -1;
		boolean decoded = false;
		
		Instruction exec = null;
		boolean executed = false;
		
		int count = 0;
		short tt = -1;
		
		while(tobefetched - 2 < im.count)
		{
			
			res += "Clock Cycle : "+(clockCycles++) + "\n";
			
			tobefetched = rf.pc;
			
			
			if(tobefetched < im.count) {
				tt = fetch();
			}
			else {
				fetched = false;
				rf.pc++;
			}
			res += ((fetched == true)? "Fetching Instrucion"+tobefetched+"\n":"");
			res += ((decoded == true)? "Decoding Instruction"+(tobefetched-1)+"\n":"");
			res += ((executed == true)? "Executing Instruction"+(tobefetched-2)+"\n":"");
			Instruction tmp = null;
			if(decoded == true)
			{
				tmp = decode(tobedecoded);
			}
			if(executed == true)
			{
				Pair pp = execute(exec);
				res += pp.str+"\n";
				if(pp.take == true)
					count = -1;
			}
			
			count++;
			
			if(count > 0 && tobefetched  < im.count)
				decoded = true;
			else
				decoded = false;
			if(count > 1)
				executed = true;
			else
				executed = false;
			
			
			exec = tmp;
			tobedecoded = tt;
			
			tobefetched = rf.pc;
			
			res += "---------------------------------------------------------\n";
			
		}
		
		res += printRegisters();
		
		res += "---------------------------------------------------------\n";
		
		res+=printMemory();
		

		return res;
	}
	
	public String printRegisters()
	{
		String res = "Registers Content\n";
		for(int i = 0 ; i < 64 ; ++i)
		{
			res+=("R"+(i) +" : "+rf.registers[i]);
			res+="\n";
		}
		res += "PC "+(rf.pc-2)+"\n";
		res += "Status Register "+rf.statusRegister;
		res+="---------------------------------------------------------------\n";
		return res;
	}
	
	public String printMemory()
	{
		String res = "Memory Content\n";
		for(int i = 0 ; i < 2048 ; ++i)
		{
			res+=("MemoryCell"+i + " : "+dm.data[i]);
			res+="\n";
		}
		res+="---------------------------------------------------------------\n";
		return res;
	}

}
