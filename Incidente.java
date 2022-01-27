import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Incidente {
   private int id;
   private LocalDate fecha;
   private String descripcion;
   private String nombreReporta;
   private String accionesTomadas;
   private boolean levantado;
   private String estatus;
   
   //Metodo constructor 
   public Incidente(int id,LocalDate fecha, String descripcion, String nombreReporta, String accionesTomadas, boolean levantado, String estatus){
      setId(id);
      setFecha(fecha);
      setDescripcion(descripcion);
      setNombreReporta(nombreReporta);
      setAccionesTomadas(accionesTomadas);
      setLevantado(levantado);
      setEstatus(estatus);
   }
   
   //Sets y gets de atributos
   public void setId(int id) {
      this.id=id;
   }
   public void setFecha(LocalDate fecha) {
      this.fecha=fecha;
   }
   public void setDescripcion(String descripcion) {
      this.descripcion=descripcion;
   }
   public void setNombreReporta(String nombreReporta) {
      this.nombreReporta=nombreReporta;
   }
   public void setAccionesTomadas(String accionesTomadas) {
      this.accionesTomadas=accionesTomadas;
   }
   public void setLevantado(boolean levantado) {
      this.levantado=levantado;
   }
   public void setEstatus(String estatus) {
      this.estatus=estatus;
   }
   
   public int getId(){
      return id;
   }
   public LocalDate getFecha(){
      return fecha;
   }
   public String getDescripcion(){
      return descripcion;
   }
   public String getNombreReporta(){
      return nombreReporta;
   }
   public String getAccionesTomadas(){
      return accionesTomadas;
   }
   public boolean getLevantado(){
      return levantado;
   }
   public String getEstatus(){
      return estatus;
   }
   
   //M�todo para generar los espacios de formato
    public String generarEspacios(String variable){
      while(variable.length() < 30){
         variable += " ";
       }
       return variable;
    }
    
    public String toString() {
      Locale espanol = new Locale("es", "ES"); // sirve para que el idioma sea espa�ol para los meses
      DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", espanol); // para dar formato de salida de la fecha como dd-MMMM-aaaa
      String salida = "";
      salida += generarEspacios(String.valueOf(id))+generarEspacios(String.valueOf(formatoFecha.format(fecha)))+generarEspacios(descripcion)+generarEspacios(nombreReporta)+generarEspacios(accionesTomadas)+generarEspacios(String.valueOf(levantado))+generarEspacios(estatus); 
      return "\n"+salida;             
    }
}

//Clase que contine el reporte de todos los incidentes
class Reporte{
    private ArrayList<Incidente> incidentes;
    
    public Reporte (ArrayList<Incidente> incidentes){
        setIncidentes(incidentes); 
    }
    
    public void setIncidentes(ArrayList<Incidente> incidentes) {
        this.incidentes = incidentes;
    }
    public ArrayList<Incidente> getIncidentes() {
        return incidentes;
    }

    //M�todo para generar los espacios de formato
    public String generarEspacios(String variable){
       while(variable.length() < 30){
         variable += " ";
       }
       return variable;
    }

       
//Metodo to String para imprimir el reporte diario
    public String toString() {
      String reporte = "-------------- Reporte de Incidentes ---------------\n"+generarEspacios("ID:")+generarEspacios("Fecha:")+generarEspacios("Descripcion:")+generarEspacios("Persona:")+generarEspacios("Acciones:")+generarEspacios("Levantado?:")+generarEspacios("Estatus:");
      for(int i = 0; i < incidentes.size(); i++) //Ciclo que manda a desplegar cada incidente
        reporte += incidentes.get(i);
      return reporte;
    }
}

//Clase que contine el reporte de todos los incidentes
class Razon{
    private int idIncidente;
    private String texto;
    
    public Razon (int idIncidente, String texto){
        setIdIncidente(idIncidente); 
        setTexto(texto);
    }
    
    public void setIdIncidente(int idIncidente) {
        this.idIncidente = idIncidente;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public int getIdIncidente() {
        return idIncidente;
    }
    public String getTexto() {
        return texto;
    }
}

class Principal {
   public static void main(String[] args) {
      Scanner s=new Scanner(System.in);
      Principal p = new Principal();
      int opcion = 0;
      ArrayList<Incidente> incidentes = new ArrayList<>();
      ArrayList<Razon> razones = new ArrayList<>();
      do {
      System.out.println("---Que desea hacer?---\n");
      System.out.println("1) Registrar Incidente");
      System.out.println("2) Ver incidentes levantados");
      System.out.println("3) Editar Acciones Tomadas");
      System.out.println("4) Eliminar Incidentes");
      System.out.println("5) Cambiar Estatus de un Incidente");
      System.out.println("6) Ver Reporte");
      System.out.println("7) Salir");
      opcion = s.nextInt();
      switch(opcion){
         case 1:{
            String f[] = p.capturarFecha("Ingrese la fecha del incidente en formato dd-mm-aaa:");
            LocalDate fecha = LocalDate.of(Integer.parseInt(f[2]),Integer.parseInt(f[1]),Integer.parseInt(f[0]));
            Incidente i = new Incidente(p.capturarId("Ingrese el ID del incidente (6 DIGITOS)"),fecha,p.capturarDatos("Ingrese la descripcion del incidente:"),p.capturarDatos("Ingrese el nombre de la persona quien reporta:"),p.capturarDatos("Ingrese las acciones realizadas:"),p.capturarLevantado("El incidente fue levantado? (SI/NO)"),p.capturarEstatus("Ingrese el estatus del incidente (ABIERTO/CERRADO):"));
            incidentes.add(i);
            break;
         }
         case 2:{
            System.out.println("-------------- Reporte de Incidentes Levantados---------------\n"+p.generarEspacios("ID:")+p.generarEspacios("Fecha:")+p.generarEspacios("Descripcion:")+p.generarEspacios("Persona:")+p.generarEspacios("Acciones:")+p.generarEspacios("Levantado?:")+p.generarEspacios("Estatus:"));
            for(Incidente incidente: incidentes)
               if (incidente.getLevantado())
                  System.out.println(incidente);
            break;
         }
         case 3:{
            int n = p.capturarId("Ingrese el ID del incidente que desea editar: ");
            for(Incidente incidente: incidentes)
               if (incidente.getId()==n) 
                  incidente.setAccionesTomadas(p.capturarDatos("Actualice las nuevas acciones realizadas: "));
         break;
         }
         case 4:{
            int n = p.capturarId("Ingrese el ID del incidente que desea eliminar: ");
            for(Incidente incidente: incidentes)
               if (incidente.getId()==n) { 
                  incidentes.remove(incidente);
                  break;
               }
            break;
         }
         case 5:{
            int n = p.capturarId("Ingrese el ID del incidente que desea editar: ");
            for(Incidente incidente: incidentes)
               if (incidente.getId()==n) {
                  incidente.setEstatus(p.capturarEstatus("Actualice el estatus: "));
                  Razon r = new Razon(incidente.getId(),p.capturarDatos("Indique la razon del cambio: "));
                  razones.add(r);
               }
         }   
         break;         
         case 6:{
            Reporte reporte = new Reporte(incidentes);
            System.out.println(reporte);
            break;
         }
      }
      }while(opcion != 7);
   }
   
   public String capturarDatos(String mensaje) {
      Scanner s = new Scanner(System.in);
      System.out.println(mensaje+"\n");
      return s.nextLine();
   }
   //Validacion de codigo de producto
   public int capturarId(String mensaje){
      int numEntero=0;
      String numeroS="";
      boolean verdadero=true;
      do{
        Scanner s=new Scanner(System.in);
            System.out.println(mensaje);
            numeroS=s.nextLine(); 
        try {  
            numEntero=Integer.parseInt(numeroS); 
            if(numeroS.length()!=6)
            System.out.println("Tiene que ser un numero entero de 6 digitos");
            else
            return numEntero;
          } catch(NumberFormatException e){ 
            verdadero=false;
            System.out.println("Tiene que ser un numero entero"); 
          } 
          }while(numeroS.length()!=6||verdadero==false);
       return numEntero;
    }
   
   public String[] capturarFecha(String mensaje){
      Scanner s=new Scanner(System.in);
      System.out.println(mensaje);
      String fecha[] = (s.next()).split("\\-");//al tener la fecha, la dividimos para obtener cada valor por separado
      return fecha;
   }
   public boolean capturarLevantado(String mensaje) {
      Scanner s = new Scanner(System.in);
      System.out.println(mensaje+"\n");
      boolean levantado = false;
      String r = s.next();
      while (!(r.equalsIgnoreCase("si")) && !(r.equalsIgnoreCase("no"))) {
         System.out.println("Favor de escribir si o no. No se aceptara otro valor.");
         r = s.nextLine();
      }
      if (r.equalsIgnoreCase("si"))
         levantado = true;
      return levantado;
   }
   
   public String capturarEstatus(String mensaje) {
      Scanner s = new Scanner(System.in);
      System.out.println(mensaje+"\n");
      String estatus = s.next();
      while (!(estatus.equalsIgnoreCase("cerrado")) && !(estatus.equalsIgnoreCase("abierto"))) {
         System.out.println("Favor de escribir o ABIERTO o CERRADO. No se aceptara otro valor.");
         estatus = s.next();
      }
      return estatus;
   }
   
   //M�todo para generar los espacios de formato
    public String generarEspacios(String variable){
       while(variable.length() < 30){
         variable += " ";
       }
       return variable;
    }
}