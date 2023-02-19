package org.example;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int opt = 0;
        Scanner sc = new Scanner(System.in);
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();


        do{
            System.out.println("---MENU---\n 1.Insercción de datos en la BD\n 2.Listado de todos los datos\n 3.Busqueda filtrada\n 4.Busqueda filtrada 2\n 5.Modificar Dirección\n 6.Salir");
            opt = sc.nextInt();
            sc.nextLine();

            switch(opt){
                case 1:

                    Direccion dir1 = new Direccion("calle","FrayLuis22");
                    Direccion dir2 = new Direccion("Av","JoseFrancisco");
                    Direccion dir3 = new Direccion("calle","Dahellos");

                    Persona autor1 = new Autor();
                    autor1.setNombre("Jose");
                    ((Autor) autor1).setNacionalidad("Española");
                    autor1.setDireccion(dir1);
                    Persona autor2 = new Autor();
                    autor2.setNombre("Ricar");
                    ((Autor) autor2).setNacionalidad("PR");
                    autor2.setDireccion(dir2);
                    Persona autor3 = new Autor();
                    autor3.setNombre("Mari");
                    ((Autor) autor3).setNacionalidad("Brazil");
                    autor3.setDireccion(dir3);

                    s.persist(autor1);
                    s.persist(autor2);
                    s.persist(autor3);

                    s.persist(new Libro("Tierra", 12,(Autor) autor1));
                    s.persist(new Libro("Agua", 13, (Autor) autor2));
                    s.persist(new Libro("Fuego", 22,(Autor) autor2));
                    s.persist(new Libro("Viento", 8,(Autor) autor3));
                    s.persist(new Libro("Montaña", 18,(Autor) autor1));

                    t.commit();

                    break;
                case 2:

                    String sql = "from Persona ";
                    Query q = s.createQuery(sql);
                    List res = q.getResultList();
                    System.out.println("Autores---");

                    for(Object a:res){
                        Autor aut = (Autor) a;
                        System.out.println(aut.toString());
                    }

                    sql = "from Libro ";
                    q = s.createQuery(sql);
                    res = q.getResultList();
                    System.out.println("Libros---");

                    for(Object a:res){
                        Libro libro = (Libro) a;
                        System.out.println(libro.toString());;
                    }

                    sql = "from Direccion ";
                    q = s.createQuery(sql);
                    res = q.getResultList();
                    System.out.println("Direcciones---");

                    for(Object a:res){
                        Direccion dir = (Direccion) a;
                        System.out.println(dir.toString());
                    }

                    break;
                case 3:
                    System.out.println("Vamos a mostrar por pantalla el nombre de un libro y su precio ordenado ASC, filtrado por el nombre de su autor");
                    System.out.println("Inserte el nombre: ");
                    String aux = sc.nextLine();

                    String hql = "from Libro where autor.nombre = :nombre_autor order by precio asc ";
                    Query q2= s.createQuery(hql);
                    q2.setParameter("nombre_autor",aux);
                    Iterator it = q2.getResultList().iterator();

                    while (it.hasNext()){
                        Libro libro = (Libro) it.next();

                        System.out.println("Nombre: " + libro.getTitulo() + "  Precio: " + libro.getPrecio());
                    }
                    break;
                case 4:
                    System.out.println("Vamos a mostrar por pantalla la suma de los precios de los libros escritos por un autor de una nacionalidad en especifico");
                    System.out.println("Inserte nacionalidad: ");
                    aux = sc.nextLine();

                    hql = "select SUM(l.precio) from Libro l where autor.nacionalidad = :nacionalidad_autor";
                    q2= s.createQuery(hql);
                    q2.setParameter("nacionalidad_autor",aux);
                    System.out.println("La suma de los precios es: " + (long) q2.getSingleResult());

                    break;
                case 5:

                    sql = "from Direccion ";
                    q = s.createQuery(sql);
                    res = q.getResultList();
                    System.out.println("Direcciones---");

                    for(Object a:res){
                        Direccion dir = (Direccion) a;
                        System.out.println(dir.toString());
                    }

                    System.out.println("Seleccione la id de la dirección a modificar: ");
                    int opt2 = sc.nextInt();
                    sc.nextLine();

                    sql = "from Direccion where id =:_id";
                    q = s.createQuery(sql);
                    q.setParameter("_id",opt2);
                    Direccion dir = (Direccion) q.getSingleResult();

                    System.out.println("Nuevo nombre: ");
                    dir.setNombreCalle(sc.nextLine());
                    System.out.println("Nuevo tipo de via: ");
                    dir.setTipoVia(sc.nextLine());
                    dir.setTipoVia("");

                    s.update(dir);
                    t.commit();

                    break;
                case 6:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Introduce una opción valida");
                    break;
            }
        }while(opt != 6);
        s.close();
    }
}