package eventos.servicio;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eventos.modelo.PuntoInteres;

public class ServicioPuntoInteres implements IServicioPuntoInteres {

	 private static final String GEO_NAMES_USERNAME = "aadd"; // Cambia este nombre de usuario si tienes uno propio
	    private static final String GEO_NAMES_URL_TEMPLATE = "http://api.geonames.org/findNearbyWikipedia?username=%s&lang=ES&lat=%f&lng=%f";

	    public List<PuntoInteres> puntosCercanos(double latitud, double longitud) throws Exception {
	        String urlStr = String.format(GEO_NAMES_URL_TEMPLATE, GEO_NAMES_USERNAME, latitud, longitud);
	        URL url = new URL("http://api.geonames.org/findNearbyWikipedia?&username=aadd&lang=ES&lat=37.984123153048316&lng=-1.1291804565199284");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        List<PuntoInteres> puntosDeInteres = new ArrayList<>();
	        try (InputStream inputStream = connection.getInputStream()) {
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(inputStream);
	            document.getDocumentElement().normalize();
	            System.out.println(document.toString());

	            NodeList entryList = document.getElementsByTagName("entry");
	            for (int i = 0; i < entryList.getLength(); i++) {
	                Node node = entryList.item(i);
	                if (node.getNodeType() == Node.ELEMENT_NODE) {
	                    Element element = (Element) node;
	                    String title = element.getElementsByTagName("title").item(0).getTextContent();
	                    String summary = element.getElementsByTagName("summary").item(0).getTextContent();
	                    double lat = Double.parseDouble(element.getElementsByTagName("lat").item(0).getTextContent());
	                    double lng = Double.parseDouble(element.getElementsByTagName("lng").item(0).getTextContent());

	                    PuntoInteres puntoInteres = new PuntoInteres(title, summary, 1000, "http://.........");
	                    puntosDeInteres.add(puntoInteres);
	                }
	            }
	        }
	        return puntosDeInteres;
	    }
	

}
