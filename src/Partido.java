import java.util.Objects;
public class Partido {
    public String local;
    public String visitante;
    public String resultado;

    public Partido(String local, String visitante) {
        this.local = local;
        this.visitante = visitante;
        this.resultado = null;}

    //Modificación para que soporte "equals" y "HashCode"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return local.equals(partido.local) && visitante.equals(partido.visitante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(local, visitante);
    }
}
