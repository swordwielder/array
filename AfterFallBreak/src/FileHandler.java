/*
 *
 * @author Serge
 *
*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class FileHandler
{
	private File file;
	private Writer writer;
	private BufferedReader reader;

	public FileHandler(String pathname)
	{
		file = new File(pathname);
		if(! file.exists())
		{
			try
			{
				new FileOutputStream(pathname);
			}
			catch(IOException e)
			{
				System.err.println(e);
				return;
			}
		}
		writer = null;
		reader = null;
	}

	public void write(String contents, boolean append)
	{
		try
		{
			if(writer == null)
			{
				writer = new BufferedWriter(new FileWriter(file, append));
			}
			writer.write(contents);
			writer.flush();
		}
		catch (IOException e)
		{
			System.err.println(e);
			return;
		}
	}

	public void closeWriter()
	{
		try
		{ 
			writer.close();
			writer = null;
		}
		catch(IOException e)
		{
			System.err.println(e);
			return;
		}
		catch(NullPointerException e)
		{
			System.err.println(e);
			return;
		}
	}

	public String readLine()
	{
		String line = new String();
		try
		{
			if(reader == null)
			{
				reader = new BufferedReader(new FileReader(file));
			}
			line = reader.readLine();
			return line;
		}
		catch (IOException e)
		{
			System.err.println(e);
			return "";
		}
	}

	public void closeReader()
	{
		try
		{ 
			reader.close();
			reader = null;
		}
		catch(IOException e)
		{
			System.err.println(e);
			return;
		}
		catch(NullPointerException e)
		{
			System.err.println(e);
			return;
		}
	}
	
	public static void main(String[] args)
	{
		FileHandler fh = new FileHandler("new_file.txt");
		fh.write("hello world! 1\n", false);
		fh.write("hello world! 2\n", false);
		fh.closeWriter();
		System.out.println(fh.readLine());
		System.out.println(fh.readLine());
		fh.closeReader();
		
	}	
}
