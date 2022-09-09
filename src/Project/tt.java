package Project;

public class tt {
	
	public static void main(String [] args)
	{
		
        InstructionMemory im = new InstructionMemory();
		
		DataMemory dm = new DataMemory();
		
		ALU alu = new ALU();
		
		RegisterFile rf = new RegisterFile();
		
		MicroArcheticture mac = new MicroArcheticture(im,dm,rf,alu);
		
		CodeParser cp = new CodeParser();
		
		System.out.println(mac.updateOverflow((byte)-128,(byte) 7,0));
		
	}

}
