import java.text.SimpleDateFormat;
import java.util.Date;

public class validacion {
	
	public boolean isNumeric(String cadena){
		try {
			double na=Double.parseDouble(cadena);

		} catch (NumberFormatException na){
			return false;
		}
		return true;
	}
	
	 public boolean isDate(String fechax) {
	        try {
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	            Date fecha = formatoFecha.parse(fechax);
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
	}
}
