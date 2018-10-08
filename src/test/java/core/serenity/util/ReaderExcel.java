package core.serenity.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import core.serenity.models.Person;

public class ReaderExcel{

	private HSSFWorkbook workbook;
	private FileInputStream fileInput;
	private FileOutputStream fileOutput;
	
	public ReaderExcel()throws Exception{
		fileInput = new FileInputStream(new File("C:/Users/ariel.arnedo/Documents/Resources/Documents/reto3_screnplay.xls"));
		workbook = new HSSFWorkbook(fileInput);   
	}
	
	
	public HSSFWorkbook getWorkbook() {
		return workbook;
	}
	
	public void close() throws IOException {
		fileInput.close();
	}
	
	public List<Person> getData(){
		List<Person> list = new ArrayList();
		Sheet sheet = workbook.getSheetAt(0);
		
		 Iterator<Row> iterator = sheet.iterator();
	     
	     while (iterator.hasNext()) {
	         Row row = iterator.next();
	         Iterator<Cell> cells = row.cellIterator();
	         
	         Person person = new Person();
	         int i = 0;
	         while (cells.hasNext()) {
	             Cell cell = cells.next();
	
	             if(i == 0) {
	            	 person.setName(cell.getStringCellValue());
	             }else if(i == 1){
	            	 person.setEmail(cell.getStringCellValue());
	             }else if(i == 2){
	            	 person.setSubject(cell.getStringCellValue());
	             }else {
	            	 person.setNote(cell.getStringCellValue());
	             }
	             i++;
	         }
	         
	         list.add(person);
	     }    	
	     return list;
	}  
}
