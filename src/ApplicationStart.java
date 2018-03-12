import java.io.File;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Model.Order;

public class ApplicationStart {
	
	public static void main(String s[]) {
		 //startup things
		//Could have used builder to do this.
		 Util.Processor.processDepartment();	
		 Util.Processor.generateProductDepartmentMap();
		 Util.Processor.processOrders();
		 Util.Processor.departmentCount();
		 Util.Processor.writeReport();
	}
}
