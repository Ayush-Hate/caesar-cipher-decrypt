import java.io.*;

public class CaesarCipher {

	static int getCount() throws IOException //returns the length of file
	{
		int i=0;
		BufferedReader fileRead = new BufferedReader(new FileReader("1_cipher.txt"));
		while(fileRead.read()!=-1)//until EOF increment count
		{
			i++;
		}
		fileRead.close();
		return --i;//the file starts with a 'mark' character of '$'. So actual length of text is one less
}
	
	public static void main(String[] args) throws IOException {
		int shift,count;
		char content;
		String decryptString = "";
		count=getCount();
		System.out.println("CAESAR CIPHER ENCRYPTION DECRYPTION\n\nThis program will decrypt the contents of the file '1_cipher.txt' by BRUTE FORCE\n\n");
		BufferedReader fileIn = new BufferedReader(new FileReader("1_cipher.txt"));
		fileIn.mark(fileIn.read());//marks the start of the file. 
		for(shift=0;shift<=26;shift++)//check all possible shift values to generate all possible decryptions
		{
			System.out.println("\nContents of file ("+count+" characters):\n");
			while(count-->0)//run the decryption for each of the 'count' number of characters in the file
			{
				content = (char)fileIn.read();//get next character
				System.out.print(content);
				char x=content;
				if((content>='a' && content <= 'z'))//if lowercase
				{
					x=(char)(((byte)x-shift));//convert x to byte, apply shift then convert byte to character.
					if((byte)x<97)//if we go out of range for the ASCII values of 'a' and 'z' we wrap around
						x=(char)(122-(97-(byte)x-1));
						//x=(char)(((byte)x+26));
					decryptString+=x;//append character to the decrypted string
				}
				else if(content>='A' && content <= 'Z')//if uppercase handle like above
				{
					x=(char)(((byte)x-shift));
					if((byte)x<65)
						x=(char)(90-(65-(byte)x-1));
					decryptString+=x;
				}
				else//if space/newline/symbol etc just append to decrypted string.
				{
					decryptString+=x;
				}
			}
			count=getCount();//reset counter for next iteration
			fileIn.reset();//reset file pointer to the marked location (beginning of file)
			System.out.println("\n\nThe decrypted file with shift "+shift+" is:\n\n" +decryptString+"\n\n");
			decryptString = "";//reset decrypted string for next iteration.
		}
		fileIn.close();
	}

}
