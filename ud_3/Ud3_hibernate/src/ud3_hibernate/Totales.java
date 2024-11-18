
package ud3_hibernate;

class Totales {
    private Long cuenta;
    private Byte numero;
    private Double media;
    private String nombre;

    public Totales(Byte numero, Long cuenta, Double media, String nombre) {
        this.cuenta = cuenta;
        this.numero = numero;
        this.media = media;
        this.nombre = nombre;
    }

    public Totales() {
    }

    public Long getCuenta() {
        return cuenta;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    public Byte getNumero() {
        return numero;
    }

    public void setNumero(Byte numero) {
        this.numero = numero;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
