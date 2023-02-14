import javax.xml.bind.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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

public class ej4{

    public static void main(String[] args) {
        try {

            Artropodo artoprodo = new Artropodo(1,"artropodo1",4.2);
            JAXBContext contexto = JAXBContext.newInstance(artropodo.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(artropodo, System.out);

        } 
        catch (PropertyException e) {e.printStackTrace();} 
        catch (JAXBException e) {e.printStackTrace();}

        try {

            JAXBContext context = JAXBContext.newInstance(Artropodo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Artropodo artropdo = (Artropodo)unmarshaller.unmarshal(new File("artropdos.xml") );
            System.out.println(artropdo.getCodigo());
            System.out.println(artropdo.getNombre());
            System.out.println(artropdo.getPeso());

        } 
        catch (JAXBException e) {e.printStackTrace();}
}
}