package com.ejemplo.decorador;
public abstract class CondimentoDecorador implements Bebida {
    protected final Bebida bebida;
    private final String nombreCondimento;
    private final double costoExtra;
    protected CondimentoDecorador(Bebida bebida, String nombre, double costoExtra) {
        if (bebida == null || nombre == null || nombre.trim().isEmpty() || !Double.isFinite(costoExtra) || costoExtra < 0)
            throw new BebidaInvalidaException("Condimento inválido");
        this.bebida = bebida;
        this.nombreCondimento = nombre;
        this.costoExtra = costoExtra;
    }
    @Override public String getDescripcion() { return bebida.getDescripcion() + ", " + nombreCondimento; }
    @Override public double costo() { return bebida.costo() + costoExtra; }
    @Override public void mostrarDetalle() { System.out.println(getDescripcion() + " - Q" + String.format(java.util.Locale.US, "%.2f", costo())); }
}
