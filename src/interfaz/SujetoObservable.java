package interfaz;

public interface SujetoObservable {
	void agregarObservador(Observador o);
    void eliminarObservador(Observador o);
    void notificarObservadores();
}
