package com.audit.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.audit.model.Vmlogin;
import com.audit.service.VmService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Controller
public class VmController {
	@Autowired
	private VmService vmService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@RequestMapping(value = "/insertdealerinfo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Vmlogin> insertdealerinfo(@RequestBody Vmlogin vmlogin,HttpServletRequest request) {
		System.out.println("vm register");
		
		Vmlogin dealerRegLoginObj = vmService.dealerinfo(vmlogin);
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/assignid/{audit_id}", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Vmlogin> assignid(@PathVariable String audit_id,HttpSession session) {
		System.out.println("vm register");
		Vmlogin dealerRegLoginObj =vmService.assigninfo(audit_id);
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Vmlogin> assign(@RequestBody Vmlogin vmlogin,HttpSession session) {
		System.out.println("vm register");
		Vmlogin dealerRegLoginObj = new Vmlogin();
		
	int sno=(int)session.getAttribute("sno");
	vmlogin.setAssign_by(sno);
		 vmService.assign(vmlogin);
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadStatement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vmlogin> uploadStatement(
			HttpServletRequest request) {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
		int day = now.get(Calendar.DAY_OF_MONTH);

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println("isMultipart: " + isMultipart);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.getMessage();
		}
		Iterator<?> iterator = items.iterator();
		int i = 1;
		String imageurl = "";
		while (iterator.hasNext()) {
			JSONObject obj = new JSONObject();
			try {
				FileItem item = (FileItem) iterator.next();
				if (!item.isFormField()) {
					String fileName = item.getName();
					// System.out.println("fileName--->"+fileName);
					DiskFileItem fileItem = (DiskFileItem) item;

					Map<String, String> configr = new HashMap<String, String>();
					// ----- For Production cloudinary add by 11-12-2017----- //
					configr.put("cloud_name", "eimagecloud");
					configr.put("api_key", "621264646611695");
					configr.put("api_secret", "n9vJwzoUhGPUjTJDzsYvCJYdOew");

					Cloudinary cloudinary = new Cloudinary(configr);
					String imagePath = fileItem.getStoreLocation().toString()
							.replace("\\", "//");

					// System.out.println("imagePath----->"+imagePath);
					@SuppressWarnings("rawtypes")
					Map params = ObjectUtils.asMap("folder", "Kuwy/" + year
							+ "/" + month + "/" + day, "public_id",
							fileName.substring(0, fileName.lastIndexOf('.')),
							"resource_type", "auto");
					// fileName.substring(0, fileName.lastIndexOf('.'))
					JSONObject result = (JSONObject) cloudinary.uploader()
							.upload(imagePath, params);
					String image = (String) result.get("url");
					System.out.println(fileItem.getFieldName() + " - " + image);

					// obj.put("url"+i, image);
					imageurl += image + ",";
					// imageurl.add(obj);

					i++;
				} else {
					System.out.println("not filepart");
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			System.out.println("imageURL =====" + imageurl);
		}

		if (imageurl.endsWith(",")) {
			imageurl = imageurl.substring(0, imageurl.length() - 1);
		}

		Vmlogin vdoutObj = new Vmlogin();
		/*vdoutObj.setStatus(true);*/
		vdoutObj.setAuth_letter(imageurl);
		return new ResponseEntity<Vmlogin>(vdoutObj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addlead", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> addlead(@RequestParam("file") MultipartFile file,HttpServletRequest request,
				HttpServletResponse response,
				javax.servlet.http.HttpSession session) {
			
		ArrayList<Hashtable<String, String>> al = new ArrayList<Hashtable<String, String>>();

		Map<String, String> mp = new HashMap<String, String>();
		System.out.println("============="+file);
		int insertDealerReg_int = 0;
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
			
			Vmlogin user2 = new Vmlogin();
			try {
				
				
				List<Vmlogin> audit_vw = new ArrayList<Vmlogin>(); 
				
				String query = "SELECT MAX(sno) AS sno FROM dealer_info"; 
				
				audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Vmlogin.class)); 
				if (audit_vw.size() > 0) {
					user2.setStatus(true);
					user2.setSno(audit_vw.get(0).getSno());
					
					
					/*userOutObj.setLoginfo(user2);*/
					} else { 
						
						user2.setStatus(false);
						/*userOutObj.setLoginfo(user2);*/
						}
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	try {
				
				
				List<Vmlogin> audit_vw = new ArrayList<Vmlogin>(); 
				
				String query = "SELECT audit_id FROM dealer_info where sno='"+user2.getSno()+"'"; 
				
				audit_vw = getJdbcTemplate().query(query, new BeanPropertyRowMapper(Vmlogin.class)); 
				if (audit_vw.size() > 0) {
					user2.setStatus(true);
					user2.setAudit_id(audit_vw.get(0).getAudit_id());
					
					
					/*userOutObj.setLoginfo(user2);*/
					} else { 
						
						user2.setStatus(false);
						/*userOutObj.setLoginfo(user2);*/
						}
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     String query1="insert into stock_info(dealer_mapped,audit_id,vin_no,reg_no,stock_dt)values(?,?,?,?,now())";

			System.out.println("query====="+query1);
			insertDealerReg_int = this.jdbcTemplate.update(
					query1,
					new Object[] {
							user2.getSno(),
							user2.getAudit_id(),
							vin_no,
							reg_no
							});
			
	}
		
		}catch(Exception e){
			
			System.out.println(e);
			System.out.println("final========================="+e);
			
		}
			return new ResponseEntity<Vmlogin>(HttpStatus.OK);
		}
	
	
	
	@RequestMapping(value = "/getdetails", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Vmlogin> getdetails() {
		System.out.println("vm details");
		
		Vmlogin dealerRegLoginObj = vmService.details();
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	@RequestMapping(value = "/stockup", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Vmlogin> stockup(@RequestBody Vmlogin vmlogin) {
		System.out.println("vm register");
		
		Vmlogin dealerRegLoginObj = vmService.stockup(vmlogin);
		return new ResponseEntity<Vmlogin>(dealerRegLoginObj,HttpStatus.OK);
	}
	@RequestMapping(value="/stocklist/{sno}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> stocklist(@PathVariable int sno,HttpSession session){
		
		 List<Vmlogin> list=vmService.stocklist(sno);
		 return new ResponseEntity<List<Vmlogin>>(list,HttpStatus.OK);
}
	@RequestMapping(value="/dealerlist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dealerlist(HttpSession session){
		
		 List<Vmlogin> list=vmService.dealerlist();
		 return new ResponseEntity<List<Vmlogin>>(list,HttpStatus.OK);
}
	@RequestMapping(value="/assigndealerlist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> assigndealerlist(HttpSession session){
		
		 List<Vmlogin> list=vmService.assigndealerlist();
		 return new ResponseEntity<List<Vmlogin>>(list,HttpStatus.OK);
}
	@RequestMapping(value="/completedealerlist",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> completedealerlist(HttpSession session){
		
		 List<Vmlogin> list=vmService.completedealerlist();
		 return new ResponseEntity<List<Vmlogin>>(list,HttpStatus.OK);
}
}
