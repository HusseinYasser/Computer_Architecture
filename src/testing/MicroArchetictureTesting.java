package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Project.ALU;
import Project.CodeParser;
import Project.DataMemory;
import Project.InstructionMemory;
import Project.MicroArcheticture;
import Project.RegisterFile;

public class MicroArchetictureTesting {
	
	@Test
	void testt1()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (0B0000000000000000);
        short out = mac.getField1(instruction);
        assertEquals(0 , out);
        instruction = 0;
        out = mac.getField1(instruction);
        assertEquals(0 , out);
        
	}
	
	@Test
	void testt2()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (0B0001000000001111);
        short out = mac.getField1(instruction);
        assertEquals(0 , out);
        instruction = 4111;
        out = mac.getField1(instruction);
        assertEquals(0 , out);
        
	}
	
	@Test
	void testt3()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1111000000000000);
        short out = mac.getField1(instruction);
        assertEquals(0 , out);
        instruction = -4096;
        out = mac.getField1(instruction);
        assertEquals(0 , out);
        
	}
	
	@Test
	void testt4()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B0001000001100001);
        short out = mac.getField1(instruction);
        assertEquals(1 , out);
        instruction = 4193;
        out = mac.getField1(instruction);
        assertEquals(1 , out);
        
	}
	
	@Test
	void testt5()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B0011000011111111);
        short out = mac.getField1(instruction);
        assertEquals(3 , out);
        instruction = 12543;
        out = mac.getField1(instruction);
        assertEquals(3 , out);
        
	}
	
	@Test
	void testt6()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B0111001010111000);
        short out = mac.getField1(instruction);
        assertEquals(10 , out);
        instruction = 29368;
        out = mac.getField1(instruction);
        assertEquals(10 , out);
        
	}
	
	@Test
	void testt7()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B0011010011110000);
        short out = mac.getField1(instruction);
        assertEquals(19 , out);
        instruction = 13552;
        out = mac.getField1(instruction);
        assertEquals(19 , out);
        
	}
	
	@Test
	void testt8()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1001010110111000);
        short out = mac.getField1(instruction);
        assertEquals(22 , out);
        instruction = -27208;
        out = mac.getField1(instruction);
        assertEquals(22 , out);
        
	}
	
	@Test
	void testt9()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1001011111111111);
        short out = mac.getField1(instruction);
        assertEquals(31 , out);
        instruction = -26625;
        out = mac.getField1(instruction);
        assertEquals(31 , out);
        
	}
	
	@Test
	void testt10()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1010101000001111);
        short out = mac.getField1(instruction);
        assertEquals(40 , out);
        instruction = -22001;
        out = mac.getField1(instruction);
        assertEquals(40 , out);
        
	}
	
	@Test
	void testt11()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1100111011000001);
        short out = mac.getField1(instruction);
        assertEquals(59 , out);
        instruction = -12607;
        out = mac.getField1(instruction);
        assertEquals(59 , out);
        
	}
	
	@Test
	void testt12()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1110111100000000);
        short out = mac.getField1(instruction);
        assertEquals(60 , out);
        instruction = -4352;
        out = mac.getField1(instruction);
        assertEquals(60 , out);
        
	}
	
	@Test
	void testt13()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B0000111111000011);
        short out = mac.getField1(instruction);
        assertEquals(63 , out);
        instruction = 4035;
        out = mac.getField1(instruction);
        assertEquals(63 , out);
        
	}
	
	@Test
	void testt14()
	{
		InstructionMemory im = new InstructionMemory();
		DataMemory dm = new DataMemory();
		RegisterFile rf = new RegisterFile();
		ALU alu = new ALU();
        MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
        
        short instruction = (short) (0B1111111111111111);
        short out = mac.getField1(instruction);
        assertEquals(63 , out);
        instruction = -1;
        out = mac.getField1(instruction);
        assertEquals(63 , out);
        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void test1()
	{
		 InstructionMemory im = new InstructionMemory();
		 DataMemory dm = new DataMemory();
		 RegisterFile rf = new RegisterFile();
		 ALU alu = new ALU();
         MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
		
		 short instruction = (short) (0B1100111000000101);
		 short res = mac.getField2(instruction);
		 assertEquals(5,res);
	}
	
	@Test
	void test2()
	{
		 InstructionMemory im = new InstructionMemory();
		 DataMemory dm = new DataMemory();
		 RegisterFile rf = new RegisterFile();
		 ALU alu = new ALU();
         MicroArcheticture mac = new MicroArcheticture(im, dm, rf, alu);
		
		 short instruction = (short) (0B1001001100000110);
		 short res = mac.getField2(instruction);
		 assertEquals(6,res);
	}
	
	

}
