package Project;
import java.io.File;
import java.io.IOException;
import java.util.*;

import view.Window;

public class Main {
	
	public static String BinaryInstruction(short i)
	{
		String res = Integer.toBinaryString(i);
		String ret = "";
		if(res.length() > 16)
			ret = res.substring(16);
		else {
			while(res.length()<16)
				res = "0"+res;
			ret = res;
		}
		return ret;
		
		
	}
	
	public static void main(String[]args) throws IOException
	{
		
		InstructionMemory im = new InstructionMemory();
		
		DataMemory dm = new DataMemory();
		
		ALU alu = new ALU();
		
		RegisterFile rf = new RegisterFile();
		
		MicroArcheticture mac = new MicroArcheticture(im,dm,rf,alu);
		
		//CodeParser cp = new CodeParser();
		
		//cp.parse(new File("test.txt"), im);
		
		//Window w = new Window();
		
		//mac.runPipelining();
		
		Window w = new Window();
		
		
		
		
	}

}
