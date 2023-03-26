import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PronosticosDeportivos {
    public static void main(String[] args) throws IOException{
        //Leer archivo de partidos
        List<Partido> partidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("partidos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                partidos.add(new Partido(campos[0], campos[1]));}}

        //Leer archivo de resultados
        Map<Partido, String> resultados = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resultados.txt"))){
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null) {
                Partido partido = partidos.get(i);
                partido.resultado = linea;
                resultados.put(partido, linea);
                i++;
            }
        }

        //Leer archivo de pronósticos
        List<Pronostico> pronosticos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pronosticos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                pronosticos.add(new Pronostico(campos[0], campos[1], campos[2]));
            }
        }

        //Calcular puntajes de los pronósticos
        int puntos = 0;
        Map<String, Integer> puntajes = new HashMap<>();
        for (Pronostico pronostico : pronosticos) {
            String resultadoReal = resultados.get(new Partido(pronostico.local, pronostico.visitante));
            if (pronostico.resultado.equals(resultadoReal)) {
                puntajes.put(pronostico.local + " vs " + pronostico.visitante, 1);
                puntos++;
            } else {
                puntajes.put(pronostico.local + " vs " + pronostico.visitante, 0);
            }
        }

        //Ordenar por puntaje obtenido en cada partido
        List<Map.Entry<String, Integer>> listaPuntajes = new ArrayList<>(puntajes.entrySet());
        Collections.sort(listaPuntajes, (a, b) -> b.getValue().compareTo(a.getValue()));

        //Imprimir resultados
        System.out.println("Puntos sumados por cada partido:\n");
        for (Map.Entry<String, Integer> entry : listaPuntajes) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " puntos");
        }
        System.out.println("\nPuntaje total del participante en esta ronda: " + puntos + " puntos.");
    }
}
