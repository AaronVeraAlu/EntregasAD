
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.*;
import java.io.*;

class Artropodo {

    private int codigo;
    private String nombre;
    private double peso;

    public Artropodo(int codigo,String nombre, double peso) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.peso = peso;

    }

    public int getCodigo () { return codigo; }
    public void setCodigo (int codigo) { this.codigo = codigo; }
    public String getNombre () { return nombre; }
    public void setNombre (String nombre) { this.nombre = nombre; }
    public double getPeso () { return peso; }
    public void setPeso (double peso) { this.peso = peso; }

}

public class ej3{

    public static void main(String[] args) throws Exception{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);
        Element raiz = documento.createElement("artropodos");
        documento.getDocumentElement().appendChild(raiz);
        Element nodoArtropodo = null , nodoDatos = null ;
        Text texto = null;


        ArrayList<Artropodo> listaArtropodo = new ArrayList<>();
        listaArtropodo.add(new Artropodo(1, "Artropodo 1", 10.5));
        listaArtropodo.add(new Artropodo(2, "Artropodo 2", 15.1));
        listaArtropodo.add(new Artropodo(3, "Artropodo 3", 11.5));
        listaArtropodo.add(new Artropodo(4, "Artropodo 4", 50));
        listaArtropodo.add(new Artropodo(5, "Artropodo 5", 79.3));

        for (Artropodo artoprodo : listaArtropodo) {

            nodoArtropodo = documento.createElement("artropodo");raiz.appendChild(nodoArtropodo);
            nodoDatos = documento.createElement("codigo");
            nodoArtropodo.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(artoprodo.getCodigo()));
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("nombre");
            nodoArtropodo.appendChild(nodoDatos);
            texto = documento.createTextNode(artoprodo.getNombre());
            nodoDatos.appendChild(texto);
            nodoDatos = documento.createElement("peso");
            nodoArtropodo.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(artoprodo.getPeso()));
            nodoDatos.appendChild(texto);
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(new File("artoprodos.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);

        Document documentoLectura = builder.parse(new File("artoprodos.xml"));
        NodeList artropodos = documentoLectura.getElementsByTagName("artropodo");

        for (int i = 0; i < artropodos.getLength(); i++) {

            Node artoprodo = artropodos.item(i) ;
            Element elemento = ( Element ) artoprodo ;
            System.out.println(elemento.getElementsByTagName("codigo").item(0)
            .getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("nombre").item(0)
            .getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("peso").item(0)
            .getChildNodes().item(0).getNodeValue());
        }
        
    }

}