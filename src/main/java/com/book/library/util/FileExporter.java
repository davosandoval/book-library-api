package com.book.library.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.book.library.dto.BookDTO;

public class FileExporter {
	private static final Logger LOG = LoggerFactory.getLogger(FileExporter.class);
	private static final String SHEET_NAME = "Books";
	private static final String HEADER_BOOK_AUTHOR = "Author";
	private static final String HEADER_BOOK_TITLE = "Title";
	private static final String HEADER_BOOK_FAMILY = "Book family";
	
	public static ByteArrayInputStream booksToExcelFile(List<BookDTO> bookDTOs) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet(SHEET_NAME);
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        Cell cell = row.createCell(0);
	        cell.setCellValue(HEADER_BOOK_TITLE);
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue(HEADER_BOOK_AUTHOR);
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(2);
	        cell.setCellValue(HEADER_BOOK_FAMILY);
	        cell.setCellStyle(headerCellStyle);
	        
	        for(int i=0; i< bookDTOs.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(bookDTOs.get(i).getTitle());
	        	dataRow.createCell(1).setCellValue(bookDTOs.get(i).getAuthor());
	        	dataRow.createCell(2).setCellValue(bookDTOs.get(i).getFamily());
	        }
	        
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException e) {
			LOG.error("Error processing excel file..." , e);
			return null;
		}
	}
	
	public static ByteArrayInputStream booksToTxtFile(List<BookDTO> bookDTOs) {
		StringBuilder stringBuilder = new StringBuilder(100);
		stringBuilder.append(HEADER_BOOK_TITLE).append(",").append(HEADER_BOOK_AUTHOR).append(",").append(HEADER_BOOK_FAMILY).append("\r\n");
		for(BookDTO dto: bookDTOs) {
			stringBuilder.append(dto.getTitle()).append(",").append(dto.getAuthor()).append(",").append(dto.getFamily()).append("\r\n");
		}
		return new ByteArrayInputStream(stringBuilder.toString().getBytes());
	}

}
