package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import Model.Department;
import Model.ExportData;
import Model.Order;
import Model.Product;

public class Processor {

	public static final String ORDER_FILE_PATH = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//orders.csv";
	public static final String DEPARTMENT_FILE_PATH = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//departments.csv";
	public static final String ORDER_PRODUCT_PRIOR_FILE_PATH = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//order_products__prior.csv";
	public static final String ORDER_PRODUCT_TRAIN_FILE_PATH = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//order_products_train.csv";
	public static final String PRODUCT_FILE_PATH = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//products.csv";
	public static final String RESULT_FILE = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//result.csv";
	public static final String REPORT_FILE = "C://Users//shivangi garg//Downloads//instacart_online_grocery_shopping_2017_05_01//instacart_2017_05_01//report.csv";

	public static Map<String, String> departmentMap = new HashMap<String, String>();

	public static Map<String, String> productDepartmentMap = new HashMap<String, String>();

	public static Map<String,List<Map<String,String>>> dowMap = new HashMap<String,List<Map<String,String>>>();
	public static void processOrders() {
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(ORDER_FILE_PATH), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvFileParser) {
				if (csvRecord.get(2).equalsIgnoreCase("prior")) {
					Order order = new Order(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
							csvRecord.get(4), csvRecord.get(5), csvRecord.get(6));
					List<String> productList = getAllProductsFromOrder(order.getOrder_id());
					for (String productid : productList) {
						String depname = productDepartmentMap.get(productid);
						writeFinalFile(order.getOrder_dow(), order.getOrder_hour_of_day(),order.getUser_id(),depname);
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static List<String> getAllProductsFromOrder(String orderid) {
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(ORDER_PRODUCT_PRIOR_FILE_PATH), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			List<String> productList = new ArrayList<String>();
			int start = 0;
			for (CSVRecord csvRecord : csvFileParser) {
				if (csvRecord.get(0).compareTo(orderid) == 0) {
					start = 1;
					productList.add(csvRecord.get(1));
				} else {
					if (start == 1)
						return productList;
				}
			}
			return productList;

		} catch (Exception ex) {
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}

	}

	public static void processDepartment() {
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(DEPARTMENT_FILE_PATH), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvFileParser) {
				Department department = new Department(csvRecord.get(0), csvRecord.get(1));
				departmentMap.put(department.getDepartment_id(), department.getDepartment());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void generateProductDepartmentMap() {
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(PRODUCT_FILE_PATH), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvFileParser) {
				Product product = new Product(csvRecord.get(0), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3));
				if (departmentMap.containsKey(product.getDepartment_id())) {
					String dname = departmentMap.get(product.getDepartment_id());
					productDepartmentMap.put(product.getProduct_id(), dname);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void writeFinalFile(String dow, String hod, String userid, String depname) throws IOException {

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(RESULT_FILE),StandardOpenOption.APPEND);
		//for unique user entries dow_hod_user_dep
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(RESULT_FILE), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvFileParser) {
				if(csvRecord.get(0).compareTo(dow)==0 && csvRecord.get(1).compareTo(hod)==0 && csvRecord.get(2).compareTo(userid)==0 && csvRecord.get(3).compareTo(depname)==0) {
					return;
				}
					
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			 writer = Files.newBufferedWriter(Paths.get(RESULT_FILE));

			CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader("DOW", "HOD", "UserId", "DepartmentName"));
			csvPrinter.printRecord(dow, hod, userid, depname);
			csvPrinter.flush();
		} catch (Exception ex) {

		}
	}
	
	public static void departmentCount() {
		Map<String,String> depRatingMap = new HashMap<String,String>();
			
		//List<Map<String,String>> hodList = new ArrayList<Map<String,String>>();
		
		
		
		
		Map<ExportData,Long> depCount = new HashMap<ExportData,Long>();
		try {

			CSVParser csvFileParser = CSVParser.parse(new File(RESULT_FILE), Charset.defaultCharset(),
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			for (CSVRecord csvRecord : csvFileParser) {
				String dow = csvRecord.get(0);
				String hod = csvRecord.get(1);
				String dep = csvRecord.get(3);
				ExportData expData = new ExportData(dow,hod,dep);
				
				if(depCount.containsKey(expData)) {
					Long count  = depCount.get(expData);
					count++;
					depCount.put(expData, count);
				}
			}
			
			for(Map.Entry<ExportData, Long> entry : depCount.entrySet()) {
			    ExportData key = entry.getKey();
			    Long value = entry.getValue();
			    if(dowMap.containsKey(key.getDow())) {
			    	List<Map<String,String>> hodList = dowMap.get(key.getDow());
			    	if(hodList == null || hodList.isEmpty()) {
			    		hodList = new ArrayList<Map<String,String>>(24);
			    	}
			    	Map<String,String> depCMap = hodList.get(Integer.parseInt(key.getHod()));
			    	Long allCount = 0L;
			    	for(Map.Entry<String,String> dep : depCMap.entrySet()) {
			    		allCount = allCount + Long.parseLong(dep.getValue());
			    	}
			    	for(Map.Entry<String,String> dep : depCMap.entrySet()) {
			    		String per = ((Double.parseDouble(dep.getValue()) / allCount) * 100) + "";
			    		dep.setValue(per);
			    	}
			    }
			}
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
	public static void writeReport() {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(REPORT_FILE));

			CSVPrinter csvPrinter = new CSVPrinter(writer,
					CSVFormat.DEFAULT.withHeader("DAY OF WEEK ", "HOUR OF DAY", "DEPARTMENT", "Rating"));
			for(Map.Entry<String,List<Map<String,String>>> entry : dowMap.entrySet()) {
	    		String dow = entry.getKey();
	    		List<Map<String,String>> hourList = entry.getValue();
	    		for(int i=0;i<24;i++) {
	    			String hod = i+"";
	    			Map<String,String> depMap = hourList.get(i);
	    			for(Map.Entry<String,String>  depEntry: depMap.entrySet()) {
	    				String depName = depEntry.getKey();
	    				String rating = depEntry.getValue();
	    				csvPrinter.printRecord(dow, hod, depName, rating);
	    			}
	    		}
	    	}
			csvPrinter.flush();
		} catch (Exception ex) {

		}
	}

}
