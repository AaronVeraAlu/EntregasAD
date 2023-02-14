package org.example;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.DateCodec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MongoClient mongoClient = new MongoClient();
        Document doc = new Document();
        FindIterable findIterable = null;
        MongoCursor iter;
        ArrayList<Document> documents = new ArrayList<>();
        int opt = 0;
        int opt2 = 0;
        int opt3 = 0;
        String aux ="";
        float aux2 = 0;


        while(opt!= 3) {

            System.out.println("------MENU------\n 1.Biblioteca\n 2.Jugadores\n 3.Salir");
            opt = sc.nextInt();

            if (opt == 1){
                ConnectionString connectionString = new ConnectionString("mongodb://localhost/biblioteca");
                CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
                CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry);
                MongoClientSettings clientSettings = MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .codecRegistry(codecRegistry)
                        .build();
                try (com.mongodb.client.MongoClient client = MongoClients.create(clientSettings)) {
                    MongoDatabase db = client.getDatabase("biblioteca");
                    MongoCollection<Libro> libros = db.getCollection("biblioteca", Libro.class);
                    System.out.println("\nUsando biblioteca...\n");
                    do {
                        System.out.println("------MENU------\n 1.Nuevo libro\n 2.Mostrar todos los libros\n 3.Consulta con proyección\n 4.Salir");
                        opt2 = sc.nextInt();
                        sc.nextLine();

                        Libro libro = new Libro();
                        ArrayList<Libro> listaLibros;
                        switch (opt2) {
                            case 1:

                                System.out.println("Ttulo: ");
                                libro.setNombre(sc.nextLine());
                                System.out.println("Autor: ");
                                libro.setAutor(sc.nextLine());
                                System.out.println("Precio: ");
                                libro.setPrecio(sc.nextFloat());
                                sc.nextLine();
                                System.out.println("Para dejar de añadir genrós escribe exit");
                                while (!Objects.equals(aux, "exit")) {
                                    System.out.println("Añadir género: ");
                                    aux = sc.nextLine();
                                    if(!Objects.equals(aux, "exit"))
                                        libro.addGenero(aux);
                                }

                                libros.insertOne(libro);

                                break;
                            case 2:
                                listaLibros = libros.find().into(new ArrayList<Libro>());
                                muestraLibros(listaLibros);
                                listaLibros.clear();
                                break;

                            case 3:
                                System.out.println("Se realizará una consulta de todos los libros con un precio menor del solicitado");
                                System.out.println("Precio: ");
                                aux2 = sc.nextFloat();
                                sc.nextLine();
                                System.out.println("Seleccione el campo que desea ver(1.Nombre/2.Autor/3.Precio)");
                                opt3 = sc.nextInt();
                                sc.nextLine();
                                switch (opt3){
                                    case 1:
                                        listaLibros = libros.find(lt("precio",aux2)).projection(fields(excludeId(),include("nombre"))).into(new ArrayList<Libro>());
                                        muestraLibros(listaLibros);
                                        listaLibros.clear();
                                        break;
                                    case 2:
                                        listaLibros = libros.find(lt("precio",aux2)).projection(fields(excludeId(),include("autor"))).into(new ArrayList<Libro>());                                        muestraLibros(listaLibros);
                                        muestraLibros(listaLibros);
                                        listaLibros.clear();
                                        break;
                                    case 3:
                                        listaLibros = libros.find(lt("precio",aux2)).projection(fields(excludeId(),include("precio"))).into(new ArrayList<Libro>());                                        muestraLibros(listaLibros);
                                        muestraLibros(listaLibros);
                                        listaLibros.clear();
                                        break;
                                    default: break;
                                }
                                break;
                            case 4:
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Introduzca una opción válida");
                                break;
                        }


                    } while (opt2 != 4);
                }
            } else if (opt == 2) {
                MongoDatabase db = mongoClient.getDatabase("jugadores");
                System.out.println("\nUsando jugadores...\n");

                Jugador jugador = new Jugador();
                do {
                    System.out.println("------MENU------\n 1.Nuevo jugador\n 2.Insertar varios jugadores\n 3.Borrar un jugador\n 4.Buscar un jugador\n 5.Mostrar todos los jugadores\n 6.Modificar un jugador\n 7.Salir");
                    opt2 = sc.nextInt();
                    sc.nextLine();
                    switch (opt2){
                        case 1:
                            System.out.println("DNI: ");
                            jugador.setDni(sc.nextLine());
                            System.out.println("Nombre: ");
                            jugador.setNombre(sc.nextLine());
                            System.out.println("Dorsal: ");
                            jugador.setDorsal(sc.nextInt());
                            sc.nextLine();

                            doc.append("dni", jugador.getDni()).append("nombre",jugador.getNombre()).append("dorsal",jugador.getDorsal());
                            db.getCollection("jugadores").insertOne(doc);

                            break;
                        case 2:
                            while (!Objects.equals(aux, "-1")) {
                                System.out.println("Para terminar con la introducción de jugadores darle el valor -1 al DNI\n");
                                System.out.println("DNI: ");
                                aux = sc.nextLine();
                                if (!Objects.equals(aux, "-1")) {
                                    jugador.setDni(aux);
                                    System.out.println("Nombre: ");
                                    jugador.setNombre(sc.nextLine());
                                    System.out.println("Dorsal: ");
                                    jugador.setDorsal(sc.nextInt());
                                    sc.nextLine();

                                        Document docs = new Document().append("dni", jugador.getDni()).append("nombre", jugador.getNombre()).append("dorsal", jugador.getDorsal());
                                        documents.add(docs);
                                }
                            }

                            db.getCollection("jugadores").insertMany(documents);
                            break;

                        case 3:
                            System.out.println("Introduzca DNI del jugador que desea borrar: \n");
                            System.out.println("DNI: ");
                            db.getCollection("jugadores").deleteOne(new Document("dni",sc.nextLine()));

                            break;
                        case 4:

                            System.out.println(" 1.Buscar por DNI\n 2.Busqueda filtrada\n");
                            opt = sc.nextInt();
                            sc.nextLine();
                            if(opt == 1) {
                                System.out.println("Introduzca DNI del jugador que desea buscar: ");
                                System.out.println("DNI: ");
                                findIterable = db.getCollection("jugadores").find(new Document("dni", sc.nextLine())).limit(1);
                                iter = findIterable.iterator();
                                if (iter.hasNext()) {
                                    doc = (Document) iter.next();
                                    jugador = new Jugador(doc.getString("dni"), doc.getString("nombre"), doc.getInteger("dorsal"));
                                    System.out.println(jugador.toString());
                                }
                            }else if(opt == 2){
                                System.out.println(" 1.Filtrar por dorsal\n 2.Filtrar por nombre y dorsal");
                                opt3 = sc.nextInt();
                                sc.nextLine();
                                switch (opt3){
                                    case 1:
                                        System.out.println("Introduzca número de dorsal para filtrar");
                                        jugador.setDorsal(sc.nextInt());
                                        System.out.println("Dorsal (1.Mayor / 2.Menor / 3.Igual)");
                                        opt = sc.nextInt();
                                        sc.nextLine();
                                        switch (opt){
                                            case 1 :
                                                findIterable = db.getCollection("jugadores").find(gt("dorsal",jugador.getDorsal()));
                                                muestraJugadores(findIterable);
                                                break;
                                            case 2:
                                                findIterable = db.getCollection("jugadores").find(lt("dorsal",jugador.getDorsal()));
                                                muestraJugadores(findIterable);
                                                break;
                                            case 3:
                                                findIterable = db.getCollection("jugadores").find(eq("dorsal",jugador.getDorsal()));
                                                muestraJugadores(findIterable);
                                                break;
                                            default:


                                              break;
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Introduzca el nombre del jugador");
                                        jugador.setNombre(sc.nextLine());
                                        System.out.println("Introduzca número de dorsal para filtrar");
                                        jugador.setDorsal(sc.nextInt());
                                        sc.nextLine();
                                        System.out.println("Dorsal (1.Mayor / 2.Menor / 3.Igual)");
                                        opt3 = sc.nextInt();
                                        sc.nextLine();
                                        switch (opt3){
                                            case 1 :
                                                findIterable = db.getCollection("jugadores").find(and(gt("dorsal",jugador.getDorsal()),eq("nombre",jugador.getNombre())));
                                                muestraJugadores(findIterable);
                                                break;
                                            case 2:
                                                findIterable = db.getCollection("jugadores").find(and(lt("dorsal",jugador.getDorsal()),eq("nombre",jugador.getNombre())));
                                                muestraJugadores(findIterable);
                                                break;
                                            case 3:
                                                findIterable = db.getCollection("jugadores").find(and(eq("dorsal",jugador.getDorsal()),eq("nombre",jugador.getNombre())));
                                                muestraJugadores(findIterable);
                                                break;
                                            default:
                                                iter = findIterable.iterator();
                                                while(iter.hasNext()){
                                                    doc =(Document) iter.next();
                                                    jugador = new Jugador(doc.getString("dni"), doc.getString("nombre"), doc.getInteger("dorsal"));
                                                    System.out.println(jugador.toString());
                                                }
                                                break;
                                        }
                                        break;
                                    default:
                                        break;
                                }

                            }else
                                System.out.println("Introduzca una opción valida");
                            break;
                        case 5:

                            findIterable = db.getCollection("jugadores").find();
                            muestraJugadores(findIterable);
                            break;
                        case 6:

                            System.out.println("Introduzca DNI del jugador que desea modificar \n");
                            System.out.println("DNI: ");
                            aux = sc.nextLine();
                            jugador = new Jugador();
                            System.out.println("Modificar(1.Nombre/2.Dorsal/3.Ambos)");
                            opt3 = sc.nextInt();
                            sc.nextLine();
                            switch (opt3){
                                case 1:
                                    System.out.println("Nuevo nombre: ");
                                    jugador.setNombre(sc.nextLine());
                                    doc = new Document("$set", new Document("nombre", jugador.getNombre()));
                                    db.getCollection("jugadores").updateOne(new Document("dni", aux),doc);
                                    break;
                                case 2:
                                    System.out.println("Nuevo dorsal: ");
                                    jugador.setDorsal(sc.nextInt());
                                    sc.nextLine();
                                    doc = new Document("$set", new Document("dorsal", jugador.getDorsal()));
                                    db.getCollection("jugadores").updateOne(new Document("dni", aux),doc);
                                    break;
                                case 3:
                                    System.out.println("Nuevo nombre: ");
                                    jugador.setNombre(sc.nextLine());
                                    System.out.println("Nuevo dorsal: ");
                                    jugador.setDorsal(sc.nextInt());
                                    sc.nextLine();
                                    doc = new Document().append("dni",aux).append("nombre",jugador.getNombre()).append("dorsal",jugador.getDorsal());
                                    db.getCollection("jugadores").replaceOne(new Document("dni", aux),doc);
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 7:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Introduzca una opción válida");
                            break;
                            }

                }while(opt2!=7);
            }
            else
                System.out.println("Inserte una opción valida");
        }
        mongoClient.close();


    }

    private static void muestraJugadores(FindIterable findIterable) {
        MongoCursor iter;
        Document doc;
        iter = findIterable.iterator();
        Jugador jugador;
        while(iter.hasNext()){
            doc =(Document) iter.next();
            jugador = new Jugador(doc.getString("dni"), doc.getString("nombre"), doc.getInteger("dorsal"));
            System.out.println(jugador.toString());
        }
    }
    private static void muestraLibros(ArrayList<Libro> listaLibros){
        for (Libro l:listaLibros) {
            System.out.println(l.toString());
        }
    }
}

