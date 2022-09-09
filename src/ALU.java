import java.util.*;

public class ALU {
	
	public ALU() {
		
	}
	
	public byte ADD(byte x, byte y)
	{
		return (byte) (x+y);
	}
	
	public byte SUB(byte x, byte y)
	{
		return (byte) (x-y);
	}
	
	public byte MUL(byte x, byte y)
	{
		return (byte) (x*y);
	}
	
	public byte BEQZ(byte val , byte imm)
	{
		return (val == 0)? imm:0;
	}
	
	public byte ANDI(byte src1, byte src2)
	{
		return (byte) (src1&src2);
	}
	
	public byte EOR(byte x, byte y)
	{
		return (byte) (x|y);
	}
	
	public byte SAL(byte x,byte shift)
	{
		return (byte) (x<<shift);
	}
	
	public byte SAR(byte x, byte shift)
	{
		return (byte) (x>>shift);
	}
	
	

}
