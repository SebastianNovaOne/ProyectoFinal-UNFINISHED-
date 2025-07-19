package logica;

/**
 * Excepcion que ocurre cuando se intenta realizar una reserva que ya ha sido confirmada.
 * Esto ocurre cuando el cliente intenta duplicar una reserva existente (por ejemplo podria ser misma clase fecha y hora).
 */
public class ReservaDuplicadaException extends Exception {

  /**
   * Constructor que recibe el mensaje de error.
   *
   * @param mensaje Detalle que describe la causa de la excepción.
   */
  public ReservaDuplicadaException(String mensaje) {
    super(mensaje);
  }
}