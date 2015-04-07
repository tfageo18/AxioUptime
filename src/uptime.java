import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class uptime {
	public static void main(String[] args) throws Exception {
		JFrame cadre 		= new javax.swing.JFrame("Uptime de ma machine");
		JPanel panneau 		= new JPanel();
		JLabel texteHaut 	= new JLabel();
		JLabel texteBas 	= new JLabel();
		JLabel dateUptime	= new JLabel();
		JLabel dateDuJour 	= new JLabel();
		
		texteHaut.setText("Date du jour : ");
		texteBas.setText("Uptime de la machine : ");
		dateUptime.setText(getSystemUptime());
		dateDuJour.setText(getTodayDate());
		
		panneau.setPreferredSize(new Dimension(250, 80));
		panneau.setBackground(Color.WHITE);
		
		cadre.setContentPane(panneau);
		cadre.setLocation(400, 300);
		cadre.pack();
		cadre.setVisible(true);
		cadre.add(texteHaut);
		cadre.add(dateDuJour);
		cadre.add(texteBas);
		cadre.add(dateUptime);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static String getSystemUptime() throws Exception {
		String uptime = null;
	    String os = System.getProperty("os.name").toLowerCase();
	    if (os.contains("win")) {
	    	Process uptimeProc 	= Runtime.getRuntime().exec("net stats srv");
	        BufferedReader in 	= new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
	        String line;
	        
	        while ((line = in.readLine()) != null) {
	        	//System.out.println(line);
	        	if (line.startsWith("Statistiques depuis")) {
	            	if (uptime == null) {
	            		uptime = line;
	            	} else {
	            		uptime += line;
	            	}
	            }
	        }
	    } else {
	    	uptime = "OS non pris en charge";
	    }
	    	
		return uptime;
	}
	
	public static String getTodayDate() throws Exception {
		DateFormat df 		= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date today 			= Calendar.getInstance().getTime();  
		String reportDate 	= df.format(today);
		return reportDate;
	}
}