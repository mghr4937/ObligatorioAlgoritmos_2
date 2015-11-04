package Estructuras.Grafo.MatrizAdyacencia;

public class NodoPunto {

    private static int ProxId = 1;
    private int id;
    private String nombre;
    private String coordX;
    private String coordY;
    private NodoPunto sig;

    public NodoPunto getSig() {
        return sig;
    }

    public NodoPunto(String nombre, String coordX, String coordY) {
        this.nombre = nombre;
        this.coordX = coordX;
        this.coordY = coordY;
        this.id = this.ProxId;
        this.ProxId++;
    }

    public NodoPunto() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodoPunto other = (NodoPunto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static int getProxId() {
        return ProxId;
    }

    public static void setProxId(int ProxId) {
        NodoPunto.ProxId = ProxId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoordX() {
		return coordX;
	}

	public void setCoordX(String coordX_) {
		coordX = coordX_;
	}

	public String getCoordY() {
		return coordY;
	}

	public void setCoordY(String coordY_) {
		coordY = coordY_;
	}

	public void setSig(NodoPunto nuevo) {
        this.sig = nuevo;
    }
}
