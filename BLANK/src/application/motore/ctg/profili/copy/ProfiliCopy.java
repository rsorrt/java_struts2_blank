package application.motore.ctg.profili.copy;
/*
 * @(#) racfProfili.cob 04/11/2010 10:18:15
 *
 * File generato da cobol2java 1.20
 */

import it.sgsbpvn.common.host.CCIRecord;
import it.sgsbpvn.common.host.COBOLRecord;
import it.sgsbpvn.common.host.RecordConversionException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class ProfiliCopy extends COBOLRecord implements CCIRecord
{
	public ProfiliCopy() throws UnsupportedEncodingException
	{
		super(COBOLRecord.EBCDIC, 32);
		//setRecordName("");
		initialize(length(), " ", true);
	}

	public void read(InputStream input) throws IOException
	{
		int bytes = input.available();
		if (bytes > 0)
		{
			byte[] buffer = new byte[bytes];
			input.read(buffer);
			setBytes(buffer);
		}
		else
		{
			setBytes(null);
		}
	}

	public void write(OutputStream output) throws IOException
	{
		byte[] buffer = getBytes();
		if (buffer != null)
		{
			output.write(buffer);
			output.flush();
		}
	}

	public void setNome_profilo(String nome_profilo) throws RecordConversionException
	{
		toBytes(nome_profilo, 0, 8, false);
	}

	public String getNome_profilo() throws RecordConversionException
	{
		return toString(0, 8);
	}

	public void setLog_message(int log_message) throws RecordConversionException
	{
		toBytes(log_message, 8, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}

	public int getLog_message() throws RecordConversionException
	{
		return toInt(8, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}

	public void setAut_read(int aut_read) throws RecordConversionException
	{
		toBytes(aut_read, 16, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}

	public int getAut_read() throws RecordConversionException
	{
		return toInt(16, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}

	public void setAut_update(int aut_update) throws RecordConversionException
	{
		toBytes(aut_update, 24, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}

	public int getAut_update() throws RecordConversionException
	{
		return toInt(24, 8, COBOLRecord.COBOL_DISPLAY_NUMERIC, 8, true);
	}
}
