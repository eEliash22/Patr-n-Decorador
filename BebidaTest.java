package com.ejemplo.decorador;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
public class BebidaTest {
    @Test public void descripcionBase() { assertEquals("Café", new CafeSimple("Café", 10).getDescripcion()); }
    @Test public void costoBase() { assertEquals(10.0, new CafeSimple("Café", 10).costo(), 0.001); }
    @Test public void decoradoresAcumulados() {
        Bebida b = new CremaDecorador(new ChocolateDecorador(new LecheDecorador(new CafeSimple("Café", 10))));
        assertEquals(20.0, b.costo(), 0.001);
        assertEquals("Café, Leche, Chocolate, Crema", b.getDescripcion());
    }
    @Test public void salidaVoid() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(bytes, true, "UTF-8"));
            new CafeSimple("Café", 10).mostrarDetalle();
        } finally { System.setOut(original); }
        assertTrue(new String(bytes.toByteArray(), StandardCharsets.UTF_8).contains("Café - Q10.00"));
    }
    @Test(expected = BebidaInvalidaException.class) public void precioNegativo() { new CafeSimple("Café", -1); }
    @Test(expected = BebidaInvalidaException.class) public void decoradorNulo() { new LecheDecorador(null); }
    @Test public void ordenAgregarEliminarYVaciar() {
        OrdenCafe<Bebida> orden = new OrdenCafe<>();
        Bebida b = new CafeSimple("Café", 10);
        orden.agregarItem(b);
        assertEquals(1, orden.obtenerItems().size());
        assertEquals(10.0, orden.calcularTotal(), 0.001);
        orden.eliminarItem(b);
        assertTrue(orden.obtenerItems().isEmpty());
        orden.agregarItem(b);
        orden.vaciar();
        assertEquals(0.0, orden.calcularTotal(), 0.001);
    }
    @Test public void catalogoYMenu() throws Exception {
        CatalogoCafe<Bebida> catalogo = new CatalogoCafe<>("Central");
        catalogo.agregarBebida(new CafeSimple("Café", 10));
        assertEquals(1, catalogo.obtenerBebidas().size());
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try { System.setOut(new PrintStream(bytes, true, "UTF-8")); catalogo.mostrarMenu(); }
        finally { System.setOut(original); }
        assertTrue(new String(bytes.toByteArray(), StandardCharsets.UTF_8).contains("Menú: Central"));
    }
    @Test(expected = BebidaInvalidaException.class) public void ordenRechazaNulos() { new OrdenCafe<Bebida>().agregarItem(null); }
}
