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

	private static final String GEO_NAMES_USERNAME = "aadd";
	private static final String GEO_NAMES_URL = "http://api.geonames.org/findNearbyWikipedia?&lang=ES&username=";

	public List<PuntoInteres> puntosCercanos(double latitud, double longitud) throws Exception {
		String url = GEO_NAMES_URL + GEO_NAMES_USERNAME + "&lat=" + latitud + "&lng=" + longitud;
		URL urlPI = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) urlPI.openConnection();
		connection.setRequestMethod("GET");

		List<PuntoInteres> puntosDeInteres = new ArrayList<>();
		try (InputStream inputStream = connection.getInputStream()) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			document.getDocumentElement().normalize();
			NodeList entryList = document.getElementsByTagName("entry");
			for (int i = 0; i < entryList.getLength(); i++) {
				Node node = entryList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String title = element.getElementsByTagName("title").item(0).getTextContent();
					String summary = element.getElementsByTagName("summary").item(0).getTextContent();
					double distance = Double
							.parseDouble(element.getElementsByTagName("distance").item(0).getTextContent());
					String urlWiki = element.getElementsByTagName("wikipediaUrl").item(0).getTextContent();

					PuntoInteres puntoInteres = new PuntoInteres(title, summary, distance, urlWiki);
					puntosDeInteres.add(puntoInteres);
				}
			}
		}
		return puntosDeInteres;
	}

}
