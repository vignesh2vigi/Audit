package com.audit_vw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.audit_vw.model.Vmlogin;
import com.audit_vw.service.VmService;

@Controller
public class VmController {
	@Autowired
	private VmService vmService;
	
	
	@RequestMapping(value = "/insertdealerinfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<?> insertdealerinfo(@RequestBody Vmlogin vmlogin) {
		System.out.println("user register");
		
		Vmlogin dealerRegLoginObj = vmService.dealerinfo(vmlogin);
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	@RequestMapping(value = "/addlead", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> loglast(@RequestParam("file") MultipartFile file,HttpServletRequest request,
				HttpServletResponse response,
				javax.servlet.http.HttpSession session) {
			
		ArrayList<Hashtable<String, String>> al = new ArrayList<Hashtable<String, String>>();

		Map<String, String> mp = new HashMap<String, String>();
		System.out.println("============="+file);

		try {
			
			HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
			System.out.println("2========="+file.getInputStream());
			HSSFSheet sh = (HSSFSheet) workbook.getSheet("Sheet1");
			Iterator<HSSFRow> rowitreator = sh.rowIterator();
			int rowcount = 0;

			while (rowitreator.hasNext()) {

				String vin_no = "";

				String reg_no = "";

				


				HSSFRow row = rowitreator.next();

				Iterator<HSSFCell> cellIterator = row.cellIterator();

				int colcount = 0;

				Hashtable<String, String> ht = new Hashtable<String, String>();

				while (cellIterator.hasNext()) {

					HSSFCell cell = cellIterator.next();
					String value = "";
					switch (cell.getCellType()) {

					case HSSFCell.CELL_TYPE_NUMERIC:

						String val = "";

						if ((int) cell.getNumericCellValue() == 0) {

							val = "0";

						} else {

							val = new java.text.DecimalFormat("0").format(cell
									.getNumericCellValue());
						}
						String column = mp.get(colcount + "");
						
						break;

					case HSSFCell.CELL_TYPE_STRING:

						if (cell.getStringCellValue().toString() == null
								|| cell.getStringCellValue().toString() == ""
								|| cell.getStringCellValue().toString().trim()
										.length() == 0) {

							value = "NA";

						} else {

							value = cell.getStringCellValue().toString();

						}

						if (rowcount != 0) {

							column = mp.get(colcount + "");

							if (column.trim().equals("vin_no")) {
								vin_no = value;
							}

							else if (column.trim().equals("reg_no")) {
								reg_no = value;
							}
							
						
						}

						break;

					}

					if (rowcount == 0) {

						mp.put(colcount + "", cell.getStringCellValue()
								.toString().trim());

					}

					colcount += 1;

				}

				if (rowcount != 0) {

					ht.put("vin_no", vin_no );

					ht.put("reg_no", reg_no);

				
					
					al.add(ht);

				}

				rowcount = rowcount + 1;

				

			}
			}
			catch (Exception e){
				System.out.println("erooorrrr=="+e);
				
			}
		    
		try {
			

			
			int i =0;			

			for(int it=0;it<(al.size());it++){
			
				Hashtable<String, String> h=(Hashtable<String, String>)al.get(it);
			 
			
				String vin_no=h.get("vin_no");
		   
				String reg_no=h.get("reg_no");
		    
	
			System.out.println("leadid============="+vin_no);
			System.out.println("clientid==========="+reg_no);
			
			
     String query1="insert into stock_info(vin_no,reg_no)values('"+vin_no+"','"+reg_no+"')";

			System.out.println("query====="+query1);

				
			

				
			}
		


		}catch(Exception e){
			
			System.out.println(e);
			System.out.println("final========================="+e);
			
		}
			return new ResponseEntity<Vmlogin>(HttpStatus.OK);
		}
}
